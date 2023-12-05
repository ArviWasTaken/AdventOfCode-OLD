package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 extends Solution<List<String>>{

@Override
public void run(){
    List<String> input=prepareInput();
        System.out.println("Solution day 5, part 1: "+part1(input));
        System.out.println("Solution day 5, part 2: "+part2(input));
        }

@Override
public String part1(List<String> input){
        // Put your solution for part 1 here
    List<Long> seeds = new ArrayList<>();
    String temp = input.get(0).split("seeds: ")[1];
    String[] temp1 = temp.split(" ");
    for (String s: temp1
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
                        locations[i] = des + ( range - indexInRange) - 1;
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
public String part2(List<String> input){
        // Put your solution for part 2 here
    List<long[]> startingRanges = new ArrayList<>();
    String temp = input.get(0).split("seeds: ")[1];
    String[] temp1 = temp.split(" ");

    for (int i = 0; i < temp1.length; i+=2) {
        startingRanges.add(new long[]{Long.parseLong(temp1[i]), Long.parseLong(temp1[i + 1])});
    }

    List<long[]> currentMap = new ArrayList<>();

    // set future to starting
    List<long[]> futureMap = new ArrayList<>(startingRanges);

    // loop trough whole almanac and per map adjust the future map
    for (int nr = 1; nr < input.size(); nr++) {
        String line = input.get(nr);
        // Skip all empty lines
        if (!line.isBlank()) continue;

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

        // For every entry in the current map we are see if it applies and then update the part
        for (int i = 0; i < currentMap.size(); i++) {
            // find overlap in currentmap, if any
            List<long[]> overlap = findOverlap();
            // if Overlap is found:
            if (overlap != null) {
                // put overlap part in futuremap
                futureMap.add(overlap.get(0));
                // put non overlapping parts back into current map to be altered by another rule
                for (int j = 1; j < overlap.size(); j++) {
                    currentMap.add(overlap.get(j));
                }
            }
        }
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

    private void findOverlap(long[] current, long startSource, long startDestination, long range) {
        // for each we want to see if they overlap if so get the range that overlaps and add that to futureMap
        // Four cases exists
        // 1st case if no overlap between sets:     ( top1 < bot2 || bot1 > top2 ) change nothing
        // 2nd case if top of set 1 overlaps set2:  ( bot1 < bot2 && top1 <= top2 ) change top of s1 to output of s2
        // 3rd case if bottom of set1 overlaps set2 ( bot1 >= bot2 && top1 > top2 ) change bot of s1 to out of s2
        // 4th case if set1 is contained in set2    ( bot1 >= bot2 && top1 <= top2 ) change mathing set of s1 to out of s2
        // 5th case if set2 is cvontained in set1   ( ...
        long s1_bot = currentMap.get(i)[0]; // Set bottom
        long s1_top = currentMap.get(i)[0] + currentMap.get(i)[1] - 1; // set top

        long s2_bot = source; // get bottom of update to mapping
        long s2_top = source + range - 1; // get top of update to mapping

        if (s1_bot < s2_bot && s1_top <= s2_top && s1_top > s2_bot) {
            // top of current overlaps over a set of the update
            // long[] updatedPart = new long[]{s2_bot, s1_top - s2_bot + 1};
            long[] updatedPart =  new long[]{des, s1_top - s2_bot + 1};
            long[] oldPart = new long[]{s1_bot, s2_bot - s1_bot + 1};

            // add updated to future
            futureMap.add(updatedPart);
            currentMap.add(oldPart);

        } else if (s1_bot >= s2_bot && s1_top > s2_top && s1_bot < s2_top) {
            // bot of current overlaps a set of the update
//                       long[] updatedPart = new long[]{s1_bot, s2_top - s1_bot + 1};
            long[] updatedPart =  new long[]{des + s1_bot - s2_bot, s2_top - s1_bot + 1};
            long[] oldPart = new long[]{s2_top + 1, s1_top - s2_top};

            // add updated to future
            futureMap.add(updatedPart);
            currentMap.add(oldPart);

        } else if ( s1_bot <= s2_bot && s1_top >= s2_top) {
            // a part of current is updated
//                       long[] updatedPart = new long[]{s2_bot, s2_top - 1};
            long[] updatedPart =  new long[]{des, s2_top + 1};

            long[] oldPartBot = new long[]{s1_bot, s2_bot - s1_bot};
            long[] oldPartTop = new long[]{s2_top + 1, s1_top - s2_top};

            // add old parts back to current
            currentMap.add(oldPartBot);
            currentMap.add(oldPartTop);

            // add updated to future
            futureMap.add(updatedPart);
        } else if (s1_bot > s2_bot && s1_top < s2_top) {
            // full current is updated
//                        long[] updatedPart = new long[]{s1_bot, s1_top -1 };
            long[] updatedPart =  new long[]{des + s1_bot - s2_bot, range};

            // add old parts to current

            // add updated to future
            futureMap.add(updatedPart);
        }

//                    long indexInRange = topvalue - currentMap[i];
//
//                    if (indexInRange >= 0 && indexInRange < range) {
//                        futureMap[i] = des + ( range - indexInRange) - 1;
//                    }
    }
}