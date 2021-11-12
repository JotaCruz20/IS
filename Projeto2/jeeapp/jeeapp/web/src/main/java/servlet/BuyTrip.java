package servlet;

import beans.IManageClients;
import beans.IManageTrips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/buyTrip")
public class BuyTrip extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(BuyTrip.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageTrips manageTrips;
    @EJB
    private IManageClients manageClients;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("tripId");
        logger.info("Selecting seats trip:" + id);

        req.getSession(true).setAttribute("tripSelected", id);

        List<Integer> availableSeats = manageTrips.getSeats(Integer.parseInt(id));
        req.getSession(true).setAttribute("seats",availableSeats);
        req.getRequestDispatcher("/secured/selectSeats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String busId = (String) req.getSession(true).getAttribute("tripSelected");
        String user = (String) req.getSession(true).getAttribute("auth");
        String seat = req.getParameter("seat");

        if(!manageClients.checkWallet(busId,user)){
            logger.info("User: " + user + " doesn't have enough money for this trip");
            req.setAttribute("error","Not enough money!");
            req.getRequestDispatcher("/secured/errors.jsp").forward(req, resp);
        }
        else {
            logger.info("Buying trip:" + busId + " for user: " + user + " in seat: " + seat);

            manageTrips.buyTicket(busId, seat, user);
            req.getRequestDispatcher("/secured/main.jsp").forward(req, resp);
        }
    }
}
