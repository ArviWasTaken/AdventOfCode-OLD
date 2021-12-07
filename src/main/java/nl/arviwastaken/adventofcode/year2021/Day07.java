package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 extends Solution<List<Integer>> {

    @Override
    public void run() {
        List<Integer> input = prepareInput();
        System.out.println("Solution day 7, part 1: " + part1(input));
        System.out.println("Solution day 7, part 2: " + part2(input));
    }

    @Override
    public String part1(List<Integer> input) {
        // Put your solution for part 1 here
        List<Integer> Totalfuelcost = new ArrayList<>();
        for (int i = Collections.min(input); i < Collections.max(input); i++) {
            Integer fuelcost = 0;
            for (Integer n : input
            ) {
                fuelcost += Math.abs(n - i);
            }
            Totalfuelcost.add(fuelcost);
        }
        return Collections.min(Totalfuelcost).toString();
    }

    @Override
    public String part2(List<Integer> input) {
        // Put your solution for part 2 here
        List<Integer> Totalfuelcost = new ArrayList<>();
        for (int i = Collections.min(input); i < Collections.max(input); i++) {
            Integer fuelcost = 0;
            for (Integer n : input
            ) {
                for (int j = 0; j < Math.abs(n - i); j++) {
                    fuelcost += 1 + j;
                }
            }
            Totalfuelcost.add(fuelcost);
        }
        return Collections.min(Totalfuelcost).toString();
    }

    @Override
    public List<Integer> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "7", false);
        List<Integer> output = new ArrayList<>();
        for (String s: input
             ) {
            for (String n:s.split(",")
                 ) {
                output.add(Integer.parseInt(n));
            }
        }
        return output;
    }
}