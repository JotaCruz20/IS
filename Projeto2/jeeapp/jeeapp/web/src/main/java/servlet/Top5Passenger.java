package servlet;


import beans.IManageClients;
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
import java.util.List;

@WebServlet("/top5passenger")
public class Top5Passenger extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Top5Passenger.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Getting top 5 passangers");
        List<ClientDTO> clients = manageClients.getTopClients();
        request.getSession(true).setAttribute("clients", clients);
        request.getRequestDispatcher("/secured/top5Passenger.jsp").forward(request, response);
    }
}