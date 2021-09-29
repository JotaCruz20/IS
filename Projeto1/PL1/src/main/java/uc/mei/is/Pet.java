package uc.mei.is;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class Pet {
    private long id;
    private String name;
    private String specie;
    private String gender;
    private Date birthdate;
    private long weight;
    private String description;

    public Pet(long id, String name, String specie, String gender, Date birthdate, long weight, String description) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.gender = gender;
        this.birthdate = birthdate;
        this.weight = weight;
        this.description = description;
    }

    public Pet(){}

    @XmlElement
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    @XmlElement
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @XmlElement
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @XmlElement
    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
