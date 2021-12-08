package nl.arviwastaken.adventofcode.year2021;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;
import org.apache.commons.codec.binary.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day04 extends Solution<List<Board>> {

    private List<Integer> randomNumbers = new ArrayList<>();

    @Override
    public void run() {
        List<Board> input = prepareInput();
        System.out.println("Solution day 4, part 1: " + part1(input));
        System.out.println("Solution day 4, part 2: " + part2(input));
    }

    @Override
    public String part1(List<Board> input) {
        // Put your solution for part 1 here
        return null;
    }

    @Override
    public String part2(List<Board> input) {
        // Put your solution for part 2 here
        return null;
    }

    @Override
    public List<Board> prepareInput() {
        List<String> input = InputUtil.getInput("2021", "4", true);


        for (String s : input.get(0).split(",")) {
            Integer number = Integer.parseInt(s);
            randomNumbers.add(number);
        }

        // Creating a list of boards
        List<Board> output = new ArrayList<>();
        for (int j = 1; j < input.size(); j++) {
            List<String> board = new ArrayList<>();
            for (int i = j + 1; i < j + 6; i++) {
                board.add(input.get(i));
            }
            Board boardObject = new Board(board);
            output.add(boardObject);
            j += 5;
        }
        System.out.println(output);
        return output;
    }
}

class Board {
    int[][] numbers = new int[5][5];
    Boolean[][] checked = new Boolean[5][5];

    public Board(List<String> rows) {
        Integer curRow = 4;
        for (String row : rows) {
            row = row.strip();
            row = row.replace("  ", ";");
            row = row.replace(" ", ";");
            String[] columns = row.split(";");
            for (int i = 0; i < 5; i++) {
                numbers[i][curRow] = Integer.parseInt(columns[i]);
                checked[i][curRow] = false;
            }
            curRow--;
        }
    }

    public void checkNumber(Integer number) {
        for (int y = 4; y > -1; y--) {
            for (int x = 0; x < 5; x++) {
                if (numbers[x][y] == number) {
                    checked[x][y] = true;
                }
            }
        }
    }

//    public Boolean checkBingo() {
//
//    }
//
//    private Boolean checkRowBingo() {
//
//    }
//
//    private Boolean checkColumnBingo() {
//
//    }
//
//    public Integer getSumUnchecked() {
//
//    }

    @Override
    public String toString() {
        StringBuilder gridString = new StringBuilder();
        for (int y = 4; y > -1; y--) {
            for (int x = 0; x < 5; x++) {
                gridString
                        .append(numbers[x][y])
//                        .append(":")
//                        .append(checked[x][y])
                        .append(" ");
            }
            gridString.append("\n");
        }



        return "Board{\n" +
                gridString +
                '}';
    }
}