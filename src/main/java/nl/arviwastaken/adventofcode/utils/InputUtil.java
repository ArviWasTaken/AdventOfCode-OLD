package nl.arviwastaken.adventofcode.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputUtil {

    public static String getInputFromFile(String dag) {
        if (dag.length() == 1) {
            dag = "0" + dag;
        }

        StringBuilder data = new StringBuilder();

        try {
            File inputFile = new File(System.getProperty("user.dir") + "/src/main/java/nl.arviwastaken.adventofcode.year2021/day" + dag + "/PuzzleInput-01.txt");
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                data.append(reader.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return data.toString();
    }
}
