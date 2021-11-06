package beans;

import data.*;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ManageClients implements IManageClients {
    Logger logger = LoggerFactory.getLogger(ManageClients.class);
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    public void addClient(String email, String password, String name, String birthdate) throws ParseException { //https://stackoverflow.com/questions/27667034/how-to-encrypt-password-using-spring-hibernate-in-java encriptar(?)
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

    public void updateInfo(String email,String name, String birthdate, String password) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);

        TypedQuery<Client> q = em.createQuery("from Client c " +
                "where c.email= :email", Client.class).setParameter("email", email);
        Client client = q.getSingleResult();
        client.setName(name);
        client.setBirthdate(date1);
        client.setPassword(password);
        em.persist(client);

    }

    public void delete(String email){
        TypedQuery<Client> q = em.createQuery("from Client c " +
                "where c.email= :email", Client.class).setParameter("email", email);
        Client client = q.getSingleResult();

        for (Ticket ticket : client.getTickets()) {
            TypedQuery<Bus> q1 = em.createQuery("from Bus b " +
                    "where b.id= :id", Bus.class).setParameter("id", ticket.getBus().getId());
            Bus bus = q1.getSingleResult();
            bus.setCapacity(bus.getCapacity()+1);
            em.persist(bus);
        }
        em.remove(client);
    }

    public ClientDTO getClientInfo(String email){
        logger.info("Getting Client:" + email +" info ...");
        TypedQuery<Client> q = em.createQuery("from Client c " +
                "where c.email= :email", Client.class).setParameter("email", email);
        Client client = q.getSingleResult();

        logger.info("Date: "+ client.getBirthdate().toString().split(" ")[0]);
        return new ClientDTO(client.getEmail(), client.getName(), client.getBirthdate().toString().split(" ")[0]);

    }


    public List<Client> getClientInfoDebug(){
        TypedQuery<Client> q = em.createQuery("from Client c", Client.class);
        return q.getResultList();
    }


}
