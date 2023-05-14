package lab7;

import lab4.exceptions.FileReadException;
import lab4.exceptions.FileWriteException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        startPage();
    }

    public static void startPage() {
        JFrame mainFrame = new JFrame("Swing GUI");
        JButton button1 = new JButton();
        button1.addActionListener(e -> lab1.Main.swingMain());
        JButton button2 = new JButton();
        button2.addActionListener(e -> lab2.Main.swingMain());
        JButton button3 = new JButton();
        button3.addActionListener(e -> lab3.Main.swingMain());
        JButton button4 = new JButton();
        button4.addActionListener(e -> {
            try {
                lab4.Main.swingMain();
            } catch (FileWriteException ex) {
                ex.printStackTrace();
            } catch (FileReadException ex) {
                ex.printStackTrace();
            }
        });
        JButton button5 = new JButton();
        button5.addActionListener(e -> lab5.Main.swingMain());
        JButton button6 = new JButton();
        button6.addActionListener(e -> lab6.Main.swingMain());

        JPanel buttonPanel = new JPanel(); // TODO realised what it exactly does
        List<JButton> jButtons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6));

        for (int i = 0; i < jButtons.size(); i++) {
            jButtons.get(i).setText("LAB " + (i+1));
        }
        setListForPanel(buttonPanel, jButtons);
        mainFrame.setBounds(100, 100, 600, 600);
        mainFrame.add(buttonPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
    }
    public static void setListForPanel(JPanel panel, List<JButton> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setMnemonic(KeyEvent.VK_1 + i);
            list.get(i).setBackground(Color.GREEN);
            list.get(i).setPreferredSize(new Dimension(300, 70));
            panel.add(list.get(i));
        }
    }
}
