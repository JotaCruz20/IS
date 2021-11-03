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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/createTrip")
public class CreateTrip extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Regist.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Creating bus trip Login");
        String destination = req.getParameter("destination");
        String departure = req.getParameter("departure");
        String price = req.getParameter("price");
        String capacity = req.getParameter("capacity");
        String departureTime = req.getParameter("departureTime");

        manageClients.addTrip(destination,departure,price,capacity,departureTime);

        //How do i get the email in here
        //req.getSession(true).setAttribute("auth", email);
        req.getRequestDispatcher("/secured/mainM.jsp").forward(req, resp);
    }
}
