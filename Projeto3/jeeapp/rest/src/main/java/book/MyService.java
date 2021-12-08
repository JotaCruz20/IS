package book;

import beans.Bean;
import data.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/myservice")
@Produces(MediaType.APPLICATION_JSON)
public class MyService {

    @EJB
    private Bean beans;

    @POST
    @Path("/addClient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(ClientDTO client) {
        Admin admin = beans.login(client.getAdmin().getName());
        Client client1 = new Client(client.getName(),0,0,0,admin);
        beans.addClient(client1);
        String str = "Person received : " + client.getName();
        return Response.status(Response.Status.OK).entity(str).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(String name) {
        Admin admin1 = beans.login(name);
        if(admin1 != null) {
            AdminDTO adminDTO = new AdminDTO(admin1.getId(), admin1.getName());
            return Response.status(Response.Status.OK).entity(adminDTO).build();
        }
        else{
            AdminDTO adminDTO = new AdminDTO(0,"error");
            return Response.status(Response.Status.OK).entity(adminDTO).build();
        }
    }

    @POST
    @Path("/addManager")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addManager(AdminDTO admin) {
        Admin admin1 = new Admin(admin.getName());
        beans.addManager(admin1);
        String str = "Person received : " + admin.getName();
        return Response.status(Response.Status.OK).entity(str).build();
    }

    @POST
    @Path("/addCurrency")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurrency(CurrencyDTO currencies) {
        Currencies currencies1 = new Currencies(currencies.getName(),currencies.getExchangeRate());
        beans.addCurrency(currencies1);
        String str = "Person received : " + currencies.getName();
        return Response.status(Response.Status.OK).entity(str).build();
    }

    @GET
    @Path("/clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClients() {
        List<Client> personList = beans.getClient();
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client c:personList) {
            AdminDTO adminDTO = new AdminDTO(c.getAdmin().getId(), c.getAdmin().getName());
            clientDTOS.add(new ClientDTO(c.getId(),c.getName(),c.getCredit(),c.getPayment(),c.getBalance(),adminDTO));
        }
        return Response.ok().entity(clientDTOS).build();
    }

    @GET
    @Path("/admins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdmins() {
        List<Admin> personList = beans.getAdmin();
        List<AdminDTO> adminDTOS = new ArrayList<>();
        for (Admin a:personList) {
            adminDTOS.add(new AdminDTO(a.getId(),a.getName()));
        }
        return Response.ok().entity(adminDTOS).build();
    }

    @GET
    @Path("/currencies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrencies() {
        List<Currencies> personList = beans.getCurrencies();
        List<CurrencyDTO> currencyDTOS = new ArrayList<>();
        for (Currencies c:personList) {
            currencyDTOS.add(new CurrencyDTO(c.getName(),c.getExchangeRate()));
        }
        return Response.ok().entity(currencyDTOS).build();
    }
}
