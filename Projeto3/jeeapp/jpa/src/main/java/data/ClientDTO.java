package data;


import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private double credit;
    private double payment;
    private double balance;
    private AdminDTO admin;
    private double lastMonthBill;

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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AdminDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
    }

    public double getLastMonthBill() {
        return lastMonthBill;
    }

    public void setLastMonthBill(double lastMonthBill) {
        this.lastMonthBill = lastMonthBill;
    }

    public ClientDTO(int id, String name, double credit, double payment, double balance, AdminDTO admin, double lastMonthBill) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.payment = payment;
        this.balance = balance;
        this.admin = admin;
        this.lastMonthBill = lastMonthBill;
    }

    public ClientDTO(String name, AdminDTO admin) {
        this.name = name;
        this.admin = admin;
    }

    public ClientDTO(){};

    @Override
    public String toString() {
        return "Name=" + name + '\'' +
                ", credit=" + credit +
                ", payment=" + payment +
                ", balance=" + balance;
    }
}
