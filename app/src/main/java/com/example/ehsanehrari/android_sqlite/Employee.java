package com.example.ehsanehrari.android_sqlite;


public class Employee {

    private int id;
    private String name;
    private String lastname;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Employee(){}

    public Employee(String name, String lastname) {
        super();
        this.name = name;
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", lastname=" + lastname
                + "]";
    }
}