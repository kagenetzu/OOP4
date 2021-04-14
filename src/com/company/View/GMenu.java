package com.company.View;

import com.company.Controller.*;
import com.company.Module.Administration;
import com.company.Module.Engineer;
import com.company.Module.JustWorker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class GMenu extends JFrame {


    public static JTable jTable;
    public JButton addJustWorker;
    public JButton addEngineer;
    public JButton addAdministration;
    private static JTextField TNameJW;
    private static JTextField TSalary;
    private static JTextField TWorkingH;
    private static JTextField TNameEngineer;
    private static JTextField TSalaryE;
    private static JTextField TWorkingHourseE;
    private static JTextField TRank;
    private static JTextField TNameAdministration;
    private static JTextField TSalaryA;
    private static JTextField TWorkingHourseA;
    private static JTextField TSubordinates;
    private Object[] columnusHeader = new String[]{"Номер", "Имя", "Зарплата", "Кол-во часов", "Должность", "Разряд", "Кол-во подчиненных"};

    public GMenu() {
        super("Сотрудники");
        setSize(1750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    BDWorkers.closeDB();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        JMenuBar mainMenu = new JMenuBar();
        mainMenu.add(menuAdd());
        mainMenu.add(menuAction());
        setJMenuBar(mainMenu);
        init();
        setResizable(false);
        setVisible(true);

    }

    private void init() {
        AddListener listener = new AddListener();

        jTable = new JTable();
        jTable.setModel(new MyTable());
        addJustWorker = new JButton("Добавление рабочего");
        addEngineer = new JButton("Добавление инженера");
        addAdministration = new JButton("Добавление управляющего");
        getContentPane().setLayout(null);
        JScrollPane scroll = new JScrollPane(jTable);
        scroll.setBounds(1, 1, 750, 400);
        addJustWorker.setBounds(40,400,200,50);
        addEngineer.setBounds(250,400,200,50);
        addAdministration.setBounds(460,400,200,50);
        getContentPane().add(scroll);
        //getContentPane().add(addJustWorker);
        //getContentPane().add(addEngineer);
        //getContentPane().add(addAdministration);

        JPanel workerk = new JPanel();
        workerk.setLayout(new GridLayout(11,1));
        JLabel labelWork = new JLabel("Рабочий");
        JLabel LNameJustWorker = new JLabel("Имя рабочего");
        TNameJW = new JTextField();
        JLabel LSalary = new JLabel("Заработная плата");
        TSalary = new JTextField();
        JLabel LWorkingHourse = new JLabel("Время работы");
        TWorkingH = new JTextField();
        workerk.add(labelWork);
        workerk.add(LNameJustWorker);
        workerk.add(TNameJW);
        workerk.add(LSalary);
        workerk.add(TSalary);
        workerk.add(LWorkingHourse);
        workerk.add(TWorkingH);
        workerk.add(addJustWorker);
        workerk.setBounds(800,1,250,400);
        getContentPane().add(workerk);

        JPanel engine = new JPanel();
        engine.setLayout(new GridLayout(11,1));
        JLabel labelEngine = new JLabel("Инженер");
        JLabel LNameEngineer = new JLabel("Имя инженера");
        TNameEngineer = new JTextField();
        LSalary = new JLabel("Заработная плата");
        TSalaryE = new JTextField();
        LWorkingHourse = new JLabel("Время работы");
        TWorkingHourseE = new JTextField();
        JLabel LRank = new JLabel("Ранг инженера");
        TRank = new JTextField();
        engine.add(labelEngine);
        engine.add(LNameEngineer);
        engine.add(TNameEngineer);
        engine.add(LSalary);
        engine.add(TSalaryE);
        engine.add(LWorkingHourse);
        engine.add(TWorkingHourseE);
        engine.add(LRank);
        engine.add(TRank);
        engine.add(addEngineer);
        engine.setBounds(1100,1,250,400);
        getContentPane().add(engine);

        JPanel admin = new JPanel();
        admin.setLayout(new GridLayout(11,1));
        JLabel labelAdmin = new JLabel("Управляющий");
        JLabel LNameAdministration = new JLabel("Имя управляющего");
        TNameAdministration = new JTextField();
        LSalary = new JLabel("Заработная плата");
        TSalaryA = new JTextField();
        LWorkingHourse = new JLabel("Время работы");
        TWorkingHourseA = new JTextField();
        JLabel LSubordinates = new JLabel("Кол-во подчиненных");
        TSubordinates = new JTextField();
        admin.add(labelAdmin);
        admin.add(LNameAdministration);
        admin.add(TNameAdministration);
        admin.add(LSalary);
        admin.add(TSalaryA);
        admin.add(LWorkingHourse);
        admin.add(TWorkingHourseA);
        admin.add(LSubordinates);
        admin.add(TSubordinates);
        admin.add(addAdministration);
        admin.setBounds(1400,1,250,400);
        getContentPane().add(admin);

        addJustWorker.addActionListener(listener);
        addEngineer.addActionListener(listener);
        addAdministration.addActionListener(listener);
    }
    public static void addJW() {
        try {
            Functional.data.add(new JustWorker(
                    TNameJW.getText(),
                    Integer.parseInt(TSalary.getText()),
                    Integer.parseInt(TWorkingH.getText()),
                    "Рабочий"));
            BDWorkers.writeDBWORKER(TNameJW.getText(),
                    Integer.parseInt(TSalary.getText()),
                    Integer.parseInt(TWorkingH.getText()));
            //JOptionPane.showMessageDialog(null, "Инженер добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            TNameJW.setText("");
            TSalary.setText("");
            TWorkingH.setText("");
            GMenu.jTable.setModel(new MyTable());

        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public static void addE() {
        try {
            Functional.data.add(new Engineer(
                    TNameEngineer.getText(),
                    Integer.parseInt(TSalaryE.getText()),
                    Integer.parseInt(TWorkingHourseE.getText()),
                    "Инженер",TRank.getText()));
            BDWorkers.writeDBENGINEER(TNameEngineer.getText(),
                    Integer.parseInt(TSalaryE.getText()),
                    Integer.parseInt(TWorkingHourseE.getText()), TRank.getText());
            //JOptionPane.showMessageDialog(null, "Инженер добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            TNameJW.setText("");
            TSalaryE.setText("");
            TWorkingHourseE.setText("");
            TRank.setText("");
            GMenu.jTable.setModel(new MyTable());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void addA() {
        try {
            Functional.data.add(new Administration(
                    TNameAdministration.getText(),
                    Integer.parseInt(TSalaryA.getText()),
                    Integer.parseInt(TWorkingHourseA.getText()),
                    "Управляющий",Integer.parseInt(TSubordinates.getText())));

                BDWorkers.writeDBADMIN(TNameAdministration.getText(),
                        Integer.parseInt(TSalaryA.getText()),
                        Integer.parseInt(TWorkingHourseA.getText()), Integer.parseInt(TSubordinates.getText()));
            //JOptionPane.showMessageDialog(null, "Управляющий добавлен", "Добавление", JOptionPane.PLAIN_MESSAGE);
            TNameAdministration.setText("");
            TSalaryA.setText("");
            TWorkingHourseA.setText("");
            TSubordinates.setText("");
            GMenu.jTable.setModel(new MyTable());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Некорректные данные", "Добавление", JOptionPane.PLAIN_MESSAGE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private JMenu menuAdd() {

        AddListener addListener = new AddListener();
        JMenu viewMenu = new JMenu("Добавить сотрудника");
        JMenuItem worker = new JMenuItem("Добавить рабочего");
        JMenuItem engineer = new JMenuItem("Добавить инженера");
        JMenuItem administranor = new JMenuItem("Добавить управляющего");
        viewMenu.add(worker);
        worker.addActionListener(addListener);
        engineer.addActionListener(addListener);
        administranor.addActionListener(addListener);
        viewMenu.add(engineer);
        viewMenu.add(administranor);
        return viewMenu;
    }

    private JMenu menuAction() {
        AddListener addListener = new AddListener();
        JMenu viewMenu = new JMenu("Действия с базой данных");
        JMenuItem search = new JMenuItem("Поиск / Удаление");
        JMenuItem aboutAdmin = new JMenuItem("Вывести информацию только о управляющих");
        aboutAdmin.addActionListener(addListener);
        search.addActionListener(addListener);
        viewMenu.add(search);
        viewMenu.add(aboutAdmin);
        return viewMenu;
    }
}


