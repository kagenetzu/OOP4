package com.company.Module;

public class JustWorker extends Workers {

    public  JustWorker(int id,String name, int salary, int workingHouse, String function){
        super(id,name,salary,workingHouse,function);

    }

    @Override
    public String show() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Номер в базе данных: "+this.id + "\n");
        stringBuilder.append("Имя: " + getName() + "\n");
        stringBuilder.append("Заработная плата: " + getSalary() + "\n");
        stringBuilder.append("Кол-во часов работы: " + getWorkingHourse() + "\n");
        stringBuilder.append("Задача: " + getFunction() + "\n");
        return (stringBuilder.toString());
    }



}
