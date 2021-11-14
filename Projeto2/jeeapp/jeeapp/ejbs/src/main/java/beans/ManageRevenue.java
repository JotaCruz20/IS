package beans;

import data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Singleton
public class ManageRevenue implements IManageRevenue {
    Logger logger = LoggerFactory.getLogger(ManageTrips.class);
    @PersistenceContext(unitName = "projeto")
    EntityManager em;

    @Resource(mappedName = "java:jboss/mail/Default")
    private Session session;

    @Schedule(dayOfWeek = "*",hour = "18", minute = "26")
    public void sendDailyRevenue() throws ParseException {
        logger.info("getting today's date");

        LocalDate todayL = java.time.LocalDate.now();
        String today = todayL.toString();
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(today);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(today));
        c.add(Calendar.DATE, 1);  // number of days to add
        String endS = sdf.format(c.getTime());  //
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endS);

        Instant instant = Instant.ofEpochMilli(start.getTime());
        LocalDateTime dateStart = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        Instant instantEnd = Instant.ofEpochMilli(end.getTime());
        LocalDateTime dateEnd = LocalDateTime.ofInstant(instantEnd, ZoneOffset.UTC);

        logger.info("getting today's tickets");
        TypedQuery<Ticket> q1 = em.createQuery("from Ticket t " +
                "where t.buyDate >= :start and t.buyDate < :end", Ticket.class).setParameter("start", dateStart).setParameter("end", dateEnd);
        List<Ticket> tickets = q1.getResultList();

        logger.info("Calculating daily revenue");
        int numberOfTickets = 0;
        double totalRevenue = 0.0;
        for (Ticket ticket : tickets) {
            numberOfTickets++;
            totalRevenue += ticket.getBus().getPrice();
        }

        logger.info("getting managers");
        TypedQuery<Manager> q2 = em.createQuery("from Manager m", Manager.class);
        List<Manager> managers = q2.getResultList();


        logger.info("sending daily revenue");
        for (Manager manager : managers) {
            try {

                Message message = new MimeMessage(session);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(manager.getEmail()));
                message.setSubject("Daily Revenue");
                message.setText("Daily Revenue:\n"
                        + "\tTickets sold today: " + numberOfTickets
                        + "\tTotal Money:" + totalRevenue);
                Transport.send(message);

            } catch (MessagingException e) {
                logger.info("Falha ao enviar email: " + e.toString());
            }
        }
    }
}

