package com.company.frames;

import com.company.controllers.OptionsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsWindow extends JFrame {

    private JPanel grid;
    private static String[] boxOptions;
    private JComboBox<String> typeOfWaste;
    private JTextField newTypeOfWaste;
    private JButton addOption, removeOption, clearFile;

    public OptionsWindow() {
        this.setResizable(true);
        this.setVisible(true);
        this.setTitle("Настройки");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 120));
        this.setLocationRelativeTo(null);
        initLayout();
    }

    private void initLayout() {
        grid = new JPanel();
        GridLayout layout = new GridLayout(3, 2, 5, 5);
        grid.setLayout(layout);
        initContent();
        grid.add(typeOfWaste);
        grid.add(removeOption);
        grid.add(newTypeOfWaste);
        grid.add(addOption);
        grid.add(clearFile);
        getContentPane().add(grid);
        pack();
    }


    private void initContent() {
        boxOptions = OptionsController.getBoxOptions();
        if (boxOptions != null) typeOfWaste = new JComboBox<>(boxOptions);
        typeOfWaste.setPreferredSize(new Dimension(100, 50));

        newTypeOfWaste = new JTextField();
        addOption = new JButton("Добавить тип затрат");
        removeOption = new JButton("Удалить тип затрат");
        clearFile = new JButton("Очистить файл");


        addOption.addActionListener(e -> {
            String text = newTypeOfWaste.getText();
                if (text.equals("")) {
                    JOptionPane.showMessageDialog(grid,
                            "Поле пустое",
                            "Ошибка",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    OptionsController.addBoxOption(newTypeOfWaste.getText());
                }
        });

        removeOption.addActionListener(e -> OptionsController.deleteBoxOptions((String) typeOfWaste.getSelectedItem()));

    }

}
