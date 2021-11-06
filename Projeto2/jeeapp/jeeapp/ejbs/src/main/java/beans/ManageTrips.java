package beans;

import data.Bus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

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

}
