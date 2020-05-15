package com.company.controllers;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionsController {

    protected static String boxOptionsPath = "C:/Users/lns/IdeaProjects/budget/src/com/company/Resources/boxoptions.txt";
    protected static String tempBoxOptionsPath = "C:/Users/lns/IdeaProjects/budget/src/com/company/Resources/tempboxoptions.txt";

    public static String getBoxOptionsPath() {
        return boxOptionsPath;
    }

    public static void setBoxOptionsPath(String boxOptionsPath) {
        OptionsController.boxOptionsPath = boxOptionsPath;
    }

    // Получение массива всех типов затрат
    public static String[] getBoxOptions() {
        try {
            Scanner sc = new Scanner(new File(boxOptionsPath));
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            sc.close();
            return lines.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Добавление опции в тип затрат
    public static void addBoxOption (String option) {
        BufferedWriter bw = null;
        try {
            File file = new File(boxOptionsPath);

            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(option + "\n");
            JOptionPane.showMessageDialog(new JOptionPane(),
                    "Опция добавлена \nПоявится после перезагрузки программы",
                    "Ок",
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

    // Удаление типа затрат
    public static void deleteBoxOptions(String target) {

        createTempWithout(target);


    }


    private static void createTempWithout(String target) {
        BufferedWriter bw = null;
        try {
            File file = new File(tempBoxOptionsPath);

            if (!file.exists()) {
                boolean success = file.createNewFile();
            }

            String[] options = getBoxOptions();

            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            if (options != null) {
                for (String s : options) {
                    if (!s.equals(target)) bw.write(s + "\n");
                }
            }
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

}
