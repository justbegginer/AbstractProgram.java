package lab6;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AbstractProgram abstractProgram = new AbstractProgram();
        Supervisor supervisor = new Supervisor(abstractProgram, ReadingStreamImplSwing.getInstance());
        supervisor.start();
    }

    public static void swingMain() {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(100, 100, 600, 600);
        JButton jButton = new JButton();
        jButton.setBounds(250, 250, 100, 100);
        jButton.setBackground(Color.GREEN);
        jButton.setText("Start");
        jButton.addActionListener((e) -> outputFunc());
        jFrame.add(jButton);
        jFrame.setVisible(true);
    }

    private static JTextField[] output;
    private static JFrame outputFrame;

    private static void outputFunc() {
        output = new JTextField[6];
        JPanel jPanel = new JPanel();
        for (int i = 0; i < 6; i++) {
            output[i] = new JTextField();
            output[i].setPreferredSize(new Dimension(300, 70));
            jPanel.add(output[i]);
        }

        outputFrame = new JFrame();
        outputFrame.setBounds(100, 100, 600, 600);
        outputFrame.add(jPanel);
        outputFrame.setVisible(true);

        Supervisor supervisor = new Supervisor(new AbstractProgram(), ReadingStreamImplSwing.getInstance());
        supervisor.start();
    }

    public static void refreshPage(String newText) {
        outputFrame.getContentPane().removeAll();
        JPanel jPanel = new JPanel();
        JTextField newJTextField = new JTextField();
        newJTextField.setPreferredSize(new Dimension(300, 70));
        newJTextField.setText(newText);

        JTextField[] copyOutput = new JTextField[5];
        for (int i = 1; i < copyOutput.length + 1; i++) {
            copyOutput[i - 1] = output[i];
        }
        for (int i = 0; i < output.length - 1; i++) {
            output[i] = copyOutput[i];
        }
        output[5] = newJTextField;
        for (int i = 0; i < output.length; i++) {
            jPanel.add(output[i]);
        }

        outputFrame.add(jPanel);
        outputFrame.revalidate();
        outputFrame.setVisible(true);
    }
}
