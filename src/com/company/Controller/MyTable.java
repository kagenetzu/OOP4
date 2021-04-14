package com.company.Controller;

import javax.swing.table.AbstractTableModel;


public class MyTable extends AbstractTableModel {
    public int rowCount = Functional.data.getSize();
    public int columnCount = 7;

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return    Functional.data.printTable()[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Номер";
            case 1: return "Имя";
            case 2: return "Зарплата";
            case 3: return "Кол-во часов";
            case 4: return "Должность";
            case 5: return "Разряд";
            case 6: return "Кол-во подчиненных";
        }
        return "";
    }
}
