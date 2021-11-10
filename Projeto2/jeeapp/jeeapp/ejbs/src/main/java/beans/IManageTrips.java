package beans;

import data.BusDTO;

import java.text.ParseException;
import java.util.List;

public interface IManageTrips {

    public void addTrip(String destination, String departure,String price, String capacity, String departureTime) throws ParseException;

    public List<BusDTO> getTrips(String start, String end) throws ParseException;

    public List<BusDTO> getTrips();
}
