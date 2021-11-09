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
import java.text.ParseException;

@WebServlet("/editInfo")
public class EditInformation extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(EditInformation.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("auth");
        ClientDTO clientDTO = manageClients.getClientInfo(email);
        request.getSession(true).setAttribute("client", clientDTO);
        request.getRequestDispatcher("/secured/editInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String birthdate = req.getParameter("birthdate");
        String email = (String) req.getSession().getAttribute("auth");
        String password = req.getParameter("password");
        logger.info("Alterar informação do user: "+email);

        try {
            manageClients.updateInfo(email, name, birthdate, password);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/secured/main.jsp");
    }
}
