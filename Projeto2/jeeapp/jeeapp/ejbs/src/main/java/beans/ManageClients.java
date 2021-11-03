package beans;

import data.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import data.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ManageClients implements IManageClients {
    Logger logger = LoggerFactory.getLogger(ManageClients.class);
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    public void addClient(String email, String password, String name, String birthdate) throws ParseException {
        logger.info("Adding client ...");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        Client s = new Client(email, name, password, date1);
        em.persist(s);
    }

    public int login(String email, String password) {
        logger.info("Tentando login com user: " + email + " pass: " + password);
        try {
            TypedQuery<Client> q = em.createQuery("from Client c " +
                    "where c.email= :email and c.password= :password ", Client.class).setParameter("email", email).setParameter("password", password);
            Client client = q.getSingleResult();
            return 1;
        } catch (NoResultException e) {
            logger.info("Not a client");
        }
        try {
            TypedQuery<Manager> q = em.createQuery("from Manager m " +
                    "where m.email= :email and m.password= :password ", Manager.class).setParameter("email", email).setParameter("password", password);
            Manager manager = q.getSingleResult();
            return 2;
        } catch (NoResultException e) {
            logger.info("Not a manager");
        }
        return -1;
    }

    public void updateInfo(String name, Date birthdate) {

    }

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
}
