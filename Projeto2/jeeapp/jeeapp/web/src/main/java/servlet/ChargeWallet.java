package servlet;

import beans.IManageClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/chargeWallet")
public class ChargeWallet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ChargeWallet.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageClients manageClients;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("auth");
        double money = Double.parseDouble(req.getParameter("money"));
        logger.info("Charging: "+ money+" from: "+ email +" wallet");

        manageClients.chargeWallet(email, money);
        req.getRequestDispatcher("/secured/main.jsp").forward(req, resp);
    }
}
