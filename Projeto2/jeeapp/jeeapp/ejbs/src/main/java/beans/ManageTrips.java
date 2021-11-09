package beans;

import data.Bus;
import data.BusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class ManageTrips implements IManageTrips {
    Logger logger = LoggerFactory.getLogger(ManageTrips.class);
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    public void addTrip(String destination, String departure,String price, String capacity, String departureTime){
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
        logger.info("Selecting bus trips from: "+startS+" to:"+endS);
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startS);
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endS);
        TypedQuery<Bus> q = em.createQuery("from Bus b " +
                "where b.departureTime > :start and b.departureTime < :end", Bus.class).setParameter("start", start).setParameter("end",end);
        List<Bus> buses = q.getResultList();
        List<BusDTO> tripDTOS = new ArrayList<>();
        for (Bus bus:buses) {
            tripDTOS.add(new BusDTO(bus.getId(),bus.getDeparturePoint(),bus.getDestination(),bus.getDepartureTime(),bus.getCapacity()));
        }
        return tripDTOS;
    }

}
