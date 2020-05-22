package com.company.frames;

import com.company.controllers.TableController;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Так как график за месяц является приложением, повторное нажатие на кнопку крашит программу. Пока не используется,
 * в дальнейшем либо изменить реализацию, либо отказаться от этой идеи
 */

public class GraphWindow extends Application {


    @Override
    public void start(Stage primaryStage) {

        ArrayList<String> type = new ArrayList<>();
        Collections.addAll(type, BottomPanel.getBoxOptions());

        int[] values = new int[type.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = TableController.getWastedMoney(type.get(i));
        }

        PieChart pieChart = new PieChart();
        for(int i = 0; i < type.size(); i++) {
            PieChart.Data pd = new PieChart.Data(type.get(i), values[i]);
            pieChart.getData().add(pd);
        }

        pieChart.setLegendSide(Side.LEFT);
        pieChart.setLegendVisible(false);

        primaryStage.setTitle("График затрат за месяц");
        StackPane root = new StackPane(pieChart);
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void go() {
        Application.launch();
    }
}