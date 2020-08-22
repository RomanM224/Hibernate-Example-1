package com.maistruk.hibernate_1.manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.maistruk.hibernate_1.oneToMany.StudentOneToMany;

@Entity
@Table(name = "laptop_many_to_many")
public class LaptopManyToMany {

    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<StudentManyToMany> students = new ArrayList<>();

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

    public List<StudentManyToMany> getStudents() {
        return students;
    }

    public void setStudents(List<StudentManyToMany> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Laptop [id=" + id + ", name=" + name + "]";
    }

}
