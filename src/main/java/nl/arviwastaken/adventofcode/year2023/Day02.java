package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
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
        Dictionary<String, Integer> look = new Hashtable<>();
        look.put("red", 12);
        look.put("green", 13);
        look.put("blue", 14);
        int total = 0;

        for (int i = 0; i < input.size(); i++) {
            boolean possible = true;
            String[] game = input.get(i).split(":");
            String[] turn = game[1].split(";");

            for (int j = 0; j < turn.length; j++) {
                String[] cubes = turn[j].split(", ");

                for (int k = 0; k < cubes.length; k++) {
                    String[] split = cubes[k].trim().split(" ");

                    if (look.get(split[1]) < Integer.parseInt(split[0])) {
                        possible = false;
                    }
                }
            }

            if (possible) {
                total += Integer.parseInt(game[0].split(" ")[1]);
            }
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        int total = 0;

        for (int i = 0; i < input.size(); i++) {
            Dictionary<String, Integer> look = new Hashtable<>();
            look.put("red", 0);
            look.put("green", 0);
            look.put("blue", 0);

            String[] game = input.get(i).split(":");
            String[] turn = game[1].split(";");

            for (int j = 0; j < turn.length; j++) {
                String[] cubes = turn[j].split(", ");

                for (int k = 0; k < cubes.length; k++) {
                    String[] split = cubes[k].trim().split(" ");

                    if (look.get(split[1]) < Integer.parseInt(split[0])) {
                        look.put(split[1], Integer.parseInt(split[0]));
                    }
                }
            }
            total += look.get("green") * look.get("red") *look.get("blue");
        }
        return String.valueOf(total);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "2", false);
        List<String> output;
        // Put the code to convert from a list with lines to usuable objects her

        return input;
    }
}