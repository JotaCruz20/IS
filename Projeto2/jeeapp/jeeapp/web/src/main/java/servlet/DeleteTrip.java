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
    Logger logger = LoggerFactory.getLogger(DeleteTrip.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageTrips manageTrips;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BusDTO> tripDTOS = manageTrips.getTrips();
        logger.info("Getting Trips: "+ tripDTOS);
        req.getSession(true).setAttribute("trips",tripDTOS);
        req.getRequestDispatcher("/secured/deleteTrip.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tripId = req.getParameter("tripId");
        logger.info("Deleting trip: "+tripId);
        manageTrips.deleteTrip(tripId);
        req.getRequestDispatcher("/secured/mainM.jsp").forward(req, resp);
    }
}