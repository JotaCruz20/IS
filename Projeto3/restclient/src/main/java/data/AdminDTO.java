package data;

import java.io.Serializable;

public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public AdminDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AdminDTO(String name) {
        this.name = name;
    }

    public AdminDTO(){};

    @Override
    public String toString() {
        return "Name: " + name;
    }
}
