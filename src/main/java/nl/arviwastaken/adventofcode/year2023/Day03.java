package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class Day03 extends Solution<Character[][]> {

    @Override
    public void run() {
        Character[][] input = prepareInput();
        System.out.println("Solution day 3, part 1: " + part1(input));
        System.out.println("Solution day 3, part 2: " + part2(input));
    }

    @Override
    public String part1(Character[][] input) {
        // Put your solution for part 1 here
        int total = 0;
        int y_len = input[0].length;
        int x_len = input.length;
        for (int y = 0; y < y_len; y++) {
            for (int x = 0; x < x_len; x++) {
                Character c = input[x][y];
                if (Character.isDigit(c)) {
                    int pointer = 0;
                    // find length of number
                    while (pointer < x_len && pointer + x < x_len && Character.isDigit(input[pointer + x][y]) ) {
                        pointer++;
                    }
                    // if char is around add to total
                    if (findChars(input, x, y, pointer, x_len, y_len)) {

                        String number = "";
                        for (int i = 0; i < pointer; i++) {
                            number += input[x + i][y];
                        }

                        total += Integer.parseInt(number);
                    }
                    // add numlen to x to skip number
                    x += pointer;

                }
            }
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(Character[][] input) {
        // Put your solution for part 2 here
        int total = 0;
        int y_len = input[0].length;
        int x_len = input.length;
        List<Number> numbers = new ArrayList<>();

        // find all numbers
        for (int y = 0; y < y_len; y++) {
            for (int x = 0; x < x_len; x++) {
                Character c = input[x][y];
                if (Character.isDigit(c)) {
                    int pointer = 0;
                    String number = "";
                    // find length of number
                    while (pointer < x_len && pointer + x < x_len && Character.isDigit(input[pointer + x][y])) {
                        number += input[x + pointer][y];
                        pointer++;
                    }
                    numbers.add(new Number(Integer.parseInt(number), x, y, x + pointer -1));
                    x += pointer;
                }
            }
        }

        // per * check fi numbers are around
        for (int y = 0; y < y_len; y++) {
            for (int x = 0; x < x_len; x++) {
                if (input[x][y] == '*') {
                    List<Number> numbersFound = new ArrayList<>();
                    for (Number n: numbers
                         ) {
                        boolean yInRange = y - 1 <= n.y && n.y <= y + 1;
                        if ((x-1 <= n.x && n.x <= x+1) && yInRange || (x-1 <= n.last_x && n.last_x <= x+1) && yInRange) {
                            numbersFound.add(n);
                        }
                    }

                    numbersFound.forEach(number -> System.out.println(number.number));
                    if (numbersFound.size() == 2) {
                        total += numbersFound.get(0).number * numbersFound.get(1).number;
                    }
                    System.out.println();
                }
            }
        }

        return String.valueOf(total);
    }

    @Override
    public Character[][] prepareInput() {
        List<String> input = InputUtil.getInput("2023", "3", false);
        int x_length = input.get(0).length();
        int y_length = input.size();
        Character[][] output = new Character[x_length][y_length];
        // Put the code to convert from a list with lines to usuable objects here
        for (int y = 0; y < y_length; y++) {
            for (int x = 0; x < x_length; x++) {
                output[x][y] = input.get(y).charAt(x);
            }
        }

        return output;
    }

    private void print_grid(Character[][] grid) {
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }
    }

    private boolean findChars(Character[][] input, int x, int y, int numLen, int xlen, int ylen) {
        int x_end = x + numLen + 1;
        // middle of grid
        if (0 < x && x + numLen < xlen && 0 < y && y < ylen - 1) {
            for (int j = y - 1; j < y + 2; j++) {
                for (int k = x - 1; k < x_end; k++) {
                    if (isSymbol(input[k][j])) {
                        return true;
                    }
                }
            }
        } else {
            // corners
            if (x == 0 && y == 0) {
                for (int j = y; j < y + 2; j++) {
                    for (int k = x; k < x_end; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (x == 0 && y == ylen) {
                for (int j = y - 1; j < y + 1; j++) {
                    for (int k = x; k < x_end; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (x + numLen == xlen && y == ylen) {
                for (int j = y - 1; j < y + 1; j++) {
                    for (int k = x - 1; k < x + 1; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (x + numLen == xlen && y == 0) {
                for (int j = y; j < y + 2; j++) {
                    for (int k = x - 1; k < x + 1; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            }
            //sides
            else if (x == 0) {
                for (int j = y - 1; j < y + 2; j++) {
                    for (int k = x; k < x_end; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (x + numLen == xlen) {
                for (int j = y - 1; j < y + 2; j++) {
                    for (int k = x - 1; k < x + 1; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (y == 0) {
                for (int j = y; j < y + 2; j++) {
                    for (int k = x - 1; k < x_end; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            } else if (y == ylen -1) {
                for (int j = y - 1; j < y + 1; j++) {
                    for (int k = x - 1; k < x_end; k++) {
                        if (isSymbol(input[k][j])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isSymbol(Character c) {
        return !(Character.isDigit(c) || c == '.');
    }
}
class Number {
    final int number ;
    final int x;
    final int y;
    final int last_x;

    public Number(int number, int x, int y, int last_x) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.last_x = last_x;
    }
}