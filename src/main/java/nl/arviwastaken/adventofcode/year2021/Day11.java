package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;
import nl.arviwastaken.adventofcode.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day11 extends Solution<Integer[][]> {

    @Override
    public void run() {
        System.out.println("Solution day 11, part 1: " + part1(prepareInput()));
        System.out.println("Solution day 11, part 2: " + part2(prepareInput()));
    }

    @Override
    public String part1(Integer[][] input) {
        Integer steps = 100;
        Integer counter = 0;
        for (int i = 0; i < steps; i++) {
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input.length; x++) {
                    input[x][y] = input[x][y] + 1;
                }
            }

            List<Position> flashed = new ArrayList<>();
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input.length; x++) {
                    flashed = check(input, x, y, flashed);
                }
            }


            for (Position p :
                    flashed) {
                input[p.x][p.y] = 0;
                counter++;
            }
        }
        return String.valueOf(counter);
    }

    @Override
    public String part2(Integer[][] input) {
        Integer counter = 0;
        while (true) {
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input.length; x++) {
                    input[x][y] = input[x][y] + 1;
                }
            }

            List<Position> flashed = new ArrayList<>();
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input.length; x++) {
                    flashed = check(input, x, y, flashed);
                }
            }

            for (Position p :
                    flashed) {
                input[p.x][p.y] = 0;
            }


            counter++;
            Boolean asyc = false;
            for (int y = 0; y < input[0].length; y++) {
                for (int x = 0; x < input.length; x++) {
                    if (input[x][y] != 0) {
                        asyc = true;
                    }
                }
            }
            if (!asyc) {
                return String.valueOf(counter);
            }
        }
    }

    @Override
    public Integer[][] prepareInput() {
        List<String> input = InputUtil.getInput("2021", "11", false);
        Integer[][] output = new Integer[input.get(0).length()][input.size()];

        for (int y = 0; y < input.size(); y++) {
            String row = input.get(y);
            for (int x = 0; x < row.length(); x++) {
                output[x][y] = Integer.parseInt(String.valueOf(row.charAt(x)));
            }
        }

        return output;
    }

    private List<Position> check(Integer[][] input, Integer x, Integer y, List<Position> flashed) {
        if (input[x][y] > 9 && !flashed.contains(new Position(x, y))) {

            flashed.add(new Position(x, y));

            List<Position> positionsToCheck = new ArrayList<>();
            //middle
            if (0 < x && x < input[0].length - 1 && 0 < y && y < input.length - 1) {
                for (int j = y - 1; j < y + 2; j++) {
                    for (int k = x - 1; k < x + 2; k++) {
                        positionsToCheck.add(new Position(k, j));
                    }
                }
            } else
                // corners
                if (x == 0 && y == 0) {
                    for (int j = y; j < y + 2; j++) {
                        for (int k = x; k < x + 2; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (x == 0 && y == input[0].length - 1) {
                    for (int j = y - 1; j < y + 1; j++) {
                        for (int k = x; k < x + 2; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (x == input.length - 1 && y == input[0].length - 1) {
                    for (int j = y - 1; j < y + 1; j++) {
                        for (int k = x - 1; k < x + 1; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (x == input.length - 1 && y == 0) {
                    for (int j = y; j < y + 2; j++) {
                        for (int k = x - 1; k < x + 1; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                }
                //sides
                else if (x == 0) {
                    for (int j = y - 1; j < y + 2; j++) {
                        for (int k = x; k < x + 2; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (x == input.length - 1) {
                    for (int j = y - 1; j < y + 2; j++) {
                        for (int k = x - 1; k < x + 1; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (y == 0) {
                    for (int j = y; j < y + 2; j++) {
                        for (int k = x - 1; k < x + 2; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                } else if (y == input[0].length - 1) {
                    for (int j = y - 1; j < y + 1; j++) {
                        for (int k = x - 1; k < x + 2; k++) {
                            positionsToCheck.add(new Position(k, j));
                        }
                    }
                }

            // check neigbours
            for (Position p : positionsToCheck
            ) {
                if (!(Objects.equals(x, p.x) && Objects.equals(y, p.y))) {
                    input[p.x][p.y] = input[p.x][p.y] + 1;
                }
                if (input[p.x][p.y] > 9) {
                    check(input, p.x, p.y, flashed);
                }
            }
        }
        return flashed;
    }
}