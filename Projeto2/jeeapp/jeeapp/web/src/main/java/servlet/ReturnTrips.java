package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageTrips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/returnTrips")
public class ReturnTrips extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ReturnTrips.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageTrips manageTrips;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String busId = (String) request.getParameter("busId");
        String user = (String) request.getSession(true).getAttribute("auth");
        String ticketId = request.getParameter("ticketId");

        logger.info("Returning trip from user:"+user+" from bus: "+busId);

        manageTrips.returnTicket(busId,user,ticketId);

        request.getRequestDispatcher("/secured/main.jsp").forward(request, response);
    }
}