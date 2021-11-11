package main;
import data.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddManager {
    public static void main(String[] args) throws ParseException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        trx.begin();

        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(args[3]);

        Manager manager = new Manager(args[0],args[1],args[2],date1);
        em.persist(manager);
        trx.commit();
    }
}
