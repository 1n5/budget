package com.company.frames;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.controllers.OptionsController;
import com.company.controllers.TableController;

public class BottomPanel extends JPanel {

    private static final String[] boxOptions = OptionsController.getBoxOptions();
    private JComboBox<String> typeOfWaste;
    private JTextField moneyWasted;

    public BottomPanel() {
        this.setSize(MainWindow.WIDTH, MainWindow.HEIGHT/3);
        this.placeButtons();
        this.setVisible(true);
    }

    public static String[] getBoxOptions() {
        return boxOptions;
    }

    public void setComboBox() {
        typeOfWaste = new JComboBox<>();
        if (boxOptions != null) {
            DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>(boxOptions);
            typeOfWaste.setModel(comboModel);
        }
    }

    public void placeButtons() {
        moneyWasted = new JTextField();
        JButton submit = new JButton("Потрачено");
        setComboBox();

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        //Поле ввода суммы
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 0;
        c1.gridy = 1;
        this.add(moneyWasted, c1);
        //Запрет на воод в текстовое поле любых символов, кроме цифр
        ((AbstractDocument)moneyWasted.getDocument()).setDocumentFilter(new DocumentFilter(){
            Pattern regEx = Pattern.compile("\\d*");
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if(!matcher.matches()){
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        });

        //Выпадающее меню типа затрат
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.weightx = 0.5;
        c2.gridx = 1;
        c2.gridy = 1;
        this.add(typeOfWaste, c2);
        typeOfWaste.setEditable(false);
        typeOfWaste.setVisible(true);

        //Кнопка отправки формы в таблицу
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 0.5;
        c3.gridx = 2;
        c3.gridy = 1;
        this.add(submit, c3);
        submit.addActionListener(e -> submitAction());
    }

    public void submitAction() {
        String text = moneyWasted.getText();
        if (text.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Поле пустое",
                    "Ошибка",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            TableController.write(moneyWasted.getText(), (String) typeOfWaste.getSelectedItem());
        }
        moneyWasted.setText("");
        InfoPanel.setWastedInfo();
    }
}
