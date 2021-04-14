package com.company.Module;

public class Administration extends Workers {
    protected int subordinates;

    public  Administration(String name, int salary, int workingHourse, String function, int subordinates){
        super(name,salary,workingHourse,function);
        this.subordinates = subordinates;
    }

    @Override
    public String show() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Номер в базе данных: "+this.id + "\n");
        stringBuilder.append("Имя: " + getName() + "\n");
        stringBuilder.append("Заработная плата: " + getSalary() + "\n");
        stringBuilder.append("Кол-во часов работы: " + getWorkingHourse() + "\n");
        stringBuilder.append("Задача: " + getFunction() + "\n");
        stringBuilder.append("Кол-во подчиненных в управлении: "+ getSubordinates() + "\n");
        return (stringBuilder.toString());
    }

    public int getSubordinates() {
        return subordinates;
    }
}
