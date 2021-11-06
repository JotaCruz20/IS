package servlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IManageClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/listtrips")
public class ListTrips extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Delete.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Deleting User");
        String email = (String) req.getSession().getAttribute("auth");
        manageClients.delete(email);
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}