package com.maistruk.hibernate_1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity    //Default entity name is "alien" and we will save date in SQL in table "alien"
@Table(name="alien_table1")    //Change table name on "alien_table" and we will save date in SQL in table "alien_table"
//@Entity(name="alien_table")    //Change entity name on "alien_table" and we will save date in SQL in table "alien_table"
public class Alien {
    
    @Id
    private int id;
    //@Transient   //Fields that are marked as Transient will not be save in SQL
    private String name;
    @Column(name="alien_color")    //Change column name in SQL on "alien_color"
    private String color;
    
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
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString() {
        return "Alien [id=" + id + ", name=" + name + ", color=" + color + "]";
    }
    
    

}
