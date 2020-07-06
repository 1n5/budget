package view;

import controllers.OptionsController;
import controllers.TableController;

import javax.swing.*;
import java.awt.*;

public class OptionsWindow extends JFrame {

    private JPanel grid;
    private JComboBox<String> typeOfWaste;
    private JTextField newTypeOfWaste;
    private JButton addOption, removeOption, clearFile;

    public OptionsWindow() {
        this.setResizable(false);
        this.setVisible(false);
        this.setTitle("Настройки");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 120));
        this.setLocationRelativeTo(null);
        initLayout();
    }

    private void initLayout() {
        grid = new JPanel();
        GridLayout layout = new GridLayout(3, 2, 5, 7);
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
        String[] boxOptions = OptionsController.getBoxOptions();
        if (boxOptions != null) typeOfWaste = new JComboBox<>(boxOptions);
        typeOfWaste.setPreferredSize(new Dimension(100, 50));

        newTypeOfWaste = new JTextField();
        addOption = new JButton("Добавить тип затрат");
        removeOption = new JButton("Удалить тип затрат");
        clearFile = new JButton("Очистить файл затрат");

        addOption.addActionListener(e -> {
            String text = newTypeOfWaste.getText();
                if (text.equals("")) {
                    JOptionPane.showMessageDialog(grid,
                            "Поле пустое",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    OptionsController.addBoxOption(newTypeOfWaste.getText());
                }
        });

        removeOption.addActionListener(e -> {
            if (confirmation() == JOptionPane.YES_OPTION) {
                OptionsController.deleteBoxOptions((String) typeOfWaste.getSelectedItem());
            }
        });

        clearFile.addActionListener(e -> {
            if (confirmation() == JOptionPane.YES_OPTION) {
                TableController.clearFile();
            }
        });
    }

    private int confirmation() {
        Object[] options1 = {"Да", "Нет"};

        JPanel panel = new JPanel();
        panel.add(new JLabel("Вы уверены?"));

        return JOptionPane.showOptionDialog(this, panel, "Внимание!",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options1, null);
    }
}
