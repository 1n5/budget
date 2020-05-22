package com.company.frames;

import com.company.controllers.TableController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class InfoPanel extends JPanel {

    private static final JLabel wastedInfo = new JLabel();

    public InfoPanel(boolean statOrInfo) {
        this.setSize(MainWindow.WIDTH, MainWindow.HEIGHT/3);
        this.setVisible(true);
        GridLayout layout = new GridLayout();
        this.setLayout(layout);
        JLabel wastedStat = new JLabel(initStat());
        setWastedInfo();
        JLabel label = statOrInfo ? wastedInfo : wastedStat;
        this.add(label);
    }

    public static void setWastedInfo() {
        int wasted = TableController.getWastedMoney();
        wastedInfo.setText("Денег потрачено: " + wasted);
    }

    public static String initStat() {

        ArrayList<String> type = new ArrayList<>();
        Collections.addAll(type, BottomPanel.getBoxOptions());

        int[] wastes = new int[type.size()];
        for (int i = 0; i < wastes.length; i++) {
            wastes[i] = TableController.getWastedMoney(type.get(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < type.size(); i++) {
            sb.append(type.get(i)).append(": ").append(wastes[i]).append(" ");
        }
        return sb.toString();
    }

}
