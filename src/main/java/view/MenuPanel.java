package view;

import controllers.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class MenuPanel extends JPanel {

    private final OptionsWindow optionsWindow = new OptionsWindow();
    private final StatWindow statWindow = new StatWindow();
    private final JFrame statFrame = initStatFrame();

    public MenuPanel() {
        this.setSize(WIDTH, HEIGHT /3);
        this.initButtons();
        this.setVisible(true);
    }

    private void initButtons() {
        JButton statButton = new JButton("Таблица затрат");
        JButton graphButton = new JButton("Общая статистика затрат");
        JButton settingsButton = new JButton("Настройки");

        statButton.addActionListener(e -> statWindow.setVisible(true));
        graphButton.addActionListener(e -> statFrame.setVisible(true));
        settingsButton.addActionListener(e -> optionsWindow.setVisible(true));

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1;
        c1.weighty = 0;
        c1.gridx = 0;
        c1.gridy = 0;
        this.add(statButton, c1);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 1;
        c3.weighty = 0;
        c3.gridx = 1;
        c3.gridy = 0;
        this.add(graphButton, c3);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.weightx = 1;
        c1.weighty = 0;
        c2.gridx = 2;
        c2.gridy = 0;
        this.add(settingsButton, c2);

        this.setVisible(true);
    }

    private JFrame initStatFrame() {
        JFrame stat = new JFrame();

        JTable table = new JTable();
        String[] columnsName = {"Тип", "Общая сумма"};
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setColumnIdentifiers(columnsName);

        ArrayList<String> type = new ArrayList<>();
        Collections.addAll(type, BottomPanel.getBoxOptions());

        int[] wastes = new int[type.size()];
        for (int i = 0; i < wastes.length; i++) {
            wastes[i] = TableController.getWastedMoney(type.get(i));
        }

        for (int i = 0; i < type.size(); i ++) {
            String line = type.get(i) + " " + wastes[i];
            String[] dataRow = line.split(" ");
            model.addRow(dataRow);
        }
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(200, 130));

        stat.add(pane);
        stat.pack();
        stat.setLocationRelativeTo(null);
        stat.setResizable(true);
        stat.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        stat.setVisible(false);
        return stat;
    }

}
