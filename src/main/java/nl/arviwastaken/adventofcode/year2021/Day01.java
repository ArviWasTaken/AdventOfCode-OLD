package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class Day01 extends Solution {

    @Override
    public void run() {
        Object input = prepareInput();
        System.out.println("Solution day 1, part 1: " + part1(input));
        System.out.println("Solution day 1, part 2: " + part2(input));
    }

    @Override
    public String part1(Object input) {
        List<Integer> data = (List<Integer>) input;

        Integer counter = 0;
        Integer index = 0;
        for (Integer i : data) {

            if (index != 0) {
                if (i > data.get(index - 1) ) {
                    counter++;
                }
            }

            index ++;
        }


        return counter.toString();
    }

    @Override
    public String part2(Object input) {
        List<Integer> data = (List<Integer>) input;

        Integer counter = 0;
        for (int i = 0; i < data.size() ; i++) {
            if ((( i + 2) < (data.size())) && i != 0) {
                int currentSet = data.get(i) + data.get(i + 1) + data.get(i + 2);
                Integer previousSet = data.get(i - 1) + data.get(i) + data.get(i + 1);
                if (currentSet > previousSet) {
                    counter ++;
                }
            }
        }


        return counter.toString();
    }

    @Override
    public Object prepareInput() {
        List<String> input = InputUtil.getInput("2021", "1");

        List<Integer> output = new ArrayList<>();
        for (String s : input
        ) {
            output.add(Integer.parseInt(s));
        }

        return output;
    }
}