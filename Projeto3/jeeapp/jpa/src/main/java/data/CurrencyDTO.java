package data;


import java.io.Serializable;

public class CurrencyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double exchangeRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public CurrencyDTO(String name, double exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public CurrencyDTO(){}

    @Override
    public String toString() {
        return "Name=" + name +
                ", Exchange Rate=" + exchangeRate;
    }
}
