package data;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private double credit;

    @Column
    private double payment;

    @Column
    private double balance;

    @ManyToOne
    private Admin admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getBalance() {
        return balance;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client(int id, String name, double credit, double payment) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.payment = payment;
    }

    public Client(String name, double credit, double payment, double balance,Admin admin) {
        this.name = name;
        this.credit = credit;
        this.payment = payment;
        this.admin = admin;
        this.balance = balance;
    }

    public Client() {
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", payment=" + payment +
                ", balance=" + balance +
                '}';
    }
}
