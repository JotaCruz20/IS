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

@WebServlet("/buyTrip")
public class BuyTrip extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ChargeWallet.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageClients manageClients;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("tripId");
        logger.info("Buying trip:" + id);

        //TODO: TRY THIS
        req.getRequestDispatcher("/secured/main.jsp").forward(req, resp);
    }
}
