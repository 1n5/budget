package com.company.frames;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    protected static final int MAIN_WIDTH = 500;
    protected static final int MAIN_HEIGHT = 120;
    protected static final String VERSION = "0.01";

    private final InfoPanel panel = new InfoPanel(true);
    private final TopPanel panel1 = new TopPanel();
    private final BottomPanel panel2 = new BottomPanel();

    public MainWindow() {
        this.setSize(MAIN_WIDTH, MAIN_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Budget app, v." + VERSION);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initGui();
    }

    public void initGui() {
        GridLayout layout = new GridLayout(3, 1);
        this.setLayout(layout);
        this.add(panel);
        this.add(panel1);
        this.add(panel2);
    }


}
