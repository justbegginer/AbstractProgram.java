package lab5;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

    }
    public static void swingMain(){
        JFrame labFrame = new JFrame();
        JButton jButton = new JButton();
        jButton.addActionListener(e -> FunctionsTest.getAverage());
        JButton jButton1 = new JButton();
        jButton1.addActionListener(e -> FunctionsTest.newUpper());
        JButton jButton2 = new JButton();
        jButton2.addActionListener(e -> FunctionsTest.forEveryUniqueMakeSquare());
        JButton jButton3 = new JButton();
        jButton3.addActionListener(e -> FunctionsTest.getWithLetterAndSorted());
        JButton jButton4 = new JButton();
        jButton4.addActionListener(e -> FunctionsTest.getLast());
        JButton jButton5 = new JButton();
        jButton5.addActionListener(e -> FunctionsTest.getSumOfEven());
        JButton jButton6 = new JButton();
        jButton6.addActionListener(e -> FunctionsTest.mapByFirstCharacter());

        jButton.setText("getAverage");
        jButton1.setText("newUpper");
        jButton2.setText("forEveryUniqueMakeSquare");
        jButton3.setText("getWithLetterAndSorted");
        jButton4.setText("getLast");
        jButton5.setText("getSumOfEven");
        jButton6.setText("mapByFirstCharacter");

        List<JButton> buttons = new ArrayList<>(Arrays.asList(jButton,jButton1,jButton2,jButton3,jButton4,jButton5));
        JPanel buttonPanel = new JPanel();
        lab7.Main.setListForPanel(buttonPanel, buttons);
        labFrame.setBounds(100, 100, 600, 600);
        labFrame.add(buttonPanel);
        labFrame.setVisible(true);
        labFrame.setLayout(null);
    }
}