package uc.mei.is;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Owner {
    private long id;
    private String name;
    private String address;
    private Date birthdate;
    private String telephone;
    private ArrayList<Pet> pets;


    public Owner() {
    }

    public Owner(long id, String name, String address, Date birthdate, String telephone, ArrayList<Pet> pets) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.pets = pets;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public ArrayList<Pet> getPets() {
        return pets;
    }

    @XmlElement
    public Date getBirthdate() {
        return birthdate;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    @XmlElement
    public String getTelephone() {
        return telephone;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}