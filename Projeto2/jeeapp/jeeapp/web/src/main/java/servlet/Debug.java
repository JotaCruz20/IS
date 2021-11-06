package servlet;

import beans.IManageClients;
import data.Client;
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
import java.util.stream.Collectors;

@WebServlet("/debug")
public class Debug extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Logout.class);
    private static final long serialVersionUID = 1L;

    @EJB
    private IManageClients manageClients;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<String> field1List = manageClients.getClientInfoDebug().stream().map(Client::getName).collect(Collectors.toList());
        String result = "Client list: " + field1List;
        System.out.println(result);
        response.getWriter().print(result);
    }
}
