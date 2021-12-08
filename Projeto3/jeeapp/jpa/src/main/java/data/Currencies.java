package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Currencies implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private String name;

    @Column
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

    public Currencies(String name, double exchangeRate) {
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public Currencies() {
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "name='" + name + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
