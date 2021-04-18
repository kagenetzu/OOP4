package com.company.Controller;

import com.company.View.GMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Добавить рабочего":
                JustWorkerAdd addJustWorker = new JustWorkerAdd();
                break;
            case "Добавить рабочего в список":
                JustWorkerAdd.add();
                break;
            case "Добавить инженера":
                EngineerAdd addEngineer = new EngineerAdd();
                break;
            case "Добавить инженера в список":
                EngineerAdd.add();
                break;
            case "Добавить управляющего":
                AdministrationAdd addAdministration = new AdministrationAdd();
                break;
            case "Добавить управляющего в список":
                AdministrationAdd.add();
                break;
            case "Вывести информацию только о управляющих":
                Functional onlyAdmin = new Functional();
                onlyAdmin.showAdministation();
                break;
            case "Поиск / Удаление":
                menuSearch search = new menuSearch();
                break;
            case "Удалить сотрудника":
                menuSearch.dataWorkers.setText(menuSearch.del());
                GMenu.jTable.setModel(new MyTable());
                break;
            case "Найти сотрудника":
                menuSearch.dataWorkers.setText(menuSearch.search());
                break;
            case "Добавление рабочего":
                GMenu.addJW();
                break;
            case "Добавление инженера":
                GMenu.addE();
                break;
            case "Добавление управляющего":
                GMenu.addA();
                break;
            case "Сортировать":
                Functional.data.info.clear();
                GMenu.sortBy();
                GMenu.jTable.setModel(new MyTable());
                break;
            case "Найти":
                Functional.data.info.clear();
                GMenu.filtrBy();
                GMenu.jTable.setModel(new MyTable());
                break;
        }
    }
}
