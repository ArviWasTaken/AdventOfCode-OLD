package nl.arviwastaken.adventofcode.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputUtil {

    public static List<String> getInput(String year, String day) {
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (!yearExists(year)) {
            createFolder(year);
        }

        if (!inputExists(year, day)) {
            getFromInternet(year, day);
        }

        return getFromFile(year, day);
    }

    private static List<String> getFromFile(String year, String day) {
        List<String> input = new ArrayList<>();
        try {
            File inputFile = new File(System.getProperty("user.dir") + "/input/" + year + "/" + day + ".txt");
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                input.add(reader.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return input;
    }

    private static List<String> getFromInternet(String year, String dag) {

        // Code to get from internet

        return getFromFile(year, dag);
    }

    private static void createFolder(String year) {
        try {
            new File(System.getProperty("user.dir") + "/input/" + year).createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static Boolean inputExists(String year, String day) {
        return new File(System.getProperty("user.dir") + "/input/" + year + "/" + day + ".txt").exists();
    }

    private static Boolean yearExists(String year) {
        return new File(System.getProperty("user.dir") + "/input/" + year).exists();
    }
}
