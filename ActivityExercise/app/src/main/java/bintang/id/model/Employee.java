package bintang.id.model;

import java.io.Serializable;

/**
 * Created by bintang on 2/24/2016.
 */
public class Employee implements Serializable{


    private int nik;
    private String name;
    private String division;

    public Employee(){}

    public Employee(int nik, String name, String division) {
        super();
        this.nik = nik;
        this.name = name;
        this.division = division;
    }

    public int getNik() {
        return nik;
    }
    public void setNik(int nik) {
        this.nik = nik;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDivision() {
        return division;
    }
    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Employee [nik=" + nik + ", name=" + name + ", division=" + division + "]";
    }



}
