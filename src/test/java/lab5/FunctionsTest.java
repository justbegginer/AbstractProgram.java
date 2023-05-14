package lab5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

class FunctionsTest {

    @Test
    void getAverage() {
        List<Integer> integers = new ArrayList<>(10);
        Assertions.assertEquals(Functions.getAverage(integers), 0);
        for (int i = 1; i <= 10; i++) {
            integers.add(i * 100);
        }
        Assertions.assertEquals(Functions.getAverage(integers), 550);
    }

    @Test
    void newUpper() {
        List<String> listString = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            listString.add(Character.toString((char) (i + (int) ('a'))));
        }
        List<String> resultList = Functions.newUpper(listString);
        for (int i = 0; i < listString.size(); i++) {
            Assertions.assertEquals("_new_" + listString.get(i).toUpperCase(), resultList.get(i));
        }
    }

    @Test
    void forEveryUniqueMakeSquare() {
        List<Integer> integers = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            integers.add(i * 10);
        }
        for (int i = 1; i <= 5; i++) {
            integers.add(i * 10);
        }
        integers = Functions.forEveryUniqueMakeSquare(integers);
        Assertions.assertEquals(integers.get(0), 3600);
        Assertions.assertEquals(integers.get(1), 4900);
        Assertions.assertEquals(integers.get(2), 6400);
        Assertions.assertEquals(integers.get(3), 8100);
        Assertions.assertEquals(integers.get(4), 10_000);
    }

    @Test
    void getWithLetterAndSorted() {
        List<String> stringList = new ArrayList<>();
        for (int i = 10; i >= 0; i--) {
            for (int j = 10; j >= 0; j--) {
                stringList.add(Character.toString((char) ((int) 'a' + i)) + Character.toString((char) ((int) 'a' + j)));
            }
        }
        List<String> resultArr = Functions.getWithLetterAndSorted(stringList, 'a');
        Assertions.assertEquals(resultArr.size(), 11);
        for (int i = 0; i <= 10; i++) {
            Assertions.assertEquals(resultArr.get(i), "a" + (char)((int)'a'+ i));
        }
    }

    @Test
    void getLast() {
        List<Integer> integers = new ArrayList<>(10);
        Assertions.assertThrows(NoSuchElementException.class, ()->Functions.getLast(integers));
        for (int i = 0; i <= 10; i++) {
            integers.add(i);
        }
        Assertions.assertEquals(Functions.getLast(integers),10);
        List<String> strings = new ArrayList<>(10);
        for (int i = 0; i <= 10; i++) {
            strings.add(Character.toString((char)((int)'a' + i)));
        }
        Assertions.assertEquals(Functions.getLast(strings), "k");
    }

    @Test
    void getSumOfEven() {
        List<Integer> integers = new ArrayList<>(10);
        Assertions.assertEquals(Functions.getSumOfEven(integers), 0);
        for (int i = 0; i < 10; i++){
            integers.add(i);
        }
        Assertions.assertEquals(Functions.getSumOfEven(integers), 20);
    }

    @Test
    void mapByFirstCharacter() {
        List<String> listForMap = new ArrayList<>();
        listForMap.add("name");
        listForMap.add("age");
        listForMap.add("profession");
        listForMap.add("surname");
        Map<Character, String> characterStringMap = Functions.mapByFirstCharacter(listForMap);
        Assertions.assertEquals(characterStringMap.get('a'), "ge");
        Assertions.assertEquals(characterStringMap.get('n'), "ame");
        Assertions.assertEquals(characterStringMap.get('p'), "rofession");
        Assertions.assertEquals(characterStringMap.get('s'), "urname");

    }
}