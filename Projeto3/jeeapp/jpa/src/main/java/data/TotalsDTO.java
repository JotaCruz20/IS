package data;


import java.io.Serializable;

public class TotalsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private double credit;
    private double payment;
    private double balance;

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

    public TotalsDTO(double credit, double payment, double balance) {
        this.credit = credit;
        this.payment = payment;
        this.balance = balance;
    }

    public TotalsDTO(){}

    @Override
    public String toString() {
        return "Total Credit=" + credit +
                "\n Total Payment=" + payment +
                "\n Total Balance=" + balance;
    }
}
