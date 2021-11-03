package data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Manager extends Users{

    public Manager(String email, String name, String password, Date birthdate) {
        super(email, name, password, birthdate);
    }

    public Manager() {
    }

}
