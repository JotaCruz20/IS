package main;

import data.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Scanner;

public class RestClient {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = ClientBuilder.newClient();
        WebTarget target;
        Response response;
        String value;
        int n;
        AdminDTO answer;

        do{
            System.out.println("Login: ");
            System.out.println("Username: ");
            String username = scanner.nextLine();

            target = client.target("http://wildfly:8080/rest/rest/myservice/login");
            Entity<String> input = Entity.entity(username, MediaType.APPLICATION_JSON);
            response = target.request().post(input);
            answer = response.readEntity(new GenericType<AdminDTO>() {});
            response.close();
        }while (answer.getName().equals("error"));

        do {
            System.out.println("What do you want to do?\n1-List Clients\n2-List Admins\n3-List Currencies\n4-List Highest Debt Client\n5-List Clients with no Payments\n6-List Admin with the most Revenue\n7-List Totals\n8-Add Client\n9-Add Admin\n10-Add Currency\n0-Quit");
            n = scanner.nextInt();
            scanner.nextLine();
            switch (n){
                case 1:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/clients");
                    response = target.request().get();
                    List<ClientDTO> clientList = response.readEntity(new GenericType<List<ClientDTO>>() {
                    });
                    for (ClientDTO c:clientList) {
                        System.out.println(c);
                    }
                    response.close();
                    break;
                case 2:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/admins");
                    response = target.request().get();
                    List<AdminDTO> adminList = response.readEntity(new GenericType<List<AdminDTO>>() {
                    });
                    for (AdminDTO c:adminList) {
                        System.out.println(c);
                    }
                    response.close();
                    break;
                case 3:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/currencies");
                    response = target.request().get();
                    List<CurrencyDTO> currenciesList = response.readEntity(new GenericType<List<CurrencyDTO>>() {
                    });
                    for (CurrencyDTO c:currenciesList) {
                        System.out.println(c);
                    }
                    response.close();
                    break;
                case 4:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/debt");
                    response = target.request().get();
                    ClientDTO clientDTO = response.readEntity(new GenericType<ClientDTO>() {});
                    System.out.println(clientDTO);
                    response.close();
                    break;
                case 5:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/noPaymentClient");
                    response = target.request().get();
                    clientList = response.readEntity(new GenericType<List<ClientDTO>>() {
                    });
                    for (ClientDTO c:clientList) {
                        System.out.println(c);
                    }
                    response.close();
                    break;
                case 6:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/revenue");
                    response = target.request().get();
                    AdminDTO adminDTO = response.readEntity(new GenericType<AdminDTO>() {});
                    System.out.println(adminDTO);
                    response.close();
                    break;
                case 7:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/totals");
                    response = target.request().get();
                    TotalsDTO totalsDTO = response.readEntity(new GenericType<TotalsDTO>() {
                    });
                    System.out.println(totalsDTO);
                    response.close();
                    break;


                case 8:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/addClient");
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    ClientDTO c = new ClientDTO(name,answer);
                    Entity<ClientDTO> input = Entity.entity(c, MediaType.APPLICATION_JSON);
                    response = target.request().post(input);
                    response.readEntity(String.class);
                    response.close();
                    break;
                case 9:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/addManager");
                    System.out.println("Name: ");
                    name = scanner.nextLine();
                    AdminDTO a = new AdminDTO(name);
                    Entity<AdminDTO> input2 = Entity.entity(a, MediaType.APPLICATION_JSON);
                    response = target.request().post(input2);
                    response.readEntity(String.class);
                    response.close();
                    break;
                case 10:
                    target = client.target("http://wildfly:8080/rest/rest/myservice/addCurrency");
                    System.out.println("Name: ");
                    name = scanner.nextLine();
                    System.out.println("Exchange Rate: ");
                    Double rate = scanner.nextDouble();
                    CurrencyDTO cr = new CurrencyDTO(name,rate);
                    Entity<CurrencyDTO> input3 = Entity.entity(cr, MediaType.APPLICATION_JSON);
                    response = target.request().post(input3);
                    response.readEntity(String.class);
                    response.close();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose an option");
            }

        }while (n != 0);

    }
}
