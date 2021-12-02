package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day02 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 2, part 1: " + part1(input));
        System.out.println("Solution day 2, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        int horizontal = 0;
        int depth = 0;
        for (String s: input) {
            String[] command = s.split(" ");
            switch (command[0]) {
                case "forward":
                    horizontal += Integer.parseInt(command[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(command[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(command[1]);
                    break;
            }
        }
        int result = horizontal * depth;
        return Integer.toString(result);
    }

    @Override
    public String part2(List<String> input) {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for (String s: input) {
            String[] command = s.split(" ");
            switch (command[0]) {
                case "forward":
                    horizontal += Integer.parseInt(command[1]);
                    depth += aim * Integer.parseInt(command[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(command[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(command[1]);
                    break;
            }
        }
        int result = horizontal * depth;
        return Integer.toString(result);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "2", false);

        return input;
    }
}