package beans;

import data.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public boolean login(String email, String password) {
        logger.info("Tentando login com user: " + email + " pass: " + password);
        try {
            TypedQuery<Client> q = em.createQuery("from Client c " +
                    "where c.email= :email and c.password= :password ", Client.class).setParameter("email", email).setParameter("password", password);
            Client client = q.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }

        return true;
    }

    public void updateInfo(String name, Date birthdate) {

    }
}
