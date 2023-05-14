package lab3;

import lab3.hierarchy.classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        test2();
    }


    static public   void segregate(Collection<? extends Chordate> src,
                                                              Collection<? super CommonHedgehog> collection1,
                                                              Collection<? super Manul> collection2,
                                                              Collection<? super Lynx> collection3) {
        for (Chordate animal : src) {
            if (animal instanceof CommonHedgehog) {
                collection1.add((CommonHedgehog) animal);
            }
            if (animal instanceof Manul) {
                collection2.add((Manul) animal);
            }
            if (animal instanceof Lynx) {
                collection3.add((Lynx) animal);
            }
        }
    }

    private static List<String> test0() {
        Collection<Chordate> test1 = Arrays.asList(new Lynx(),
                new Manul(),
                new CommonHedgehog(),
                new Manul(),
                new CommonHedgehog()
        );


        Collection<Hedgehogs> animals1 = new ArrayList<>();
        Collection<Cats> animals2 = new ArrayList<>();
        Collection<Predatory> animals3 = new ArrayList<>();

        segregate(test1, animals1, animals2, animals3);
        return List.of("Type 1: " + animals1.size(), "Type 2: " + animals2.size(), "Type 3: " + animals3.size());
    }

    private static List<String> test1() {
        Collection<Predatory> test1 = Arrays.asList(
                new Lynx(),
                new Manul(),
                new Manul(),
                new Cats()
        );

        Collection<Chordate> animals1 = new ArrayList<>();
        Collection<Manul> animals2 = new ArrayList<>();
        Collection<Cats> animals3 = new ArrayList<>();

        segregate(test1, animals1, animals2, animals3);
        return List.of("Type 1: " + animals1.size(), "Type 2: " + animals2.size(), "Type 3: " + animals3.size());
    }

    private static List<String> test2() {
        Collection<Hedgehogs> test1 = Arrays.asList(
                new CommonHedgehog(),
                new CommonHedgehog(),
                new Hedgehogs()
        );

        Collection<Insectivores> animals1 = new ArrayList<>();
        Collection<Predatory> animals2 = new ArrayList<>();
        Collection<Predatory> animals3 = new ArrayList<>();

        segregate(test1, animals1, animals2, animals3);
        return List.of("Type 1: " + animals1.size(), "Type 2: " + animals2.size(), "Type 3: " + animals3.size());
    }

    public static void swingMain() {
        JFrame labFrame = new JFrame();
        labFrame.setBounds(100, 100, 600, 600);
        JPanel jPanel = new JPanel();
        List<JTextField> fields = new ArrayList<JTextField>();
        List<JButton> buttons = new ArrayList<JButton>();

        JButton test0Button = new JButton("Test 0");
        JButton test1Button = new JButton("Test 1");
        JButton test2Button = new JButton("Test 2");
        buttons.add(test0Button);
        buttons.add(test1Button);
        buttons.add(test2Button);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setPreferredSize(new Dimension(300, 70));
            buttons.get(i).setBackground(Color.GREEN);
            jPanel.add(buttons.get(i));
        }

        JTextField firstField = new JTextField();
        JTextField secondField = new JTextField();
        JTextField thirdField = new JTextField();
        fields.add(firstField);
        fields.add(secondField);
        fields.add(thirdField);
        for (int i = 0; i < fields.size(); i++) {
            fields.get(i).setPreferredSize(new Dimension(300, 70));
            fields.get(i).setVisible(false);
            jPanel.add(fields.get(i));
        }

        test0Button.addActionListener((e) -> {
            textSetter(fields, test0());
            labFrame.revalidate();
        });
        test1Button.addActionListener((e) -> {
            textSetter(fields, test1());
            labFrame.revalidate();
        });
        test2Button.addActionListener((e) -> {
            textSetter(fields, test2());
            labFrame.revalidate();
        });

        labFrame.add(jPanel);
        labFrame.setVisible(true);

    }
    private static void textSetter(List<JTextField> textPlace, List<String> text){
        textPlace.get(0).setText(text.get(0));
        textPlace.get(1).setText(text.get(1));
        textPlace.get(2).setText(text.get(2));
        textPlace.get(0).setVisible(true);
        textPlace.get(1).setVisible(true);
        textPlace.get(2).setVisible(true);
    }
}