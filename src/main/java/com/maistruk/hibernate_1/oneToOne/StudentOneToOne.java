package com.maistruk.hibernate_1.oneToOne;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_one_to_one")
public class StudentOneToOne {

    @Id
    private int id;
    private String name;
    private int marks;
    @OneToOne
    private LaptopOneToOne laptop;

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

    public LaptopOneToOne getLaptop() {
        return laptop;
    }

    public void setLaptop(LaptopOneToOne laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", marks=" + marks + "]";
    }

}
