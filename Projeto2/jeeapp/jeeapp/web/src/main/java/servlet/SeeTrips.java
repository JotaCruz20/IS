package servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/seeTrips")
public class SeeTrips extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(SeeTrips.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrips manageTrips;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession(true).getAttribute("auth");
        logger.info("Seeing trips for user: "+email);
        List<BusDTO> busDTOList = manageTrips.getTripsUser(email);
        request.setAttribute("trips", busDTOList);
        request.getRequestDispatcher("/secured/listTrips.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){


    }
}