package com.maistruk.hibernate_1.oneToMany;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "laptop_one_to_many")
public class LaptopOneToMany {

    @Id
    private int id;
    private String name;
    @ManyToOne
    private StudentOneToMany student;

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

    public StudentOneToMany getStudent() {
        return student;
    }

    public void setStudent(StudentOneToMany student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "LaptopOneToMany [id=" + id + ", name=" + name + "]";
    }

}
