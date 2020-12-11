package com.company;

public class JustWorker extends Workers {

    public  JustWorker(String name, int salary, int workingHouse, String function){
        super(name,salary,workingHouse,function);

    }

    @Override
    public void show() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Номер в базе данных: "+this.id + "\n");
        stringBuilder.append("Имя: " + getName() + "\n");
        stringBuilder.append("Заработная плата: " + getSalary() + "\n");
        stringBuilder.append("Кол-во часов работы: " + getWorkingHourse() + "\n");
        stringBuilder.append("Задача: " + getFunction() + "\n");
        System.out.println(stringBuilder.toString());
    }



}
