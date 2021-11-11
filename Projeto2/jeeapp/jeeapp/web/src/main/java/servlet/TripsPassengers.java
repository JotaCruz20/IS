package servlet;


import beans.IManageClients;
import beans.IManageTrips;
import data.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tripsPassengers")
public class TripsPassengers extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(EditInformation.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrips manageTrips;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tripId = Integer.parseInt(request.getParameter("tripId"));
        List<ClientDTO> clientDTO = manageTrips.getTripsPassenger(tripId);
        request.getSession(true).setAttribute("clients", clientDTO);
        request.getRequestDispatcher("/secured/tripsPassenger.jsp").forward(request, response);
    }
}
