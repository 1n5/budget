package view;

import controllers.TableController;

import javax.swing.*;
import java.awt.*;


public class InfoPanel extends JPanel {

    private final JLabel wastedInfoLabel = new JLabel();

    public InfoPanel() {
        this.setSize(WIDTH, HEIGHT /3);
        this.setVisible(true);
        GridLayout layout = new GridLayout();
        this.setLayout(layout);
        setWastedInfo();
        this.add(wastedInfoLabel);
    }

    public void setWastedInfo() {
        int wasted = TableController.getWastedMoney();
        wastedInfoLabel.setText("Денег потрачено: " + wasted);
    }
/* Не используется в данный момент

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

 */

}
