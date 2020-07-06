package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    protected static final int MAIN_WIDTH = 500;
    protected static final int MAIN_HEIGHT = 120;

    private final InfoPanel infoPanel = new InfoPanel();
    private final MenuPanel menuPanel = new MenuPanel();
    private final BottomPanel bottomPanel = new BottomPanel();

    public MainWindow(String title) {
        this.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initGui();
    }

    public void initGui() {
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        this.add(infoPanel);
        this.add(menuPanel);
        this.add(bottomPanel);
    }


}
