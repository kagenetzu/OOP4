package com.company.Controller;

import com.company.Module.Administration;
import com.company.Module.Engineer;
import com.company.Module.JustWorker;
import java.sql.*;

public class BDWorkers {
    public static Connection connection; //Для соединения с БД необходимо использовать класс Connection пакета java.sql.

    public static Statement statement; //используется для выполнения SQL-запросов

    public static ResultSet result;//представляет результирующий набор данных и обеспечивает приложению построчный доступ к результатам запросов


    public static void connectionBDStud() throws ClassNotFoundException, SQLException {

        connection = null;

        Class.forName("org.sqlite.JDBC"); //("имя движка") вызывает динамическую загрузку класса, org.sqlite.JDBC принадлежит к jar sqlite-jdbc

        connection = DriverManager.getConnection("jdbc:sqlite:DBworkers.db"); //("протокол:движок:имя_файла_БД")находит java.sql.Driver соответствующей базы данных и вызывает у него метод connect (метод connect всегда создает базу данных заранее)

        System.out.println("БД подключена!");

    }
    public static void createTable() throws ClassNotFoundException, SQLException{

        statement = connection.createStatement();//создание экземпляра класса Statement

        statement.execute("CREATE TABLE if not exists 'workers' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'salary' integer,'workingHours' integer,  'function' text, 'rank' text, 'subordinates' integer);");// позволяет выполнять различные статичные SQL запросы, используется, когда операторы SQL возвращают более одного набора данных, более одного счетчика обновлений или и то, и другое

        System.out.println("Таблица созданы.");

    }
    public static void writeDBWORKER(String name, int salary, int workingHours) throws SQLException{

        PreparedStatement a = connection.prepareStatement("INSERT INTO 'workers' ('name','salary','workingHours','function','rank','subordinates') VALUES (?,?,?,?,?,?); ");
        a.setObject(1,name);
        a.setObject(2,salary);
        a.setObject(3,workingHours);
        a.setObject(4,"Рабочий");
        a.setObject(5,"-");
        a.setObject(6,"-");

        a.execute();
        result = statement.executeQuery("SELECT * FROM workers ORDER BY id DESC LIMIT 1");
        Functional.data.add(new JustWorker(result.getInt("id") ,result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function")));


    }
    public static void writeDBENGINEER(String name, int salary, int workingHours,String rank) throws SQLException{

        PreparedStatement a = connection.prepareStatement("INSERT INTO 'workers' ('name','salary','workingHours','function','rank','subordinates') VALUES (?,?,?,?,?,?); ");
        a.setObject(1,name);
        a.setObject(2,salary);
        a.setObject(3,workingHours);
        a.setObject(4,"Инженер");
        a.setObject(5,rank);
        a.setObject(6,"-");
        a.execute();
        result = statement.executeQuery("SELECT * FROM workers ORDER BY id DESC LIMIT 1");
        Functional.data.add( new Engineer(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getString("rank")));



    }
    public static void writeDBADMIN(String name, int salary, int workingHours,int subordinates) throws SQLException{

        PreparedStatement a = connection.prepareStatement("INSERT INTO 'workers' ('name','salary','workingHours','function','rank','subordinates') VALUES (?,?,?,?,?,?); ");
        a.setObject(1,name);
        a.setObject(2,salary);
        a.setObject(3,workingHours);
        a.setObject(4,"Управляющий");
        a.setObject(5,"-");
        a.setObject(6,subordinates);
        a.execute();
        result = statement.executeQuery("SELECT * FROM workers ORDER BY id DESC LIMIT 1");
        Functional.data.add(new Administration(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getInt("subordinates")));

    }
    public static void sort(int i) throws SQLException {
        if(i == 0) {
            result = statement.executeQuery("SELECT * FROM workers ORDER BY salary ");
        }else{
            if (i == 1){
                result = statement.executeQuery("SELECT * FROM workers ORDER BY workingHours ");
            }else{
                result = statement.executeQuery("SELECT * FROM workers ORDER BY subordinates ");
            }
        }


        while(result.next())
        {
            if(result.getString("function").equals("Рабочий")) {
                JustWorker worker = new JustWorker(result.getInt("id") ,result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"));
                Functional.data.add(worker);
            }
            else if (result.getString("function").equals("Инженер")){
                Engineer engineer = new Engineer(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getString("rank"));
                Functional.data.add(engineer);
            }
            else {
                Administration admin = new Administration(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getInt("subordinates"));
                Functional.data.add(admin);
            }
        }
    }
    public static void filtr(String value) throws SQLException {

        PreparedStatement a = connection.prepareStatement("SELECT * FROM workers WHERE name = ? OR  function = ? OR rank = ?");

        a.setObject(1, value);
        a.setObject(2,value);
        a.setObject(3,value);

        result = a.executeQuery();

        while(result.next())
        {
            if(result.getString("function").equals("Рабочий")) {
                JustWorker worker = new JustWorker(result.getInt("id") ,result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"));
                Functional.data.add(worker);
            }
            else if (result.getString("function").equals("Инженер")){
                Engineer engineer = new Engineer(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getString("rank"));
                Functional.data.add(engineer);
            }
            else {
                Administration admin = new Administration(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getInt("subordinates"));
                Functional.data.add(admin);
            }
        }
    }

    public static void readDBWORKER() throws ClassNotFoundException, SQLException
    {

        result = statement.executeQuery("SELECT * FROM workers"); //выборки данных с помощью команды SELECT

        while(result.next())
        {
            if(result.getString("function").equals("Рабочий")) {
                JustWorker worker = new JustWorker(result.getInt("id") ,result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"));
                Functional.data.add(worker);
            }
            else if (result.getString("function").equals("Инженер")){
                Engineer engineer = new Engineer(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getString("rank"));
                Functional.data.add(engineer);
            }
            else {
                Administration admin = new Administration(result.getInt("id"),result.getString("name"), result.getInt("salary"), result.getInt("workingHours"), result.getString("function"),result.getInt("subordinates"));
                Functional.data.add(admin);
            }
        }

        System.out.println("Таблица выгружена");
    }
    public static void deleteWorkers(int number) throws SQLException {

        PreparedStatement a = connection.prepareStatement("DELETE FROM workers WHERE id = ?");
        a.setObject(1,number);
        a.execute();

    }

    public static void closeDB() throws SQLException {
        connection.close();

        statement.close();

        result.close();

        System.out.println("Соединения закрыты");
    }
}
