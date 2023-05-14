package lab4;

import lab4.exceptions.FileReadException;
import lab4.exceptions.FileWriteException;
import lab4.exceptions.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    static List<String> fileToListString(String filename) throws FileReadException {
        List<String> stringList = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileReadException(filename + " doesn't exist");
        } else if (!file.canRead()) {
            throw new FileReadException("Permission denied to read - '" + filename + "'");
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            String lineToAdd = fileReader.readLine().toLowerCase();
            while (lineToAdd != null) {
                stringList.add(lineToAdd.toLowerCase());
                lineToAdd = fileReader.readLine();
            }
        } catch (IOException ioException) {
            throw new FileReadException(filename + " with another type of exception");
        }

        return stringList;
    }

    static HashMap<String, String> listToDictionary(List<String> stringList) throws InvalidFileFormatException {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String s : stringList) {
            String[] temp = s.split("\\|");
            if (temp.length != 2) {
                throw new InvalidFileFormatException("Line '" + s + "' is not correct");
            }
            String[] keyArray = temp[0].split(" ");
            String[] valueArray = temp[1].split(" ");
            if (keyArray.length != valueArray.length) {
                throw new InvalidFileFormatException("Line '" + s + "' is not correct");
            }
            for (int j = 0; j < keyArray.length; j++) {
                if (hashMap.containsKey(keyArray[j])) {
                    String valueBefore = hashMap.get(keyArray[j]);
                    if (valueBefore.length() < valueArray[j].length()) {
                        hashMap.replace(keyArray[j], valueArray[j]);
                    }
                } else {
                    hashMap.put(keyArray[j], valueArray[j]);
                }
            }
            if (keyArray.length != 1) {
                hashMap.put(temp[0], temp[1]);
            }
        }
        return hashMap;
    }

    private static void writeIntoFile(List<String> values, String fileName) throws FileWriteException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileWriteException(fileName + " doesn't exist");
        } else if (!file.canRead()) {
            throw new FileWriteException("Permission denied to read - '" + fileName + "'");
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (String value : values) {
                writer.write(value);
                writer.write("\n");
            }
        }
        catch (IOException e){
            throw new FileWriteException("Unknown error while writing into " + fileName);
        }
    }

    static void deleteFromFile(String filename, int index) throws FileWriteException, FileReadException {
        List<String> listFromFile = fileToListString(filename);
        listFromFile.remove(index);
        writeIntoFile(listFromFile, filename);
    }
}
