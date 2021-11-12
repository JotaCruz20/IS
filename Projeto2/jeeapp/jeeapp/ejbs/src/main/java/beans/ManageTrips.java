package beans;

import data.*;
import org.hibernate.type.LocalDateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class ManageTrips implements IManageTrips {
    Logger logger = LoggerFactory.getLogger(ManageTrips.class);
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    public void addTrip(String destination, String departure, String price, String capacity, String departureTime) {
        logger.info("Adding bus trip ...");
        logger.info("departure time: " + departureTime);
        LocalDateTime dateTime = LocalDateTime.parse(departureTime);
        logger.info("departure time: " + dateTime.toString());
        double priceD = Double.parseDouble(price);
        int capacityI = Integer.parseInt(capacity);
        Bus bus = new Bus(departure, destination, dateTime, capacityI, priceD);
        em.persist(bus);
    }

    public List<BusDTO> getTrips(String startS, String endS) throws ParseException {
        logger.info("Selecting bus trips from: " + startS + " to:" + endS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startS);
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endS);

        Instant instant = Instant.ofEpochMilli(start.getTime());
        LocalDateTime dateStart = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        Instant instantEnd = Instant.ofEpochMilli(end.getTime());
        LocalDateTime dateEnd = LocalDateTime.ofInstant(instantEnd, ZoneOffset.UTC);

        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.departureTime >= :start and b.departureTime <= :end", Bus.class).setParameter("start", dateStart).setParameter("end", dateEnd);
        List<Bus> buses = q.getResultList();
        List<BusDTO> tripDTOS = new ArrayList<>();
        for (Bus bus : buses) {
            tripDTOS.add(new BusDTO(bus.getId(), bus.getDeparturePoint(), bus.getDestination(), bus.getDepartureTime().format(formatter), bus.getCapacity()));
        }
        return tripDTOS;
    }

    public List<BusDTO> getTripsUser(String email) {
        logger.info("Selecting bus trips from user: " + em);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TypedQuery<Client> q = em.createQuery("from Client c " +
                "where c.email = :email", Client.class).setParameter("email", email);
        Client client = q.getSingleResult();

        TypedQuery<Ticket> q1 = em.createQuery("from Ticket t " +
                "where t.client = :client", Ticket.class).setParameter("client", client);
        List<Ticket> tickets = q1.getResultList();

        List<BusDTO> tripDTOS = new ArrayList<>();
        for (Ticket ticket : tickets) {
            TypedQuery<Bus> q2 = em.createQuery("from Bus b " +
                    "where b= :bus and b.departureTime > :now", Bus.class).setParameter("bus", ticket.getBus()).setParameter("now", now);
            Bus bus = q2.getSingleResult();
            tripDTOS.add(new BusDTO(bus.getId(), bus.getDeparturePoint(), bus.getDestination(), bus.getDepartureTime().format(formatter), bus.getCapacity(), ticket.getPlace(), ticket.getId()));
        }
        return tripDTOS;
    }

    public List<BusDTO> getTrips() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logger.info("Selecting bus trips from: " + now.toString());
        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.departureTime > :start", Bus.class).setParameter("start", now);
        List<Bus> buses = q.getResultList();
        List<BusDTO> tripDTOS = new ArrayList<>();
        for (Bus bus : buses) {
            tripDTOS.add(new BusDTO(bus.getId(), bus.getDeparturePoint(), bus.getDestination(), bus.getDepartureTime().format(formatter), bus.getCapacity()));
        }
        return tripDTOS;
    }

    public List<Integer> getSeats(int id) {
        logger.info("Selecting bus seats from: " + id);
        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.id = :id", Bus.class).setParameter("id", id);
        Bus bus = q.getSingleResult();

        TypedQuery<Ticket> q1 = em.createQuery("from Ticket t " +
                "where t.bus = :bus", Ticket.class).setParameter("bus", bus);

        List<Ticket> tickets = q1.getResultList();

        List<Integer> availableSeats = new ArrayList<Integer>();
        for (int i = 1; i < bus.getCapacity() + 1; i++) {
            availableSeats.add(i);
        }
        for (Ticket ticket : tickets) {
            availableSeats.remove(ticket.getPlace() - 1);
        }
        return availableSeats;
    }

    public void buyTicket(String busId, String seat, String user) {

        logger.info("Buying ticket for bus: " + busId + " in seat: " + seat + " for user: " + user);

        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.id = :id", Bus.class).setParameter("id", Integer.parseInt(busId));
        Bus bus = q.getSingleResult();

        TypedQuery<Client> q1 = em.createQuery("from Client c " +
                "where c.email = :email", Client.class).setParameter("email", user);
        Client client = q1.getSingleResult();

        client.setWallet((long) (client.getWallet() - bus.getPrice()));
        em.persist(client);

        Ticket ticket = new Ticket(Integer.parseInt(seat), client, LocalDateTime.now(), bus);
        em.persist(ticket);
    }

    public List<ClientDTO> getTripsPassenger(int tripId) {
        int flag = 0;
        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.id= :tripId", Bus.class).setParameter("tripId", tripId);
        Bus bus = q.getSingleResult();

        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Ticket ticket : bus.getTickets()) {
            ClientDTO c = new ClientDTO(ticket.getClient().getEmail(), ticket.getClient().getName());

            for (ClientDTO cl : clientDTOS) {
                if (c.getEmail() == cl.getEmail()) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                clientDTOS.add(c);
            } else {
                flag = 0;
            }
        }
        return clientDTOS;
    }

    public List<BusDTO> getTripsOnDate(String startS) throws ParseException {
        logger.info("Selecting bus trips from: " + startS + " to next day");
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(startS));
        c.add(Calendar.DATE, 1);  // number of days to add
        String endS = sdf.format(c.getTime());  //
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endS);

        Instant instant = Instant.ofEpochMilli(start.getTime());
        LocalDateTime dateStart = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        Instant instantEnd = Instant.ofEpochMilli(end.getTime());
        LocalDateTime dateEnd = LocalDateTime.ofInstant(instantEnd, ZoneOffset.UTC);

        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.departureTime >= :start and b.departureTime < :end", Bus.class).setParameter("start", dateStart).setParameter("end", dateEnd);
        List<Bus> buses = q.getResultList();
        List<BusDTO> tripDTOS = new ArrayList<>();
        for (Bus bus : buses) {
            logger.info(bus.getId() + " " + bus.getDepartureTime());
            tripDTOS.add(new BusDTO(bus.getId(), bus.getDeparturePoint(), bus.getDestination(), bus.getDepartureTime().format(formatter), bus.getCapacity()));
        }
        return tripDTOS;
    }

    public void deleteTrip(String tripId) {
        logger.info("deleting trip: " + tripId);
        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.id = :tripId", Bus.class).setParameter("tripId", Integer.parseInt(tripId));
        Bus bus = q.getSingleResult();
        logger.info("completed querie for trip: " + tripId);

        List<Ticket> tickets = bus.getTickets();

        for (Ticket ticket : tickets) {
            Client client = ticket.getClient();
            client.setWallet((long) (client.getWallet() + bus.getPrice()));
        }

        em.remove(bus);
        logger.info("deleted trip: " + tripId);
    }



    public void returnTicket(String busId, String user, String ticketId) {

        logger.info("Returning ticket for bus: "+busId+ "for user: "+user);

        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.id = :id", Bus.class).setParameter("id", Integer.parseInt(busId));
        Bus bus = q.getSingleResult();

        TypedQuery<Client> q1 = em.createQuery("from Client c " +
                "where c.email = :email", Client.class).setParameter("email", user);
        Client client = q1.getSingleResult();

        client.setWallet((long) (client.getWallet()+bus.getPrice()));
        em.persist(client);

        TypedQuery<Ticket> q2 = em.createQuery("from Ticket t " +
                "where t.id = :id", Ticket.class).setParameter("id", Integer.parseInt(ticketId));
        Ticket ticket = q2.getSingleResult();
        em.remove(ticket);
    }
}

