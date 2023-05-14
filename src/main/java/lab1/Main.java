package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi, there is some rules of testing minigame");

        System.out.println("There is some rules\n" +
                "First - flying can't fly up more than 50, reached distance more than 75 and can't dig\n" +
                "Second - digging can't fly , reached distance more than 75 and can't dig up more than 50\n" +
                "Last - walking can't fly, can't dig and can't reached distance more than 100\n" +
                "Have fun\n");
        System.out.println("What type of animal you wanna choose\nFly\nDig\nWalk\nWright your type in:");
        Scanner in = new Scanner(System.in);
        Hero newHero = new Hero(in.next());
        do {

            System.out.println("Enter x coordinate of moving:");
            double x = in.nextDouble();
            System.out.println("Enter y coordinate of moving:");
            double y = in.nextDouble();
            System.out.println("Enter z coordinate of moving:");
            double z = in.nextDouble();
            newHero.move(new Point(x, y, z));
            System.out.println("do you wanna continue?y/n");
        }while (in.next().equals("y"));
        //System.out.println(newHero.getPosition().getCoordinatesMessage());
        System.out.println("Goodbye");
    }

    public static void swingMain(){
        JFrame choiceFrame = new JFrame();
        choiceFrame.setBounds(100, 100, 600, 600);
        JButton chooseFly = new JButton(), chooseDig = new JButton(), chooseWalk = new JButton();
        chooseFly.addActionListener((e)-> game(new Hero("Fly")));
        chooseDig.addActionListener((e)-> game(new Hero("Dig")));
        chooseWalk.addActionListener((e)-> game(new Hero("Walk")));

        JPanel panel = new JPanel();

        chooseFly.setText("Fly");
        chooseDig.setText("Dig");
        chooseWalk.setText("Walk");

        chooseDig.setBackground(Color.GREEN);
        chooseFly.setBackground(Color.GREEN);
        chooseWalk.setBackground(Color.GREEN);

        chooseFly.setPreferredSize(new Dimension(300,70));
        chooseWalk.setPreferredSize(new Dimension(300, 70));
        chooseDig.setPreferredSize(new Dimension(300,70));

        panel.add(chooseFly);
        panel.add(chooseDig);
        panel.add(chooseWalk);

        choiceFrame.add(panel);
        choiceFrame.setVisible(true);
    }
    private static void game(Hero hero){
        JFrame movementFrame = new JFrame();
        movementFrame.setBounds(100, 100, 600, 600);
        JTextField x = new JTextField();
        x.setText(String.valueOf(hero.getPosition().getX()));
        JTextField y = new JTextField();
        y.setText(String.valueOf(hero.getPosition().getY()));
        JTextField z = new JTextField();
        z.setText(String.valueOf(hero.getPosition().getZ()));

        JTextField resultLog = new JTextField();
        resultLog.setText("Start work");
        JButton moveButton = new JButton();
        moveButton.addActionListener((e)-> {
            try {
                double xPoint = Double.parseDouble(x.getText());
                double yPoint = Double.parseDouble(y.getText());
                double zPoint = Double.parseDouble(z.getText());
                Point newPosition = new Point(xPoint, yPoint, zPoint);
                resultLog.setText(hero.move(newPosition));
            }
            catch (NumberFormatException exception){
                resultLog.setText("Invalid input, expected floating number");
            }
        });
        JPanel jPanel = new JPanel();

        x.setPreferredSize(new Dimension(300, 70));
        y.setPreferredSize(new Dimension(300, 70));
        z.setPreferredSize(new Dimension(300, 70));
        resultLog.setPreferredSize(new Dimension(300, 70));
        moveButton.setPreferredSize(new Dimension(300, 70));

        moveButton.setBackground(Color.GREEN);
        moveButton.setText("Move");

        jPanel.add(x);
        jPanel.add(y);
        jPanel.add(z);
        jPanel.add(resultLog);
        jPanel.add(moveButton);

        movementFrame.add(jPanel);
        movementFrame.setVisible(true);
    }
}