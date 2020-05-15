package com.company.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopPanel extends JPanel {

    public TopPanel() {
        this.setSize(MainWindow.WIDTH, MainWindow.HEIGHT/3);
        this.initButtons();
        this.setVisible(true);
    }

    public void initButtons() {
        JButton statistics = new JButton("Статистика");
        JButton settings = new JButton("Настройки");

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        //Кнопка подробной статистики
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1;
        c1.weighty = 0;
        c1.gridx = 0;
        c1.gridy = 0;
        this.add(statistics, c1);
        statistics.addActionListener(e -> new StatWindow());

        //Кнопка вывода графика
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.weightx = 1;
        c1.weighty = 0;
        c2.gridx = 1;
        c2.gridy = 0;
        this.add(settings, c2);
        settings.addActionListener(e -> new OptionsWindow());

        this.setVisible(true);
    }




}
