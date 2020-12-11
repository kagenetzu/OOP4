package com.company;

public abstract class Workers {
    private static int count = 0;
    protected int id;
    protected int salary;
    protected String name;
    protected int workingHourse;
    protected String function;
    public Workers(String name,int salary,int workingHourse, String function){
        this.id = count;
        count++;
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

    public abstract void show();
}
