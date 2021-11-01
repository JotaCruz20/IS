package data;


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String email;
    private String name;
    private String password;
    private Date birthdate;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Users(String email, String name, String password, Date birthdate) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthdate = birthdate;
    }

    public Users(){
        super();
    }
}
