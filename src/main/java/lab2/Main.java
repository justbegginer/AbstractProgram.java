package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Player player = new Player();
            Method[] methods = player.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                methods[i].setAccessible(true);
                System.out.println(methods[i].getName());
                for (int i1 = 0; i1 < methods[i].getAnnotation(MyAnnotation.class).repeatTimes(); i1++) {
                    Class<?>[] types = methods[i].getParameterTypes();
                    if (types[0] == String.class) {
                        methods[i].invoke(player, "SONG NUMBER " + i1);
                        System.out.println(player.playSong(1));
                    } else if (types[0] == int.class || types[0] == double.class) {
                        System.out.println(methods[i].invoke(player, i1));
                    } else {
                        System.out.println(methods[i].invoke(player, Class.forName(types[0].getName()).getConstructor().newInstance()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static int startField = 0;

    public static void swingMain() {
        JFrame labFrame = new JFrame();
        labFrame.setBounds(100, 100, 600, 600);
        List<JLabel> jLabels = new ArrayList<>();
        try {
            Player player = new Player();
            Method[] methods = player.getClass().getDeclaredMethods();
            for (int i = 0, y = 0; i < methods.length; i++) {
                methods[i].setAccessible(true);
                for (int i1 = 0; i1 < methods[i].getAnnotation(MyAnnotation.class).repeatTimes(); i1++, y += 60) {
                    JLabel field = new JLabel();
                    Class<?>[] types = methods[i].getParameterTypes();
                    if (types[0] == String.class) {
                        methods[i].invoke(player, "SONG NUMBER " + i1);
                        field.setText(player.playSong(1));
                    } else if (types[0] == int.class || types[0] == double.class) {
                        field.setText((String) methods[i].invoke(player, i1));
                    } else {
                        field.setText((String) methods[i].invoke(player, Class.forName(types[0].getName()).getConstructor().newInstance()));
                    }
                    jLabels.add(field);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        JButton upButton = new JButton(), downButton = new JButton();

        upButton.addActionListener((e) -> {
            if (startField > 0) {
                startField--;
                refreshPage(labFrame, jLabels);
            }
        });
        upButton.setBackground(Color.BLUE);
        upButton.setBounds(450, 50, 100, 50);

        downButton.addActionListener((e) -> {
            if (startField < jLabels.size() - 1) {
                startField++;
                refreshPage(labFrame, jLabels);
            }

        });
        downButton.setBackground(Color.BLUE);
        downButton.setBounds(450, 150, 100, 50);

        labFrame.add(upButton);
        labFrame.add(downButton);
        refreshPage(labFrame, jLabels);
        labFrame.setVisible(true);
    }

    public static void refreshPage(JFrame labFrame, List<JLabel> jTextFields) {
        JButton upButton, downButton;
        upButton = (JButton) labFrame.getContentPane().getComponent(0);
        downButton = (JButton) labFrame.getContentPane().getComponent(1);
        labFrame.getContentPane().removeAll();
        JPanel jPanel = new JPanel();
        for (int i = startField, ky = 0; i < startField + 8 && i < jTextFields.size(); i++, ky += 60) {
            jTextFields.get(i).setPreferredSize(new Dimension(300, 70));
            jPanel.add(jTextFields.get(i));
        }

        labFrame.add(upButton);
        labFrame.add(downButton);
        labFrame.add(jPanel);
        labFrame.revalidate();
        labFrame.setVisible(true);
    }
}
