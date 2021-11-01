package beans;

import data.Client;
import data.Manager;

import java.text.ParseException;
import java.util.List;

public interface IManageClients {

    public void addClient(String email, String password, String name, String birthdate) throws ParseException;

    public boolean login(String email, String password);
}
