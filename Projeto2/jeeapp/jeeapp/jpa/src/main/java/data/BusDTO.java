package data;

import java.time.LocalDateTime;

public class BusDTO {
    private int id;
    private String departurePoint;
    private String destination;
    private LocalDateTime departureTime;
    private int capacity;
    private int place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public BusDTO(int id,String departurePoint, String destination, LocalDateTime departureTime, int capacity) {
        this.id = id;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.departureTime = departureTime;
        this.capacity = capacity;
    }

    public BusDTO(int id,String departurePoint, String destination, LocalDateTime departureTime, int capacity, int place) {
        this.id = id;
        this.departurePoint = departurePoint;
        this.destination = destination;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.place = place;
    }
}
