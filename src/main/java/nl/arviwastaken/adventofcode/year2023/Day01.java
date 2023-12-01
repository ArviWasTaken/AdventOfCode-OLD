package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 1, part 1: " + part1(input));
        System.out.println("Solution day 1, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        // Put your solution for part 1 here
        int total = 0;
        for (String s: input
             ) {
            String numbers = "";

            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    numbers += s.charAt(i);
                }
            }

            if (!numbers.isEmpty()) {
                String firstLast = String.valueOf(numbers.charAt(0)) + numbers.charAt(numbers.length() -1);
                total += Integer.parseInt(firstLast);
            }
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        // Put your solution for part 2 here
        List<String> words = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"));
        int total = 0;

        for (String s: input
        ) {
            String numbers = "";

            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    numbers += s.charAt(i);
                } else {
                    for (int j= 0; j < words.size(); j++) {
                        String sub = s.substring(i);
                        if (sub.indexOf(words.get(j)) == 0) {
                            numbers += String.valueOf(j + 1);
                        }
                    }
                }
            }

            String firstLast = String.valueOf(numbers.charAt(0)) + numbers.charAt(numbers.length() -1);
            total += Integer.parseInt(firstLast);
        }
        return String.valueOf(total);
    }

    @Override
    public List<String> prepareInput() {
        List<String> output = InputUtil.getInput("2023", "1", true);
        // Put the code to convert from a list with lines to usuable objects here
        return output;
    }
}