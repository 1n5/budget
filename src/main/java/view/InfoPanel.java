package view;

import controllers.TableController;

import javax.swing.*;
import java.awt.*;


public class InfoPanel extends JPanel {

    private static final JLabel wastedInfoLabel = new JLabel();

    public InfoPanel() {
        this.setSize(WIDTH, HEIGHT /3);
        this.setVisible(true);
        GridLayout layout = new GridLayout();
        this.setLayout(layout);
        setWastedInfo();
        this.add(wastedInfoLabel);
    }

    public static void setWastedInfo() {
        int wasted = TableController.getWastedMoney();
        wastedInfoLabel.setText("Денег потрачено: " + wasted);
    }

}
