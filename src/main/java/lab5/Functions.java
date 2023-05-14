package lab5;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Functions {
    public static double getAverage(List<Integer> numbers){
        double result = numbers.stream()
                .reduce((Integer::sum)) // Integer::sum
                .orElse(0);
        return result / numbers.size();
    }
    public static List<String> newUpper(List<String> strings){
        List<String> newUpperList =  strings.stream()
                .map(i -> "_new_" + i.toUpperCase())
                .collect(Collectors.toList());
        return newUpperList;
    }
    public static  List<Integer> forEveryUniqueMakeSquare(List<Integer> ints){
        return ints.stream()
                .filter(n -> Collections.frequency(ints, n) == 1)
                .map(n -> n*n)
                .collect(Collectors.toList());
    }
    public static List<String> getWithLetterAndSorted(Collection<String> strings, Character letter){
        return strings.stream()
                .filter(n -> letter == n.charAt(0))
                .sorted()
                .collect(Collectors.toList());
    }
    public static <T> T getLast(Collection<T> list) throws NoSuchElementException {
        return list.stream()
                .reduce((first, last) -> last)
                .orElseThrow(new Supplier<NoSuchElementException>() {
                    @Override
                    public NoSuchElementException get() {
                        return new NoSuchElementException();
                    }
                });
    }
    public static int getSumOfEven(Collection<Integer> ints){
        return ints.stream()
                .filter(n -> n % 2 == 0)
                .reduce(Integer::sum) // Integer::sum
                .orElse(0);
    }
    public static Map<Character, String> mapByFirstCharacter(List<String> listString){
        return listString.stream()
                .filter(elem -> elem.length() > 1)
                .filter(elem -> Collections.frequency(listString, elem) == 1)
                .collect(Collectors.toMap((key) -> key.charAt(0), (value) -> value.substring(1)));
    }

}