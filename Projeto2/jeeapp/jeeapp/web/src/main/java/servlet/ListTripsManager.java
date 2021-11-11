package servlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageTrips;
import data.BusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("/listTripsManager")
public class ListTripsManager extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Delete.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrips manageTrips;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Selecting Dates");
        req.getRequestDispatcher("/secured/selectDatesManager.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Selecting trips");
        String startDate = req.getParameter("start");
        String endDate = req.getParameter("end");


        try {
            List<BusDTO> busDTOS = manageTrips.getTrips(startDate, endDate);
            logger.info("Trips selected: "+busDTOS);
            req.getSession(true).setAttribute("trips", busDTOS);
            req.getRequestDispatcher("/secured/listTripsManager.jsp").forward(req, resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}