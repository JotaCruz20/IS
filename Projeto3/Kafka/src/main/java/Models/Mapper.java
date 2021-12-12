package Models;

public class Mapper {
    String key;
    double value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Mapper(String key, double value) {
        this.key = key;
        this.value = value;
    }

    public Mapper() {
    }
}
