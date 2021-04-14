package com.company.View;

import com.company.Module.Administration;
import com.company.Module.Database;
import com.company.Module.Engineer;
import com.company.Module.JustWorker;

import java.util.Scanner;

public class Menu {
    private static Scanner in = new Scanner(System.in);
    private static Database data;

    static {
        data = new Database();
        data.add(new JustWorker("Владимир",12000,5,"Рабочий"));
        data.add(new Administration("Мария",90000,9,"Упраляющий",5));
        data.add(new Engineer("Сергеевич", 50000,12,"Инженер","Первый"));
        data.add(new Administration("Валера",44000,7,"Упраляющий",3));
        data.add(new JustWorker("Николай",15000,7,"Рабочий"));


    }

    public static void Menu(){
        int choice = -1;
        do{
            System.out.println("1. Вывести базу данных о сотрудниках.");
            System.out.println("2. Добавить рабочего в базу данных.");
            System.out.println("3. Добавить инженера в базу данных.");
            System.out.println("4. Добавить управляющего в базу данных.");
            System.out.println("5. Вывести информацию только о управляющих");
            System.out.println("6. Удалить человека из базы данных.");
            System.out.println("7. Поиск по имени человека.");
            System.out.println("0. Выход.");
            choice = read();
            switch (choice){
                case 1:
                    System.out.println("-------------------------------");
                    if(data.getSize() > 0) {
                        data.print();
                    }
                    else{
                        System.out.println("База данных пуста.");
                    }
                    System.out.println("-------------------------------");
                    break;
                case 2:
                    System.out.print("Имя рабочего: ");
                    String name = in.nextLine();
                    System.out.print("Заработная плата: ");
                    int salary= checkSalary();
                    System.out.print("Время работы: ");
                    int workingHourse = check(1,23);
                    String typeWork = "Рабочий";
                    data.add(new JustWorker(name,salary,workingHourse,typeWork));
                    System.out.println("Рабочий добавлен.");
                    break;
                case 3:
                    System.out.print("Имя инженера: ");
                    name = in.nextLine();
                    System.out.print("Заработная плата: ");
                    salary= checkSalary();
                    System.out.print("Время работы: ");
                    workingHourse = check(1,23);
                    typeWork = "Инженер";
                    System.out.print("Разряд инженера: ");
                    String rank = in.nextLine();
                    data.add(new Engineer(name,salary,workingHourse,typeWork,rank));
                    System.out.println("Инженер добавлен.");
                    break;
                case 4:
                    System.out.print("Имя управляющего: ");
                    name = in.nextLine();
                    System.out.print("Заработная плата: ");
                    salary = checkSalary();
                    System.out.print("Время работы: ");
                    workingHourse = check(1,23);
                    typeWork = "Управляющий";
                    System.out.print("Кол-во подчиненных: ");
                    int subordinates = check(1,30);
                    data.add(new Administration(name,salary,workingHourse,typeWork,subordinates));
                    System.out.println("Управляющий добавлен.");
                    break;
                case 5:
                    System.out.println("Вывод информации только о управляющих.");
                    data.printAdministration();
                    break;
                case 6:
                    System.out.print("Введите номер в базе данных: ");
                    int id = read();
                    data.delete(id);
                    break;
                case 7:
                    System.out.println("Введите номер: ");
                    id = read();
                    data.search(id);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Вы ввели неверный номер.");
                    break;
            }

        }while (choice != 0);



    }

    private static int read() {
        int readed = -1;
        try{
            readed = Integer.parseInt(in.nextLine());
        }
        catch (NumberFormatException ex){
            System.out.println("Введите только цифры.");
            readed = read();
        }
        return readed;
    }
    private static int checkSalary(){
        int salary;
        do {
            salary= read();
            if(salary < 1){
                System.out.println("Зарплата не может быть меньше нуля.");

            }
        }while (salary < 1);
        return salary;
    }
    private static int check(int k, int m){
        int value;
        do {
            value= read();
            if(value < k || value > m){
                System.out.println("Значение не может быть меньше "+ k + " и больше "+ m);

            }
        }while (value < k || value > m);
        return value;
    }


}
