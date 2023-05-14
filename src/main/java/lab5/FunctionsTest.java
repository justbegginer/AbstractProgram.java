package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

class FunctionsTest {
    private final static String INPUT_FIELD_TEXT = "Write your text here";
    private final static String OUTPUT_FIELD_TEXT = "There will be your result";

    private static final JFrame functionFrame;
    private static final JTextField inputField;
    private static final JTextField resultField;
    private static final JButton executeBottom;

    static {
        functionFrame = new JFrame();
        inputField = new JTextField();
        resultField = new JTextField();
        executeBottom = new JButton();
        functionFrame.setBounds(100, 100, 600, 600);

        inputField.setPreferredSize(new Dimension(300, 70));
        inputField.setText(INPUT_FIELD_TEXT);
        resultField.setPreferredSize(new Dimension(300, 70));
        resultField.setText(OUTPUT_FIELD_TEXT);

        executeBottom.setPreferredSize(new Dimension(300, 70));
        executeBottom.setBackground(Color.green);
        executeBottom.setText("Execute");

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(resultField);
        panel.add(executeBottom);
        functionFrame.add(panel);
    }

    private static String[] getResultField(JTextField input) {
        return input.getText().split(" ");
    }

    private static void resetText() {
        inputField.setText(INPUT_FIELD_TEXT);
        resultField.setText(OUTPUT_FIELD_TEXT);
    }

    public static void getAverage() {
        resetText();
        executeBottom.addActionListener(e -> {
            String[] inputString = getResultField(inputField);
            List<Integer> functionList = new ArrayList<>();
            boolean isHaveError = false;
            try {
                for (int i = 0; i < inputString.length; i++) {
                    functionList.add(Integer.parseInt(inputString[i]));
                }
            }
            catch (NumberFormatException numberFormatException) {
                isHaveError = true;
            }
            if (isHaveError) {
                resultField.setText("Wrong input");
            }
            else {
                resultField.setText(String.valueOf(Functions.getAverage(functionList)));
            }
        });
        functionFrame.setVisible(true);
    }

    public static void newUpper() {
        resetText();
        executeBottom.addActionListener(e -> {
            String[] inputString = getResultField(inputField);
            List<String> functionList = new ArrayList<>( Arrays.asList(inputString));
            resultField.setText(String.valueOf(Functions.newUpper(functionList)));
        });
        functionFrame.setVisible(true);
    }

    public static void forEveryUniqueMakeSquare() {
        resetText();
        executeBottom.addActionListener(e -> {
            if (!convertionForListInteger().isEmpty()){
                resultField.setText(String.valueOf(Functions.forEveryUniqueMakeSquare(convertionForListInteger())));
            }
        });
        functionFrame.setVisible(true);
    }

    public static void getWithLetterAndSorted() {
        resetText();
        executeBottom.addActionListener(e -> {
            String[] inputString = getResultField(inputField);
            Character character = inputString[0].charAt(0);
            List<String> functionList = new ArrayList<>(Arrays.asList(inputString));
            functionList.remove(0);
            resultField.setText(String.valueOf(Functions.getWithLetterAndSorted(functionList, character)));
        });
        functionFrame.setVisible(true);
    }

    public static void getLast() {
        resetText();
        executeBottom.addActionListener(e -> {
            String[] inputString = getResultField(inputField);
            List<String> functionList = new ArrayList<>(Arrays.asList(inputString));
            resultField.setText(String.valueOf(Functions.getLast(functionList)));
        });
        functionFrame.setVisible(true);
    }

    public static void getSumOfEven() {
        resetText();
        executeBottom.addActionListener(e -> {
            if (!convertionForListInteger().isEmpty()){
                resultField.setText(String.valueOf(Functions.getSumOfEven(convertionForListInteger())));
            }
        });
        functionFrame.setVisible(true);
    }

    private static List<Integer> convertionForListInteger(){
        String[] inputString = getResultField(inputField);
        List<Integer> functionList = new ArrayList<>();
        boolean isHaveError = false;
        try {
            for (int i = 0; i < inputString.length; i++) {
                functionList.add(Integer.parseInt(inputString[i]));
            }
        } catch (Exception e1) {
            isHaveError = true;
        }
        if (isHaveError) {
            resultField.setText("Wrong input");
        }
        return functionList;
    }

    public static void mapByFirstCharacter() {
        resetText();
        executeBottom.addActionListener(e -> {
            String[] inputString = getResultField(inputField);
            Map<Character, String> resultMap = Functions.mapByFirstCharacter(new ArrayList<String>(Arrays.asList(inputString)));
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<Character, String> entry : resultMap.entrySet()) {
                stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append(" ");
            }
            resultField.setText(stringBuilder.toString());
        });
        functionFrame.setVisible(true);
    }
}
