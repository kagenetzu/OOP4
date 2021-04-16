package com.company.Module;

import com.company.Controller.BDWorkers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Workers {
    protected int id;
    protected int salary;
    protected String name;
    protected int workingHourse;
    protected String function;
    public Workers(int id,String name,int salary,int workingHourse, String function){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.workingHourse = workingHourse;
        this.function = function;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getWorkingHourse() {
        return workingHourse;
    }

    public String getFunction() {
        return function;
    }

    public abstract String show();
}
