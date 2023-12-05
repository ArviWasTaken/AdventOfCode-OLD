package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 5, part 1: " + part1(input));
        System.out.println("Solution day 5, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        // Put your solution for part 1 here
        List<Long> seeds = new ArrayList<>();
        String temp = input.get(0).split("seeds: ")[1];
        String[] temp1 = temp.split(" ");
        for (String s : temp1
        ) {
            seeds.add(Long.parseLong(s));
        }

        long[] locations = new long[seeds.size()];
        long[] nextSource = new long[seeds.size()];

        for (int i = 0; i < seeds.size(); i++) {
            locations[i] = seeds.get(i);
        }

        for (int nr = 1; nr < input.size(); nr++) {
            String line = input.get(nr);
            if (!line.isBlank()) {
                if (Character.isDigit(line.charAt(0))) {
                    String[] nums = line.split(" ");
                    long des = Long.parseLong(nums[0]);
                    long source = Long.parseLong(nums[1]);
                    long range = Long.parseLong(nums[2]);

                    long topvalue = source + range - 1;

                    for (int i = 0; i < nextSource.length; i++) {
                        long indexInRange = topvalue - nextSource[i];

                        if (indexInRange >= 0 && indexInRange < range) {
                            locations[i] = des + (range - indexInRange) - 1;
                        }

                    }
                } else {
                    for (int i = 0; i < locations.length; i++) {
                        nextSource[i] = locations[i];
                    }

                }
            }
        }

        long low = locations[0];
        for (long i : locations
        ) {
            if (i < low) {
                low = i;
            }
        }

        return String.valueOf(low);
    }

    @Override
    public String part2(List<String> input) {
        // Put your solution for part 2 here
        List<long[]> startingRanges = new ArrayList<>();
        String temp = input.get(0).split("seeds: ")[1];
        String[] temp1 = temp.split(" ");

        for (int i = 0; i < temp1.length; i += 2) {
            startingRanges.add(new long[]{Long.parseLong(temp1[i]), Long.parseLong(temp1[i + 1])});
        }

        List<long[]> currentMap = new ArrayList<>();

        // set future to starting
        List<long[]> futureMap = new ArrayList<>(startingRanges);

        // loop trough whole almanac and per map adjust the future map
        for (int nr = 1; nr < input.size(); nr++) {
            String line = input.get(nr);
            // Skip all empty lines
            if (line.isBlank()) continue;

            // If the first char is not a digit we are at the start of a new alteration and we need to update the current mapping
            if (!Character.isDigit(line.charAt(0))) {
                currentMap.addAll(futureMap);
                futureMap.clear();
                continue;
            }

            String[] nums = line.split(" ");
            long des = Long.parseLong(nums[0]);
            long source = Long.parseLong(nums[1]);
            long range = Long.parseLong(nums[2]);

            List<long[]> remainders = new ArrayList<>();

            // For every entry in the current map we are see if it applies and then update the part
            Iterator<long[]> currentmapIterator = currentMap.iterator();
            while (currentmapIterator.hasNext()) {
                // find overlap in currentmap, if any
                List<long[]> overlap = findOverlap(currentmapIterator.next(), source, des, range);
                // if Overlap is found:
                if (overlap != null) {
                    // put overlap part in futuremap
                    futureMap.add(overlap.get(0));

                    // put non overlapping parts into remainders list to be examined by another rule
                    for (int j = 1; j < overlap.size(); j++) {
                        remainders.add(overlap.get(j));
                    }

                    // remove this range since it has been broken up
                    currentmapIterator.remove();
                }
            }

            // After all items in the currentmap have been checked add remainders for next line
            currentMap.addAll(remainders);

        }
        currentMap.addAll(futureMap);

        // To find lowest cycle over every starting position of a range
        long low = futureMap.get(0)[0];
        for (long[] i : futureMap
        ) {
            if (i[0] < low) {
                low = i[0];
            }
        }
        return String.valueOf(low);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "5", true);
        // Put the code to convert from a list with lines to usuable objects here

        return input;
    }

    private List<long[]> findOverlap(long[] current, long source_start, long destination, long range) {
        // check if two ranges overlap by x1 <= y2 && y1 <= x2

        // start and end of both ranges
        long current_start = current[0];
        long current_end = current_start + current[1] - 1;

        long source_end = source_start + range - 1;

        // Check if there is any overlap
        if (!(current_start <= source_end && source_start <= current_end)) return null;

        // Calculate overlap
        long overlap_start = Math.max(current_start, source_start);
        long overlap_end = Math.min(current_end, source_end);

        long overlap_range = overlap_end - overlap_start + 1;
        long overlap_destination_start = overlap_start - source_start;

        long[] overlap = new long[]{destination + overlap_destination_start, overlap_range};

        List<long[]> result = new ArrayList<>();
        result.add(overlap);

        // calculate left overlapping bit
        if (current_start < overlap_start) {
            long[] remainder_left = new long[]{ current_start, overlap_start + 1};
            result.add(remainder_left);
        }

        // calculate right overlapping bit
        if (current_end > overlap_end) {
            long[] remainder_right = new long[]{ overlap_end + 1, current_end - overlap_end + 1};
            result.add(remainder_right);
        }

        return result;
    }
}