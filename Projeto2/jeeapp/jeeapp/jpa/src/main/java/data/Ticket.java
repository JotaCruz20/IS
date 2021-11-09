package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private int place;

    @ManyToOne
    private Client client;

    @Column
    private Date buyDate;

    @ManyToOne
    private Bus bus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Ticket(int place, Client client, Date buyDate) {
        this.place = place;
        this.client = client;
        this.buyDate = buyDate;
    }

    public Ticket(){
    }
}
