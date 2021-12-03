package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 3, part 1: " + part1(input));
        System.out.println("Solution day 3, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        List<Integer> epsilon1 = new ArrayList<>();
        List<Integer> epsilon0 = new ArrayList<>();

        for (int i = 0; i < input.get(0).length(); i++) {
            epsilon0.add(0);
            epsilon1.add(0);
        }

        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    int epsilon = epsilon1.get(j);
                    epsilon++;
                    epsilon1.set(j, epsilon);
                } else {
                    int epsilon = epsilon0.get(j);
                    epsilon++;
                    epsilon0.set(j, epsilon);
                }
            }
        }

        StringBuilder epsilonbuilder = new StringBuilder();
        StringBuilder gammaBuilder = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i++) {
            if (epsilon1.get(i) > epsilon0.get(i)) {
                epsilonbuilder.append("1");
                gammaBuilder.append("0");
            } else {
                epsilonbuilder.append("0");
                gammaBuilder.append("1");
            }
        }

        int epsilon = Integer.parseInt(epsilonbuilder.toString(), 2);
        int gamma = Integer.parseInt(gammaBuilder.toString(), 2);
        return String.valueOf(epsilon * gamma);
    }

    @Override
    public String part2(List<String> input) {
        List<String> oxygen = new ArrayList<>(input);
        List<String> co2 = new ArrayList<>(input);

        for (int i = 0; i < oxygen.get(0).length(); i++) {
            Iterator<String> oxgeni = oxygen.iterator();

            List<Integer> oxygenpositionCommon1Counter = new ArrayList<>();
            List<Integer> oxygenpositionCommon0Counter = new ArrayList<>();

            for (int j = 0; j < oxygen.get(0).length(); j++) {
                oxygenpositionCommon0Counter.add(0);
                oxygenpositionCommon1Counter.add(0);
            }

            for (int j = 0; j < oxygen.size(); j++) {
                String s = oxygen.get(j);
                for (int z = 0; z < s.length(); z++) {
                    if (s.charAt(z) == '1') {
                        int epsilon = oxygenpositionCommon1Counter.get(z);
                        epsilon++;
                        oxygenpositionCommon1Counter.set(z, epsilon);
                    } else {
                        int epsilon = oxygenpositionCommon0Counter.get(z);
                        epsilon++;
                        oxygenpositionCommon0Counter.set(z, epsilon);
                    }
                }
            }

            if (oxygenpositionCommon1Counter.get(i) >= oxygenpositionCommon0Counter.get(i)) {
                while (oxgeni.hasNext()) {
                    String s = oxgeni.next();

                    if (oxygen.size() == 1) {
                        break;
                    }
                    if (s.charAt(i) != '1') {
                        oxgeni.remove();
                    }
                }

            } else {
                while (oxgeni.hasNext()) {
                    String s = oxgeni.next();

                    if (oxygen.size() == 1) {
                        break;
                    }
                    if (s.charAt(i) == '1') {
                        oxgeni.remove();
                    }
                }
            }

            Iterator<String> co2i = co2.iterator();

            List<Integer> co2positionCommon1Counter = new ArrayList<>();
            List<Integer> co2positionCommon0Counter = new ArrayList<>();

            for (int j = 0; j < co2.get(0).length(); j++) {
                co2positionCommon0Counter.add(0);
                co2positionCommon1Counter.add(0);
            }

            for (int j = 0; j < co2.size(); j++) {
                String s = co2.get(j);
                for (int z = 0; z < s.length(); z++) {
                    if (s.charAt(z) == '1') {
                        int epsilon = co2positionCommon1Counter.get(z);
                        epsilon++;
                        co2positionCommon1Counter.set(z, epsilon);
                    } else {
                        int epsilon = co2positionCommon0Counter.get(z);
                        epsilon++;
                        co2positionCommon0Counter.set(z, epsilon);
                    }
                }
            }

            if (co2positionCommon1Counter.get(i) >= co2positionCommon0Counter.get(i)) {
                while (co2i.hasNext()) {
                    String s = co2i.next();

                    if (co2.size() == 1) {
                        break;
                    }

                    if (s.charAt(i) == '1') {
                        co2i.remove();
                    }
                }
            } else {
                while (co2i.hasNext()) {
                    String s = co2i.next();

                    if (co2.size() == 1) {
                        break;
                    }

                    if (s.charAt(i) != '1') {
                        co2i.remove();
                    }
                }
            }
        }

        int oxygendec = Integer.parseInt(oxygen.get(0), 2);
        int co2dec = Integer.parseInt(co2.get(0), 2);

        return String.valueOf(oxygendec * co2dec);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "3", false);
        // Put the code to convert from a list with lines to usuable objects here

        return input;
    }
}