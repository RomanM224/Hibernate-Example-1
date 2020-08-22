package com.maistruk.hibernate_1.oneToOne;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="laptop_one_to_one")
public class LaptopOneToOne {
    
    @Id
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
    
    @Override
    public String toString() {
        return "Laptop [id=" + id + ", name=" + name + "]";
    }
    
    

}
