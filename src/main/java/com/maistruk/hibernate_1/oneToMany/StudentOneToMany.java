package com.maistruk.hibernate_1.oneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_one_to_many")
public class StudentOneToMany {

    @Id
    private int id;
    private String name;
    private int marks;
    @OneToMany(mappedBy = "student", fetch=FetchType.EAGER)
    private List<LaptopOneToMany> laptops = new ArrayList<>();

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

    public List<LaptopOneToMany> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopOneToMany> laptops) {
        this.laptops = laptops;
    }

    @Override
    public String toString() {
        return "StudentOneToMany [id=" + id + ", name=" + name + ", marks=" + marks + ", laptops=" + laptops + "]";
    }

}
