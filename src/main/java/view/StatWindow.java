package view;

import controllers.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StatWindow extends JFrame {

    private final JTable table = new JTable();

    public StatWindow() {
        this.setResizable(false);
        this.setVisible(false);
        this.setTitle("Статистика затрат");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(450, 485));
        this.setLocationRelativeTo(null);
        initStatTable();
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

            for (Object lines : tableLines) {
                String line = lines.toString().trim();
                String[] dataRow = line.split(" ");
                model.addRow(dataRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.add(new JScrollPane(table));
    }
}
