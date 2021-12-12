package Models;

public class TopicSendClient {
    private double exchangeRate;
    private double value;
    private int adminID;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public TopicSendClient(double exchangeRate, double value, int adminID) {
        this.exchangeRate = exchangeRate;
        this.value = value;
        this.adminID = adminID;
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
                ", adminID=" + adminID +
                '}';
    }
}
