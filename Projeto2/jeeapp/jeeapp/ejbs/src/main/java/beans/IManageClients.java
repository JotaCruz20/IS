package beans;

import data.Client;
import data.ClientDTO;
import data.Manager;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IManageClients {

    public void addClient(String email, String password, String name, String birthdate) throws ParseException;

    public int login(String email, String password);

    public List<Client> getClientInfoDebug();

    public ClientDTO getClientInfo(String email);

    public void updateInfo(String email,String name, String birthdate, String password) throws ParseException;

    public void delete(String email);

    public void chargeWallet(String email, int money);

    public List<ClientDTO> getTopClients();
}
