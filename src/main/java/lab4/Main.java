package lab4;

import lab4.exceptions.FileReadException;
import lab4.exceptions.FileWriteException;
import lab4.exceptions.InvalidFileFormatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

import static lab4.Utils.*;

public class Main {
    public static String INPUT_FILE_NAME = "src/main/java/lab4/files/inputFile.txt";
    public static String DICTIONARY_FILE_NAME = "src/main/java/lab4/files/dictionary.txt";

    public static void main(String[] args) {
        try {
            List<String> wordsToTranslate = fileToListString(INPUT_FILE_NAME);
            HashMap<String, String> dictionary = listToDictionary(fileToListString(DICTIONARY_FILE_NAME));
            for (String elem : wordsToTranslate) {
                if (dictionary.containsKey(elem)) {
                    System.out.println(dictionary.get(elem));
                } else {
                    if (elem.split(" ").length == 1) {
                        System.out.println(elem);
                    } else {
                        String[] sentenceWords = elem.split(" ");
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < sentenceWords.length; i++) {
                            stringBuilder.append(dictionary.get(sentenceWords[i])).append(" ");
                        }
                        System.out.println(stringBuilder);
                    }
                }
            }
        } catch (FileReadException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int startField = 0;

    public static void swingMain() throws FileWriteException, FileReadException {
        JFrame redirectFrame = new JFrame();
        redirectFrame.setBounds(100, 100, 600, 600);
        JButton redirectToDictionary = new JButton();
        JButton redirectToInputFile = new JButton();
        JButton redirectToTranslation = new JButton();
        JButton redirectToChangeDictionary = new JButton();
        JButton redirectToChangeInputFile = new JButton();

        redirectToDictionary.setBounds(100, 100, 100, 100);
        redirectToInputFile.setBounds(250, 100, 100, 100);
        redirectToTranslation.setBounds(400, 100, 100, 100);
        redirectToChangeDictionary.setBounds(250, 200, 100, 100);
        redirectToChangeInputFile.setBounds(400, 200, 100, 100);

        redirectToDictionary.addActionListener((e) -> {
            try {
                pageForReadDictionary();
            } catch (FileReadException ex) {
                ex.printStackTrace();
            }
        });
        redirectToInputFile.addActionListener((e) -> {
            try {
                pageForReadInputFile();
            } catch (FileReadException ex) {
                ex.printStackTrace();
            }
        });
        redirectToTranslation.addActionListener((e) -> pageForTranslation());
        redirectToChangeDictionary.addActionListener((e) -> changeFilePath("DICTIONARY"));
        redirectToChangeInputFile.addActionListener((e) -> changeFilePath("INPUT_FILE"));

        redirectToDictionary.setText("Open Dictionary");
        redirectToInputFile.setText("Open Input File");
        redirectToTranslation.setText("Translate");
        redirectToChangeDictionary.setText("Change dictionary source");
        redirectToChangeInputFile.setText("Change input file source");

        JPanel jPanel = new JPanel();
        jPanel.add(redirectToDictionary);
        jPanel.add(redirectToInputFile);
        jPanel.add(redirectToTranslation);
        jPanel.add(redirectToChangeDictionary);
        jPanel.add(redirectToChangeInputFile);
        if (!isCorrectFiles(patternForDictionary, DICTIONARY_FILE_NAME)){
            jPanel.add(new JLabel("Default dictionary file incorrect, translation unavailable, please change it or delete wrong line"));
        }
        if (!isCorrectFiles(patternForInputFile, INPUT_FILE_NAME)){
            jPanel.add(new JLabel("Input file incorrect, translation unavailable, please change it or delete wrong line"));
        }
        redirectFrame.add(jPanel);
        redirectFrame.setVisible(true);
    }

    private static int dictionaryPageIndex = 0;

    private static void pageForReadDictionary() throws FileReadException {
        JFrame dictionaryFrame = new JFrame();
        dictionaryFrame.setBounds(100, 100, 600, 600);
        List<String> lines = fileToListString(DICTIONARY_FILE_NAME);
        List<JLabel> labels = new ArrayList<>(lines.size());
        List<JButton> buttons = new ArrayList<>(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            JLabel label = new JLabel();
            label.setText(lines.get(i).replaceFirst("\\|", "-"));
            JButton button = new JButton();
            button.setText("DELETE");
            button.setBackground(Color.RED);
            int finalI = i;
            button.addActionListener(e -> {
                try {
                    deleteFromFile(DICTIONARY_FILE_NAME, finalI);
                    labels.remove(finalI);
                    buttons.remove(finalI);
                    refreshFilePage(dictionaryFrame, labels, buttons, dictionaryPageIndex);
                } catch (FileWriteException | FileReadException ex) {
                    ex.printStackTrace();
                }
            });
            labels.add(label);
            buttons.add(button);
        }

        JButton downButton = new JButton(), upButton = new JButton();
        downButton.addActionListener(e -> {
            if (dictionaryPageIndex < labels.size() - 1) {
                dictionaryPageIndex++;
                refreshFilePage(dictionaryFrame, labels, buttons, dictionaryPageIndex);
            }
        });
        downButton.setBackground(Color.BLUE);
        downButton.setBounds(450, 150, 100, 50);

        upButton.addActionListener(e -> {
            if (dictionaryPageIndex > 0) {
                dictionaryPageIndex--;
                refreshFilePage(dictionaryFrame, labels, buttons, dictionaryPageIndex);
            }
        });
        upButton.setBackground(Color.BLUE);
        upButton.setBounds(450, 50, 100, 50);

        dictionaryFrame.add(downButton);
        dictionaryFrame.add(upButton);
        refreshFilePage(dictionaryFrame, labels, buttons, dictionaryPageIndex);
        dictionaryFrame.setVisible(true);
    }


    private static void refreshFilePage(JFrame jFrame, List<JLabel> labels, List<JButton> buttons, int index) {
        JPanel resultPanel = new JPanel();
        JButton upButton = (JButton) jFrame.getContentPane().getComponent(0);
        JButton downButton = (JButton) jFrame.getContentPane().getComponent(1);
        jFrame.getContentPane().removeAll();
        for (int i = index; i < index + 3; i++) {
            JLabel labelToAdd = labels.get(i);
            JButton buttonToAdd = buttons.get(i);
            labelToAdd.setPreferredSize(new Dimension(300, 70));
            buttonToAdd.setPreferredSize(new Dimension(300, 70));
            resultPanel.add(labelToAdd);
            resultPanel.add(buttonToAdd);
        }

        jFrame.add(upButton);
        jFrame.add(downButton);
        jFrame.add(resultPanel);
        jFrame.revalidate();
    }

    private static void pageForReadInputFile() throws FileReadException {
        JFrame inputFrame = new JFrame();
        inputFrame.setBounds(100, 100, 600, 600);
        List<String> lines = fileToListString(INPUT_FILE_NAME);
        List<JLabel> labels = new ArrayList<JLabel>(lines.size());
        List<JButton> buttons = new ArrayList<JButton>(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            JLabel label = new JLabel();
            label.setText(lines.get(i).replaceFirst("\\|", "-"));
            JButton button = new JButton();
            button.setText("DELETE");
            button.setBackground(Color.RED);
            int finalI = i;
            button.addActionListener(e -> {
                try {
                    deleteFromFile(INPUT_FILE_NAME, finalI);
                    labels.remove(finalI);
                    buttons.remove(finalI);
                    refreshFilePage(inputFrame, labels, buttons, dictionaryPageIndex);
                } catch (FileWriteException | FileReadException ex) {
                    ex.printStackTrace();
                }
            });
            labels.add(label);
            buttons.add(button);
        }

        JButton downButton = new JButton(), upButton = new JButton();
        downButton.addActionListener(e -> {
            if (dictionaryPageIndex < labels.size() - 1) {
                dictionaryPageIndex++;
                refreshFilePage(inputFrame, labels, buttons, dictionaryPageIndex);
            }
        });
        downButton.setBackground(Color.BLUE);
        downButton.setBounds(450, 150, 100, 50);

        upButton.addActionListener(e -> {
            if (dictionaryPageIndex > 0) {
                dictionaryPageIndex--;
                refreshFilePage(inputFrame, labels, buttons, dictionaryPageIndex);
            }
        });
        upButton.setBackground(Color.BLUE);
        upButton.setBounds(450, 50, 100, 50);

        inputFrame.add(downButton);
        inputFrame.add(upButton);
        refreshFilePage(inputFrame, labels, buttons, dictionaryPageIndex);
        inputFrame.setVisible(true);
    }


    private static void pageForTranslation() {
        JFrame labFrame = new JFrame();
        labFrame.setBounds(100, 100, 600, 600);
        List<JLabel> initialText;
        List<JLabel> translatedText;
        try {
            List<String> wordsToTranslate = fileToListString(INPUT_FILE_NAME);
            HashMap<String, String> dictionary = listToDictionary(fileToListString(DICTIONARY_FILE_NAME));
            initialText = new ArrayList<>(wordsToTranslate.size());
            translatedText = new ArrayList<>(wordsToTranslate.size());
            for (String elem : wordsToTranslate) {
                initialText.add(new JLabel(elem));
                if (dictionary.containsKey(elem)) {
                    translatedText.add(new JLabel(dictionary.get(elem)));
                } else {
                    if (elem.split(" ").length == 1) {
                        translatedText.add(new JLabel(elem));
                    } else {
                        String[] sentenceWords = elem.split(" ");
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < sentenceWords.length; i++) {
                            stringBuilder.append(dictionary.get(sentenceWords[i])).append(" ");
                        }
                        translatedText.add(new JLabel(stringBuilder.toString()));
                    }
                }
            }
        } catch (FileReadException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        JButton upButton = new JButton(), downButton = new JButton();
        upButton.setBounds(500, 50, 50, 50);
        upButton.setText("UP");
        upButton.setBackground(Color.blue);
        upButton.setForeground(Color.white);
        upButton.addActionListener(e -> {
            if (startField > 0) {
                startField--;
                refreshPage(labFrame, initialText, translatedText);
            }
        });

        downButton.setBounds(500, 100, 50, 50);
        downButton.setText("DOWN");
        downButton.setBackground(Color.blue);
        downButton.setForeground(Color.white);
        downButton.addActionListener((ActionListener) e -> {
            if (startField < initialText.size() - 1) {
                startField++;
                refreshPage(labFrame, initialText, translatedText);
            }
        });

        labFrame.add(upButton);
        labFrame.add(downButton);
        refreshPage(labFrame, initialText, translatedText);
        labFrame.setVisible(true);
    }

    private static void changeFilePath(String fileToChange) {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(100, 100, 600, 600);
        JPanel jPanel = new JPanel();
        File directory = new File("src/main/java/lab4/files");
        JTextField logText = new JTextField();
        logText.setPreferredSize(new Dimension(300, 70));

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            JButton tempBottom = new JButton();
            tempBottom.setText(file.getName());
            tempBottom.setPreferredSize(new Dimension(300, 70));
            tempBottom.setBackground(Color.GREEN);
            tempBottom.addActionListener((e) -> {
                if (Objects.equals(fileToChange, "DICTIONARY")) {
                    if (file.getName().equals(DICTIONARY_FILE_NAME)) {
                        logText.setText("This file already chosen");
                    } else {
                        try {
                            if (isCorrectFiles(patternForDictionary, DICTIONARY_FILE_NAME)) {
                                logText.setText("Successfully changed");
                                DICTIONARY_FILE_NAME = file.getPath();
                            }
                            else {
                                logText.setText("Dictionary is not correct, path is not changed");
                            }
                        } catch (FileReadException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    if (file.getName().equals(INPUT_FILE_NAME)){
                        logText.setText("This file already chosen");
                    }
                    else {
                        try {
                            if(isCorrectFiles(patternForInputFile, INPUT_FILE_NAME)) {
                                logText.setText("Successfully changed");
                                INPUT_FILE_NAME = file.getPath();
                            }
                            else {
                                logText.setText("Input file is not correct, path is not changed");
                            }
                        } catch (FileReadException ex) {
                            ex.printStackTrace();
                        }
                        INPUT_FILE_NAME = file.getPath();
                    }
                }
            });
            jPanel.add(tempBottom);
        }

        jPanel.add(logText);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    private static void refreshPage(JFrame labFrame, List<JLabel> initialText, List<JLabel> translatedText) {
        JButton upButton = (JButton) labFrame.getContentPane().getComponent(0);
        JButton downButton = (JButton) labFrame.getContentPane().getComponent(1);
        labFrame.getContentPane().removeAll();
        JPanel jPanel = new JPanel();
        for (int i = startField, ky = 0; i < startField + 8 && i < initialText.size(); i++, ky += 60) {
            JLabel currentInitial = initialText.get(i);
            currentInitial.setPreferredSize(new Dimension(300, 70));
            currentInitial.setBackground(Color.GREEN);
            JLabel currentTranslated = translatedText.get(i);
            currentTranslated.setPreferredSize(new Dimension(300, 70));
            currentTranslated.setBackground(Color.RED);
            jPanel.add(currentInitial);
            jPanel.add(currentTranslated);
        }
        labFrame.add(upButton);
        labFrame.add(downButton);
        labFrame.add(jPanel);
        labFrame.revalidate();
    }

    private static final String patternForDictionary = "^([a-zA-Z]+ )*[a-zA-Z]+\\|([а-яА-Я]+ )*[а-яА-Я]+$";
    private static final String patternForInputFile = "^([a-zA-Z]+ )*[a-zA-Z]+$";

    private static boolean isCorrectFiles(String pattern,String filetype) throws FileReadException {
        for (String line :fileToListString(filetype)){
            if (!Pattern.compile(pattern).matcher(line).find()){
                return false;
            }
        }
        return true;
    }
}
