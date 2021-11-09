package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClients;
import beans.IManageTrips;
import data.BusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteTrip")
public class DeleteTrip extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Regist.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageTrips manageTrips;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        try {
            List<BusDTO> tripDTOS = manageTrips.getTrips(formatter.format(date));
            req.getSession(true).setAttribute("trips",tripDTOS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/secured/deleteTrip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Creating bus trip Login");
        String destination = req.getParameter("destination");
        String departure = req.getParameter("departure");
        String price = req.getParameter("price");
        String capacity = req.getParameter("capacity");
        String departureTime = req.getParameter("departureTime");

        manageTrips.addTrip(destination,departure,price,capacity,departureTime);

        req.getRequestDispatcher("/secured/mainM.jsp").forward(req, resp);
    }
}