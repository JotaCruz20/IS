package data;

import java.util.Date;

public class ClientDTO {
    private String email;
    private String name;
    private String birthdate;

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

    public ClientDTO(String email, String name, String birthdate) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
    }
}
