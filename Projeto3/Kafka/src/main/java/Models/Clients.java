package Models;

public class Clients {
    private int id;
    private String name;
    private double credit;
    private double payment;

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

    public Clients(int id, String name, double credit, double payment) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.payment = payment;
    }
}
