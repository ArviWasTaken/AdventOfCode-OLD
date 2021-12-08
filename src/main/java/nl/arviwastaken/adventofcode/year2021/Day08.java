package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 extends Solution<List<Day08.Entry>> {

    @Override
    public void run() {
        List<Entry> input = prepareInput();
        System.out.println("Solution day 8, part 1: " + part1(input));
        System.out.println("Solution day 8, part 2: " + part2(input));
    }

    @Override
    public String part1(List<Entry> input) {
        Integer unqiue = 0;
        for (Entry e: input
             ) {
            for (String s: e.output
                 ) {
                if (s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7) {
                    unqiue ++;
                }
            }
        }
        return unqiue.toString();
    }

    // 8     = 7 segments
    // 0,9,6 = 6 segments
    // 2,3,5 = 5 segments
    // 4     = 4 segemnts
    // 7     = 3 segments
    // 1     = 2 segments
    @Override
    public String part2(List<Entry> input) {
        Integer sum = 0;
        for (Entry e: input
        ) {
            // per entry sort signals into possible values base on number of sgements used
            Map<Integer, List<String>> segementsPerSignal = new HashMap<>();
            for (String s: e.output
            ) {
                Integer length = s.length();
                List<String> value;
                if (segementsPerSignal.containsKey(length)) {
                    value = segementsPerSignal.get(length);
                } else {
                    value = new ArrayList<>();
                }
                value.add(s);
                segementsPerSignal.put(length, value);
            }

            //Deduction below here
//            segments with key for "knowSegments" map
//            aaa       a = top
//           b   c      b = topLeft
//           b   c      c = topRight
//            ddd       d = mid
//           e   f      e = bottomLeft
//           e   f      f = bottomRight
//            ggg       g = bottom

            Map<String, Character> knowSegments = new HashMap<>();
            Set<Integer> counter = segementsPerSignal.keySet();
//            Rules:
//            - with a 7 and a 1 you know the top segment\
//
            if (counter.contains(2) && counter.contains(3)) {
                if (!knowSegments.containsKey("top")) {
// https://stackoverflow.com/questions/13429119/get-unique-values-from-arraylist-in-java/33735562 use this here
                    knowSegments.put("put", );
                }
            }

            //Actually calculating what the numbers are below here

        }
        return sum.toString();
    }

    @Override
    public List<Entry> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "8", true);
        List<Entry> output = new ArrayList<>();
        for (String s: input
             ) {
            output.add(new Entry(s));
        }
        System.out.println(output);

        return output;
    }

    class Entry {
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();

        public Entry(String s) {
            String[] split = s.split(" \\| ");
            String[] inputhalf = split[0].split(" ");
            String[] outputhalf = split[1].split(" ");
            for (String is: inputhalf
                 ) {
                char[] tempArray = is.toCharArray();

                Arrays.sort(tempArray);
                input.add(new String(tempArray));
            }
            input.sort(Comparator.comparing(String::length));

            for (String os: outputhalf
            ) {
                char[] tempArray = os.toCharArray();

                Arrays.sort(tempArray);
                output.add(new String(tempArray));
            }
            output.sort(Comparator.comparing(String::length));

        }

        @Override
        public String toString() {
            return "Entry{" +
                     input +
                    ", " + output +
                    '}';
        }
    }
}