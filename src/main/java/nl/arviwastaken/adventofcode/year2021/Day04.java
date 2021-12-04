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
        List<String> input = InputUtil.getInput("2021", "4", false);


        for (String s : input.get(0).split(",")) {
            Integer number = Integer.parseInt(s);
            randomNumbers.add(number);
        }

        // Creating a list of boards
        List<Board> output = new ArrayList<>();
        for (int j = 1; j < input.size(); j++) {
            List<String> board = new ArrayList<>();
            for (int i = j + 1; i < j + 5; i++) {
                board.add(input.get(i));
            }
            Board boardObject = new Board(board);
            output.add(boardObject);
            j += 5;
        }

        return output;
    }
}

class Board {
    Map<Map<Integer, Integer>, Integer> grid = new HashMap<>();
    Integer columnCount;
    Integer rowCount;

    public Board(List<String> rows) {
        rowCount = rows.size();
        Integer curRow = rowCount;
        for (String row : rows) {
            row = row.strip();
            row = row.replace("  ", ";");
            row = row.replace(" ", ";");

            String[] columns = row.split(";");
            columnCount = columns.length;
            System.out.println("row: " + row);
            for (int i = 1; i < columnCount + 1; i++) {
                Map<Integer, Integer> curPositon = new HashMap<>();
                curPositon.put(i, curRow);
                Integer value = Integer.parseInt(columns[i - 1]);

                System.out.println(curPositon + " " + value);
                grid.put(curPositon, value);
            }
            curRow--;
        }
    }

    public Map<Integer, Integer> containsnumber(Integer number) {
        return null;
    }

    @Override
    public String toString() {
        return "Board{" +
                "grid=" + grid +
                ", columnCount=" + columnCount +
                ", rowCount=" + rowCount +
                '}';
    }
}

class BingoNumber {
    Integer number;
    boolean checked = false;

    public BingoNumber(Integer number) {
        this.number = number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void check() {
        this.checked = true;
    }
}