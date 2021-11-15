package servlet;

import java.io.IOException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClients;
import beans.IManageTrips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/createTrip")
public class CreateTrip extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Regist.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageTrips manageTrips;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Getting jsp for createTrip");
        req.getRequestDispatcher("/secured/createTrip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination = req.getParameter("destination");
        String departure = req.getParameter("departure");
        String price = req.getParameter("price");
        String capacity = req.getParameter("capacity");
        String departureTime = req.getParameter("departureTime");

        logger.info("Creating bus with destination: "+destination+" to: "+departure+" leaving at: "+departureTime+" with capacity: "+capacity+" with price: "+price);

        try {
            manageTrips.addTrip(destination,departure,price,capacity,departureTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/secured/mainM.jsp").forward(req, resp);
    }
}
