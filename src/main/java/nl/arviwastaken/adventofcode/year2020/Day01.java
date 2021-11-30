package nl.arviwastaken.adventofcode.year2020;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.List;

public class Day01 extends Solution {

    @Override
    public void run() {
        Object input = prepareInput();
        System.out.println(input);
        System.out.println("Solution day 1, part 1: " + part1(input));
        System.out.println("Solution day 1, part 2: " + part2(input));
    }

    @Override
    public String part1(Object input) {
        // Put your solution for part 1 here
        return null;
    }

    @Override
    public String part2(Object input) {
        // Put your solution for part 2 here
        return null;
    }

    @Override
    public Object prepareInput() {
        List<String> input = InputUtil.getInput("2020", "1");

        // Put the code to convert from a list with lines to usuable objects here

        return input;
    }
}