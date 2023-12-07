package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 6, part 1: " + part1(input));
        System.out.println("Solution day 6, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        // Put your solution for part 1 here
        List<String> time = Arrays.stream(input.get(0).split(" ")).distinct().collect(Collectors.toList());
        List<String> distance = Arrays.stream(input.get(1).split(" ")).distinct().collect(Collectors.toList());

        time.remove(0);
        time.remove(0);
        distance.remove(0);
        distance.remove(0);

        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < time.size(); i++) {
            pairs.add(new int[]{Integer.parseInt(time.get(i)), Integer.parseInt(distance.get(i))});
        }

        List<Integer> margins = new ArrayList<>();
        for (int[] pair: pairs
             ) {
            int amount = 0;
            for (int i = 1; i < pair[0]; i++) {
                int calcDistance = i * (pair[0] - i);
                if (calcDistance > pair[1]) {
                    amount ++;
                }
            }
            margins.add(amount);
        }

        int total = 1;
        for (int i: margins
             ) {
            total = total * i;
        }

        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        // Put your solution for part 2 here
        List<String> time = Arrays.stream(input.get(0).split(" ")).distinct().collect(Collectors.toList());
        List<String> distance = Arrays.stream(input.get(1).split(" ")).distinct().collect(Collectors.toList());

        time.remove(0);
        time.remove(0);
        distance.remove(0);
        distance.remove(0);

        long[] pairs = new long[2];
        String on = "";
        String tw = "";
        for (int i = 0; i < time.size(); i++) {
            on += time.get(i);
            tw += distance.get(i);
        }

        pairs[0] = Long.parseLong(on);
        pairs[1] = Long.parseLong(tw);

        List<Integer> margins = new ArrayList<>();
        int amount = 0;
        for (long i = 1; i < pairs[0]; i++) {
            long calcDistance = i * (pairs[0] - i);
            if (calcDistance > pairs[1]) {
                amount ++;
            }
        }
        margins.add(amount);


        int total = 1;
        for (int i: margins
        ) {
            total = total * i;
        }

        return String.valueOf(total);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "6", false);
        List<String> output;
        // Put the code to convert from a list with lines to usuable objects here

        return input;
    }
}