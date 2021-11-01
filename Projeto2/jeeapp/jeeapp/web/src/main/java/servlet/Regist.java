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

@WebServlet("/create")
public class Regist extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Regist.class);
    private static final long serialVersionUID = 1L;
    @EJB
    private IManageClients manageClients;

//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        List<String> field1List = manageClients.listClients().stream().
//                map(Manager::getName).collect(Collectors.toList());
//        String result = "Manager list: " + field1List;
//        System.out.println(result);
//        response.getWriter().print(result);
//    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/regist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        logger.info("Starting Create User");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthdate = req.getParameter("birthdate");

        logger.info("Email: "+email+" password: "+password+" name: "+ name," birthdate: "+ birthdate);

        try {
            manageClients.addClient(email, password, name, birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}