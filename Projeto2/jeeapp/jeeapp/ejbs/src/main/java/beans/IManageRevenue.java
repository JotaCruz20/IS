package beans;

import data.Client;
import data.ClientDTO;
import data.Manager;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IManageRevenue {
    public void sendDailyRevenue() throws ParseException;

}