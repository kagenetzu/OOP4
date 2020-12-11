package com.company;

import java.util.ArrayList;

public class Database {
    private ArrayList<Workers> info = new ArrayList();
    public void add(Workers worker){
        this.info.add(worker);

    }
    public void print(){
        for (Workers worker : info) {
            worker.show();
        }
    }
    public int getSize(){
        return info.size();
    }


    public void printAdministration() {
        for (Workers worker : info) {
            if(worker instanceof Administration) {
                worker.show();

            }
        }
    }
    public  void search(int id) {
        boolean b = false;
        for (Workers worker : info) {
            if (id == worker.id) {
                b = true;
                worker.show();
            }
        }
        if (b == false) {
            System.out.println("Такого номера нет.");

        }
    }

    public void delete(int id){
        boolean b = false;
        for (Workers worker : info) {
            if (id == worker.getId()){
                b = true;
            }
        }
        if (b == false){
            System.out.println("Такого человека в базе данных не существует.");
        }else {
            info.removeIf(worker -> worker.id == id);
        }

    }

}
