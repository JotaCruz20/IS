package data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends Users{
    @Column(precision = 3)
    private double wallet;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Client(String email, String name, String password, Date birthdate) {
        super(email, name, password, birthdate);
        this.wallet = 0.0;
    }

    public Client(){
        super();
    }
}
