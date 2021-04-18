package com.company.Controller;

import com.company.Module.Administration;
import com.company.Module.Database;
import com.company.Module.Engineer;
import com.company.Module.JustWorker;
import com.company.View.GMenu;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Functional extends JFrame {

    public static Database data;
    private static JTextArea dataWorkers = new JTextArea();

    static {
        data = new Database();
//        data.add(new JustWorker("Владимир", 12000, 5, "Рабочий"));
//        data.add(new Administration("Мария", 90000, 9, "Управляющий", 5));
//        data.add(new Engineer("Сергеевич", 50000, 12, "Инженер", "Первый"));
//        data.add(new Administration("Валера", 44000, 7, "Упраляющий", 3));
//        data.add(new JustWorker("Николай", 15000, 7, "Рабочий"));
        try {
            BDWorkers.connectionBDStud();
            BDWorkers.createTable();
            BDWorkers.readDBWORKER();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    protected void showAdministation() {
        setBounds(10, 10, 500, 500);
        setTitle("Только администрация");
        setResizable(false);
        getContentPane().setLayout(null);

        dataWorkers.setEditable(false);
        dataWorkers.setText(data.printAdministration());

        JScrollPane scroll = new JScrollPane(dataWorkers);
        scroll.setBounds(10, 11, 400, 400);

        getContentPane().add(scroll);
        setVisible(true);
    }
}

class JustWorkerAdd extends JFrame {

    public JLabel LNameJustWorker = new JLabel("Имя рабочего");
    public static JTextField TNameJustWorker = new JTextField();
    public JLabel LSalary = new JLabel("Заработная плата");
    public static JTextField TSalary = new JTextField();
    public JLabel LWorkingHourse = new JLabel("Время работы");
    public static JTextField TWorkingHourse = new JTextField();

    public JButton addJustWorker = new JButton("Добавить рабочего в список");

    public JustWorkerAdd() {
        justWorkerInit();
        setTitle("Сотрудник");
        setVisible(true);
        setSize(270, 400);
        setResizable(false);
    }

    public static void add() {
        try {
//            Functional.data.add(new JustWorker(
//                    JustWorkerAdd.getTNameJustWorker().getText(),
//                    Integer.parseInt(JustWorkerAdd.getTSalary().getText()),
//                    Integer.parseInt(JustWorkerAdd.getTWorkingHourse().getText()),
//                    "Рабочий"));

            try {
                BDWorkers.writeDBWORKER(JustWorkerAdd.getTNameJustWorker().getText(),
                        Integer.parseInt(JustWorkerAdd.getTSalary().getText()),
                        Integer.parseInt(JustWorkerAdd.getTWorkingHourse().getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Сотрудник добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            JustWorkerAdd.TNameJustWorker.setText("");
            JustWorkerAdd.TSalary.setText("");
            JustWorkerAdd.TWorkingHourse.setText("");
            GMenu.jTable.setModel(new MyTable());


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void justWorkerInit() {
        AddListener workerListener = new AddListener();
        setLayout(new GridLayout(11, 1));
        add(LNameJustWorker);
        add(TNameJustWorker);
        add(LSalary);
        add(TSalary);
        add(LWorkingHourse);
        add(TWorkingHourse);
        addJustWorker.addActionListener(workerListener);
        add(addJustWorker);
    }

    public static JTextField getTWorkingHourse() {
        return TWorkingHourse;
    }

    public static JTextField getTNameJustWorker() {
        return TNameJustWorker;
    }

    public static JTextField getTSalary() {
        return TSalary;
    }

}

class menuSearch extends JFrame {

    public static JTextField Tid = new JTextField();
    public static JTextArea dataWorkers = new JTextArea();
    public JButton searchButton = new JButton("Найти сотрудника");
    public JButton delButton = new JButton("Удалить сотрудника");

    public menuSearch() {
        menuSearchInit();
        setSize(400, 300);
        setTitle("Поиск");
        setVisible(true);
        setResizable(false);
    }

    private void menuSearchInit() {
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        pane.setLayout(new GridBagLayout());
        JLabel Lid = new JLabel("Введите номер сотрудника");
        dataWorkers = new JTextArea();
        dataWorkers.setEditable(false);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets.top = 5;
        pane.add(Lid, c);

        Tid = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.insets.top = 0;
        pane.add(Tid, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(searchButton, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(delButton, c);


        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(dataWorkers, c);

        searchButton.addActionListener(new AddListener());
        delButton.addActionListener(new AddListener());

        add(pane);
        setVisible(true);
    }

    protected static String search() {
        int id = 0;
        String result = "";
        try {
            id = Integer.parseInt(menuSearch.getTid().getText());
            result = Functional.data.search(id);

        } catch (NumberFormatException ex) {
            result = "Такого номера не существует.";

        }
        return result;
    }

    protected static String del() {
        int id = 0;
        String result = "";

        try {
            id = Integer.parseInt(menuSearch.getTid().getText());
            Functional.data.delete(id);
            BDWorkers.deleteWorkers(id);
            result = "Сотрудник удален.";
        } catch (NumberFormatException ex) {
            result = "Неверный номер.";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Tid.setText("");

        return result;
    }

    public static JTextField getTid() {
        return Tid;
    }


}

class EngineerAdd extends JFrame {

    public JLabel LNameEngineer = new JLabel("Имя инженера");
    public static JTextField TNameEngineer = new JTextField();
    public JLabel LSalary = new JLabel("Заработная плата");
    public static JTextField TSalary = new JTextField();
    public JLabel LWorkingHourse = new JLabel("Время работы");
    public static JTextField TWorkingHourse = new JTextField();
    public JLabel LRank = new JLabel("Ранг инженера");
    public static JTextField TRank = new JTextField();

    public JButton addEngineer = new JButton("Добавить инженера в список");

    public EngineerAdd() {
        engineerInit();
        setTitle("Инженер");
        setVisible(true);
        setSize(270, 400);
        setResizable(false);
    }

    private void engineerInit() {
        AddListener workerListener = new AddListener();
        setLayout(new GridLayout(11, 1));
        add(LNameEngineer);
        add(TNameEngineer);
        add(LSalary);
        add(TSalary);
        add(LWorkingHourse);
        add(TWorkingHourse);
        add(LRank);
        add(TRank);
        addEngineer.addActionListener(workerListener);
        add(addEngineer);
    }

    public static void add() {
        try {
//            Functional.data.add(new Engineer(
//                    EngineerAdd.getTNameEngineer().getText(),
//                    Integer.parseInt(EngineerAdd.getTSalary().getText()),
//                    Integer.parseInt(EngineerAdd.getTWorkingHourse().getText()),
//                    "Инженер", EngineerAdd.getTRank().getText()));
            try {
                BDWorkers.writeDBENGINEER(EngineerAdd.getTNameEngineer().getText(),
                        Integer.parseInt(EngineerAdd.getTSalary().getText()),
                        Integer.parseInt(EngineerAdd.getTWorkingHourse().getText()),EngineerAdd.getTRank().getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Инженер добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            EngineerAdd.TNameEngineer.setText("");
            EngineerAdd.TSalary.setText("");
            EngineerAdd.TWorkingHourse.setText("");
            EngineerAdd.TRank.setText("");
            GMenu.jTable.setModel(new MyTable());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        }
    }


    public static JTextField getTWorkingHourse() {
        return TWorkingHourse;
    }

    public static JTextField getTRank() {
        return TRank;
    }

    public static JTextField getTNameEngineer() {
        return TNameEngineer;
    }

    public static JTextField getTSalary() {
        return TSalary;
    }

}

class AdministrationAdd extends JFrame {

    public JLabel LNameAdministration = new JLabel("Имя управляющего");
    public static JTextField TNameAdministration = new JTextField();
    public JLabel LSalary = new JLabel("Заработная плата");
    public static JTextField TSalary = new JTextField();
    public JLabel LWorkingHourse = new JLabel("Время работы");
    public static JTextField TWorkingHourse = new JTextField();
    public JLabel LSubordinates = new JLabel("Кол-во подчиненных");
    public static JTextField TSubordinates = new JTextField();

    public JButton addAdministation = new JButton("Добавить управляющего в список");

    public AdministrationAdd() {
        adminInit();
        setTitle("Управляющий");
        setVisible(true);
        setSize(270, 400);
        setResizable(false);
    }

    private void adminInit() {
        AddListener workerListener = new AddListener();
        setLayout(new GridLayout(11, 1));
        add(LNameAdministration);
        add(TNameAdministration);
        add(LSalary);
        add(TSalary);
        add(LWorkingHourse);
        add(TWorkingHourse);
        add(LSubordinates);
        add(TSubordinates);
        addAdministation.addActionListener(workerListener);
        add(addAdministation);
    }

    public static void add() {
        try {
//            Functional.data.add(new Administration(
//                    AdministrationAdd.getTNameAdministration().getText(),
//                    Integer.parseInt(AdministrationAdd.getTSalary().getText()),
//                    Integer.parseInt(AdministrationAdd.getTWorkingHourse().getText()),
//                    "Управляющий", Integer.parseInt(AdministrationAdd.getTSubordinates().getText())));
            try {
                BDWorkers.writeDBADMIN(AdministrationAdd.getTNameAdministration().getText(),
                        Integer.parseInt(AdministrationAdd.getTSalary().getText()),
                        Integer.parseInt(AdministrationAdd.getTWorkingHourse().getText()),Integer.parseInt(AdministrationAdd.getTSubordinates().getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Управляющий добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            AdministrationAdd.TNameAdministration.setText("");
            AdministrationAdd.TSalary.setText("");
            AdministrationAdd.TWorkingHourse.setText("");
            AdministrationAdd.TSubordinates.setText("");
            GMenu.jTable.setModel(new MyTable());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static JTextField getTWorkingHourse() {
        return TWorkingHourse;
    }

    public static JTextField getTSubordinates() {
        return TSubordinates;
    }

    public static JTextField getTNameAdministration() {
        return TNameAdministration;
    }

    public static JTextField getTSalary() {
        return TSalary;
    }

}