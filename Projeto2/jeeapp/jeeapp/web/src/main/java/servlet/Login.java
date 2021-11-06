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

@WebServlet("/login")
public class Login extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Login.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Starting Login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.info("Email: " + email + " password: " + password);

        int b = manageClients.login(email, password);

        if (b == 1) {
            req.getSession(true).setAttribute("auth", email);
            req.getRequestDispatcher("/secured/main.jsp").forward(req, resp);
        } else if (b == 2) {
            req.getSession(true).setAttribute("auth", email);
            req.getRequestDispatcher("/secured/mainM.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}