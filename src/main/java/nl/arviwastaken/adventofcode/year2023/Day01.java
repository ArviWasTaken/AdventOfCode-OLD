package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 extends Solution<List<Integer>> {

    @Override
    public void run() {
        List<Integer> input = prepareInput();
        System.out.println("Solution day 1, part 1: " + part1(input));
        System.out.println("Solution day 1, part 2: " + part2(input));
    }

    @Override
    public String part1(List<Integer> input) {
        // Put your solution for part 1 here
        return null;
    }

    @Override
    public String part2(List<Integer> input) {
        // Put your solution for part 2 here
        return null;
    }

    @Override
    public List<List<Integer>> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "1", false);
        List<Integer> output = new ArrayList<>();
        // Put the code to convert from a list with lines to usuable objects here

        return output;
    }
}