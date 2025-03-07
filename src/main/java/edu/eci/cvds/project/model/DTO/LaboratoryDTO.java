package edu.eci.cvds.project.model.DTO;

import java.io.Serializable;

public class LaboratoryDTO implements Serializable{
    private String name;
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
     public String getName() {
        return name;
     }
     public void setName(String name) {
        this.name = name;
     }
}

