package com.company.controllers;

import java.io.*;
import javax.swing.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TableController {

    protected static String wastesPath = "C:/Users/lns/IdeaProjects/budget/src/com/company/Resources/wastes.txt";

    public static String getWastesPath() {
        return wastesPath;
    }

    // Добавление записи в файл в формате "дата сумма тип"
    public static void write(String money, String type) {
        BufferedWriter bw = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yy");
        LocalDateTime now = LocalDateTime.now();
        try {
            File file = new File(wastesPath);

            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(dtf.format(now) + " " + money + " "  + type + "\n");
            JOptionPane.showMessageDialog(new JOptionPane(),
                    "Информация добавлена",
                    "Успех",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (bw!=null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    // Получение общей суммы затрат
    public static int getWastedMoney() {
        int wastes = 0;
            try (FileReader reader = new FileReader(wastesPath);
                 BufferedReader br = new BufferedReader(reader)) {

                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        wastes += Integer.parseInt(line.substring(9).replaceAll("\\D+",""));
                    } catch (NumberFormatException nfe) {
                        nfe.printStackTrace();
                    }
                }
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        return wastes;
    }

    // Получение суммы затрат в конкретной области
    public static int getWastedMoney(String type) {
        int waste = 0;
        try (FileReader reader = new FileReader(wastesPath);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    if (line.contains(type)) {
                        waste += Integer.parseInt(line.substring(9).replaceAll("\\D+",""));
                    }
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return waste;
    }
}
