package data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends Users{
    private long wallet;

    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
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
        this.wallet = 0;
    }

    public Client(){
        super();
    }
}
