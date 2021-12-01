package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends Solution<List<Integer>> {

    @Override
    public void run() {
        List<Integer> input = prepareInput();
        System.out.println("Solution day 1, part 1: " + part1(input));
        System.out.println("Solution day 1, part 2: " + part2(input));
    }

    @Override
    public String part1(List<Integer> input) {
        int counter = 0;
        int index = 0;

        for (Integer i : input) {
            if (index != 0) {
                if (i > input.get(index - 1) ) {
                    counter++;
                }
            }
            index ++;
        }

        return String.valueOf(counter);
    }

    @Override
    public String part2(List<Integer> input) {
        int counter = 0;

        for (int i = 0; i < input.size() ; i++) {
            if ((( i + 2) < (input.size())) && i != 0) {
                int currentSet = input.get(i) + input.get(i + 1) + input.get(i + 2);
                int previousSet = input.get(i - 1) + input.get(i) + input.get(i + 1);

                if (currentSet > previousSet) {
                    counter ++;
                }
            }
        }

        return String.valueOf(counter);
    }

    @Override
    public List<Integer> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "1");

        List<Integer> output = new ArrayList<>();
        for (String s : input
        ) {
            output.add(Integer.parseInt(s));
        }

        return output;
    }
}