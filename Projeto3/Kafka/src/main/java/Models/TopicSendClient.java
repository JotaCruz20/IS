package Models;

public class TopicSendClient {
    private double exchangeRate;
    private double value;

    public TopicSendClient(double exchangeRate, double value) {
        this.exchangeRate = exchangeRate;
        this.value = value;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TopicSendClient{" +
                "exchangeRate=" + exchangeRate +
                ", value=" + value +
                '}';
    }
}
