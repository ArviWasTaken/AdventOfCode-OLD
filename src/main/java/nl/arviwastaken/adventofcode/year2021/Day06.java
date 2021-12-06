package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Integer, Integer> timerAmountWithFish = new HashMap<>();

        for (String s: input
             ) {
            Integer fish = Integer.parseInt(s);
            if (!timerAmountWithFish.containsKey(fish)) {
                timerAmountWithFish.put(fish, 1);
            } else {
                timerAmountWithFish.put(fish, timerAmountWithFish.get(fish) + 1);
            }
        }

        for (int i = 0; i < 80; i++) {
            Map<Integer, Integer> newday = new HashMap<>();
            for(Map.Entry<Integer, Integer> entry : timerAmountWithFish.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();

                if (key == 0) {
                    // reset cycle of fish
                    if (!newday.containsKey(6)) {
                        newday.put(6, value);
                    } else {
                        newday.put(6, newday.get(6) + value);
                    }
                    // add new fish
                    if (!newday.containsKey(8)) {
                        newday.put(8, value);
                    } else {
                        newday.put(8, newday.get(8) + value);
                    }
                } else {
                    if (!newday.containsKey(key - 1)) {
                        newday.put(key - 1, value);
                    } else {
                        newday.put(key - 1, newday.get(key - 1) + value);
                    }
                }
            }
            timerAmountWithFish = newday;
        }

        Integer counter = 0;
        for(Map.Entry<Integer, Integer> entry : timerAmountWithFish.entrySet()) {
            Integer value = entry.getValue();
            counter += value;
        }

        return counter.toString();
    }

    @Override
    public String part2(List<String> input) {
        Map<Integer, Long> timerAmountWithFish = new HashMap<>();

        for (String s: input
        ) {
            Integer fish = Integer.parseInt(s);
            if (!timerAmountWithFish.containsKey(fish)) {
                timerAmountWithFish.put(fish, 1L);
            } else {
                timerAmountWithFish.put(fish, timerAmountWithFish.get(fish) + 1);
            }
        }

        for (int i = 0; i < 256; i++) {
            Map<Integer, Long> newday = new HashMap<>();
            for(Map.Entry<Integer, Long> entry : timerAmountWithFish.entrySet()) {
                Integer key = entry.getKey();
                Long value = entry.getValue();

                if (key == 0) {
                    // reset cycle of fish
                    if (!newday.containsKey(6)) {
                        newday.put(6, value);
                    } else {
                        newday.put(6, newday.get(6) + value);
                    }
                    // add new fish
                    if (!newday.containsKey(8)) {
                        newday.put(8, value);
                    } else {
                        newday.put(8, newday.get(8) + value);
                    }
                } else {
                    if (!newday.containsKey(key - 1)) {
                        newday.put(key - 1, value);
                    } else {
                        newday.put(key - 1, newday.get(key - 1) + value);
                    }
                }
            }
            timerAmountWithFish = newday;
        }

        System.out.println(timerAmountWithFish);

        Long counter = 0L;
        for(Map.Entry<Integer, Long> entry : timerAmountWithFish.entrySet()) {
            Long value = entry.getValue();
            counter += value;
        }

        return counter.toString();
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "6", false);

        List<String> output = List.of(input.get(0).split(","));

        return output;
    }


}