package data;

public class ClientDTO {
    private String email;
    private String name;
    private String birthdate;
    private long wallet;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }

    public ClientDTO(String email, String name, String birthdate, long wallet) {
        this.wallet = wallet;
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
    }
}