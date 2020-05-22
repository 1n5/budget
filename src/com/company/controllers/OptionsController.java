package com.company.controllers;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionsController {

    private static final String boxOptionsPath = "C:/Users/lns/IdeaProjects/budget/src/com/company/Resources/boxoptions.txt";

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
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write(option + "\n");
            JOptionPane.showMessageDialog(new JOptionPane(),
                    "Опция добавлена",
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
                ex.printStackTrace();
            }
        }
    }

    // Удаление типа затрат
    public static void deleteBoxOptions(String target) {
        BufferedWriter bw = null;

        String[] options = getBoxOptions();
        List<String> freshOptions = new ArrayList<>();

        if (options != null) {
            for (String s : options) {
                if (!s.equals(target)) freshOptions.add(s);
            }
        }

        try {
            File file = new File(boxOptionsPath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            FileWriter fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);

            String[] writeOptions = new String[freshOptions.size()];
            writeOptions = freshOptions.toArray(writeOptions);

            for (String s : writeOptions) {
                bw.write(s + "\n");
            }

            JOptionPane.showMessageDialog(new JOptionPane(),
                    "Опция удалена",
                    "Ок",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
