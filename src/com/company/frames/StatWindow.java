package com.company.frames;

import com.company.controllers.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StatWindow extends JFrame {

    private final JTable table = new JTable();
    private final InfoPanel panel = new InfoPanel(false);
    private final JButton graph = new JButton("График");

    public StatWindow() {
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Статистика затрат");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(450, 485));
        this.setLocationRelativeTo(null);
        initPanels();
        this.pack();
    }

    private void initStatTable() {
        File file = new File(TableController.getWastesPath());
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String[] columnsName = {"Дата", "Затраты", "Тип"};
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setColumnIdentifiers(columnsName);

            Object[] tableLines = br.lines().toArray();

            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] dataRow = line.split(" ");
                model.addRow(dataRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(new JScrollPane(table));
    }

    // Доделать панель таким образом, чтобы на ней находилась и таблица и кнопки
    private void initStatPanel() {
        panel.setPreferredSize(new Dimension(100, 200));
        panel.add(graph);
        graph.addActionListener(e -> {
            try {
                GraphWindow.go();
            } catch (IllegalStateException ise) {
                System.exit(1); // При повторном запуске графика приложение будет закрываться, пофиксить
            }
        });
        this.add(panel);
    }

    // Разобраться с лэйаутом окна статистики
    private void initPanels() {
        //BoxLayout bl = new BoxLayout();
        //this.setLayout(bl);

        initStatTable();
        //initStatPanel();


    }

    /*
     * Нижняя панель должна содержать:
     * 1. Подсчет затрат за этот месяц - общий и по каждой категории. В конце каждого месяца траты должны сохраняться
     * в файл monthlywastes в таком же формате.
     * 2. Кнопка вывода графика за этот месяц (wastes) \ за все месяцы (montlywastes)
     * 3. Кнопка вывода статикики за месяц из комбобокса
     */



}
