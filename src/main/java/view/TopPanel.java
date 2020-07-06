package view;

import controllers.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TopPanel extends JPanel {

    private final OptionsWindow ow = new OptionsWindow();
    private final StatWindow sw = new StatWindow();
    private final JFrame stat = showStat();

    public TopPanel() {
        this.setSize(WIDTH, HEIGHT /3);
        this.initButtons();
        this.setVisible(true);
    }

    private void initButtons() {
        JButton statistics = new JButton("Таблица затрат");
        JButton graph = new JButton("Общая статистика затрат");
        JButton settings = new JButton("Настройки");

        statistics.addActionListener(e -> sw.setVisible(true));
        graph.addActionListener(e -> stat.setVisible(true));
        settings.addActionListener(e -> ow.setVisible(true));

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        //Кнопка вывода таблицы
        GridBagConstraints c1 = new GridBagConstraints();
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 1;
        c1.weighty = 0;
        c1.gridx = 0;
        c1.gridy = 0;
        this.add(statistics, c1);

        //Кнопка вывода графика
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.weightx = 1;
        c3.weighty = 0;
        c3.gridx = 1;
        c3.gridy = 0;
        this.add(graph, c3);

        //Кнопка вывода панели опций
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.weightx = 1;
        c1.weighty = 0;
        c2.gridx = 2;
        c2.gridy = 0;
        this.add(settings, c2);

        this.setVisible(true);
    }

    private JFrame showStat() {
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
