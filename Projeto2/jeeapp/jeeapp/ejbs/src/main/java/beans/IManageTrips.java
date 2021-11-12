package beans;

import data.BusDTO;
import data.ClientDTO;

import java.text.ParseException;
import java.util.List;

public interface IManageTrips {

    public void addTrip(String destination, String departure,String price, String capacity, String departureTime) throws ParseException;

    public List<BusDTO> getTrips(String start, String end) throws ParseException;

    public List<BusDTO> getTrips();

    public List<Integer> getSeats(int id);

    public void buyTicket(String busId,String seat,String user);

    public List<BusDTO> getTripsUser(String email);

    public List<ClientDTO> getTripsPassenger(int tripId);

    public List<BusDTO> getTripsOnDate(String date) throws ParseException;

    public void deleteTrip(String tripId);
}
