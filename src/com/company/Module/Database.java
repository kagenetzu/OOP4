package com.company.Module;

import java.util.ArrayList;

public class Database {
    public ArrayList<Workers> info = new ArrayList();
    public void add(Workers worker){
        this.info.add(worker);
    }

    public String print(){
        String result = "";
        for (Workers worker : info) {
            result += worker.show() + "\n";
        }
        return result;
    }

    public int getSize(){
        return info.size();
    }
    public String[][] printTable(){
        String [][] data = new String[getSize()][7];
        int j = 0;
        for (Workers worker : info) {
            if(worker instanceof JustWorker) {
                data[j][0] = String.valueOf(worker.getId());
                data[j][1] = worker.getName();
                data[j][2] = String.valueOf(worker.getSalary());
                data[j][3] = String.valueOf(worker.getWorkingHourse());
                data[j][4] = "Рабочий";
                data[j][5] = "-";
                data[j][6] = "-";
                j++;
            }
            else{
                if(worker instanceof Engineer) {
                    data[j][0] = String.valueOf(worker.getId());
                    data[j][1] = worker.getName();
                    data[j][2] = String.valueOf(worker.getSalary());
                    data[j][3] = String.valueOf(worker.getWorkingHourse());
                    data[j][4] = "Инженер";
                    data[j][5] = ((Engineer) worker).getRank();
                    data[j][6] = "-";
                    j++;
                }else{
                    if(worker instanceof Administration) {
                        data[j][0] = String.valueOf(worker.getId());
                        data[j][1] = worker.getName();
                        data[j][2] = String.valueOf(worker.getSalary());
                        data[j][3] = String.valueOf(worker.getWorkingHourse());
                        data[j][4] = "Управляющий";
                        data[j][5] = "-";
                        data[j][6] = String.valueOf(((Administration) worker).getSubordinates());
                        j++;
                    }
                }
            }
        }
        return data;

    }


    public String printAdministration() {
        String result = "Управляющих нет.";
        for (Workers worker : info) {
            if(worker instanceof Administration) {
                result += worker.show() + "\n";
            }
        }
        return result;
    }

    public  String search(int id) {
        boolean b = false;
        String result ="";
        for (Workers worker : info) {
            if (id == worker.id) {
                b = true;
                result += worker.show();
            }
        }
        if (b == false) {
            result = ("Такого номера нет.");
        }
        return  result;
    }

    public void delete(int id){
        boolean b = false;
        for (Workers worker : info) {
            if (id == worker.getId()){
                b = true;
            }
        }
        if (b == false){
//            System.out.println("Такого человека в базе данных не существует.");
        }else {
            info.removeIf(worker -> worker.id == id);
        }

    }

}
