package com.maistruk.hibernate_1.manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.maistruk.hibernate_1.oneToMany.LaptopOneToMany;

@Entity
@Table(name = "student_many_to_many")
public class StudentManyToMany {

    @Id
    private int id;
    private String name;
    private int marks;
    @ManyToMany(mappedBy = "students")
    private List<LaptopManyToMany> laptops = new ArrayList<>();

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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public List<LaptopManyToMany> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopManyToMany> laptops) {
        this.laptops = laptops;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", marks=" + marks + "]";
    }

}
