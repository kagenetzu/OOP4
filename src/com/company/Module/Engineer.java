package com.company.Module;

public class Engineer extends Workers {
    protected String rank;

    public  Engineer(int id,String name, int salary, int workingHourse, String function, String rank){
        super(id,name,salary,workingHourse,function);
        this.rank = rank;
    }
    @Override
    public String show() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Номер в базе данных: "+this.id + "\n");
        stringBuilder.append("Имя: " + getName() + "\n");
        stringBuilder.append("Заработная плата: " + getSalary() + "\n");
        stringBuilder.append("Кол-во часов работы: " + getWorkingHourse() + "\n");
        stringBuilder.append("Задача: " + getFunction() + "\n");
        stringBuilder.append("Разряд: "+ getRank()+ "\n");
        return (stringBuilder.toString());
    }

    public String getRank() {
        return rank;
    }
}
