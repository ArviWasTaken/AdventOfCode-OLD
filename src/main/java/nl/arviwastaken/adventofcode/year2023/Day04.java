package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day04 extends Solution<Dictionary<Integer, List<List<Integer>>>> {

    @Override
    public void run() {
        Dictionary<Integer, List<List<Integer>>> input = prepareInput();
        System.out.println("Solution day 4, part 1: " + part1(input));
        System.out.println("Solution day 4, part 2: " + part2(input));
    }

    @Override
    public String part1(Dictionary<Integer, List<List<Integer>>> input) {
        Integer total = 0;

        for (int i = 1; i <= input.size(); i ++) {
            int running = 0;
            List<List<Integer>> nums = input.get(i);
            for (Integer num: nums.get(1)
                 ) {
                if (nums.get(0).contains(num)) {
                    if (running == 0) {
                        running = 1;
                    } else {
                        running = running * 2;
                    }
                }
            }
            total += running;
        }

        return String.valueOf(total);
    }

    @Override
    public String part2(Dictionary<Integer, List<List<Integer>>> input) {
        Integer total = 0;

        // GameNum, [amountOfWins, instances]
        Dictionary<Integer, int[]> wins = new Hashtable<>();

        for (int i = 1; i <= input.size(); i ++) {
            int running = 0;
            List<List<Integer>> nums = input.get(i);
            for (Integer num: nums.get(1)
            ) {
                if (nums.get(0).contains(num)) {
                    running += 1;
                }
            }
            wins.put(i, new int[]{running, 1});
        }

        for (int i = 1; i <= wins.size(); i ++) {
            int[] val = wins.get(i);
            int amountOfWins = val[0];
            for (int j = 1; j <= amountOfWins; j ++) {
                int keyOfChanged = i + j;
                int[] valOfChanged = wins.get(keyOfChanged);
                valOfChanged[1] += val[1];
                wins.put(keyOfChanged, valOfChanged);
            }
        }

        for (int i = 1; i <= wins.size(); i ++) {
            int[] val = wins.get(i);
            total += val[1];
        }

        return String.valueOf(total);
    }

    @Override
    public Dictionary<Integer, List<List<Integer>>> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "4", false);
        Dictionary<Integer, List<List<Integer>>> output =  new Hashtable<>();
        // Put the code to convert from a list with lines to usuable objects here
        for (String s: input) {
            String[] game = s.split(": ");
            String[] gameNum = game[0].split(" ");
            String[] numbers = game[1].split(" \\| ");
            String[] winNum = numbers[0].split(" ");
            String[] myNum = numbers[1].split(" ");

            List<Integer> winInt = new ArrayList<>();
            for (String s1: winNum
                 ) {
                if (!s1.isBlank()) {
                    winInt.add(Integer.parseInt(s1));
            }}

            List<Integer> myInt = new ArrayList<>();
            for (String s1: myNum
            ) {
                if (!s1.isBlank()) {
                    myInt.add(Integer.parseInt(s1));
                }}
            List<List<Integer>> ints = new ArrayList<>();
            ints.add(winInt);
            ints.add(myInt);

            String gamerNummer = gameNum[gameNum.length - 1];

            output.put(Integer.parseInt(gamerNummer), ints);
        }

        return output;
    }
}