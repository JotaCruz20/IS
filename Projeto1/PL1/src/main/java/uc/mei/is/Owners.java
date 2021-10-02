package uc.mei.is;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Owners {
    private Owner[] owners;

    public Owners(Owner[] owners) {
        this.owners = owners;
    }

    public Owners(){}

    @XmlElement
    public Owner[] getOwners() {
        return owners;
    }

    public void setOwners(Owner[] owners) {
        this.owners = owners;
    }
}
