package data;

import javax.persistence.*;

@Entity
public class Totals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private double credit;

    @Column
    private double payment;

    @Column
    private double balance;

    @ManyToOne
    private Client highestDebt;

    @ManyToOne
    private Admin highestRevenue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getHighestDebt() {
        return highestDebt;
    }

    public void setHighestDebt(Client highestDebt) {
        this.highestDebt = highestDebt;
    }

    public Admin getHighestRevenue() {
        return highestRevenue;
    }

    public void setHighestRevenue(Admin highestRevenue) {
        this.highestRevenue = highestRevenue;
    }

    public Totals() {
    }

    public Totals(double credit, double payment, double balance) {
        this.credit = credit;
        this.payment = payment;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Totals{" +
                "id=" + id +
                ", credit=" + credit +
                ", payment=" + payment +
                ", balance=" + balance +
                '}';
    }
}
