package nl.arviwastaken.adventofcode.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class InputUtil {
    private static final File resources = new File(System.getProperty("user.dir") + "/input");
    private static String session;

    public static List<String> getInput(String year, String day, Boolean example) {
        if (!yearExists(year)) {
            createFolder(year);
        }

        if (!inputExists(year, day)) {
            getFromAOC(year, day);
        }

        return getFromFile(year, day, example);
    }

    private static List<String> getFromFile(String year, String day, Boolean example) {
        List<String> input = new ArrayList<>();
        if (day.length() == 1) {
            day = "0" + day;
        }
        try {
            File inputFile;
            if (example) {
                inputFile = new File(getResources(), year + "/" + day + "/example" + day + ".txt");
            } else {
                 inputFile = new File(getResources(), year + "/" + day + "/input" + day + ".txt");
            }
            Scanner reader = new Scanner(inputFile);

            while (reader.hasNextLine()) {
                input.add(reader.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return input;
    }

    private static void createFolder(String year) {
        new File(getResources(), year).mkdir();
    }

    private static Boolean inputExists(String year, String day) {
        if (day.length() == 1) {
            day = "0" + day;
        }
        return new File(getResources(), year + "/" + day + "/input" + day + ".txt").exists();
    }

    private static Boolean yearExists(String year) {
        return new File(getResources(), year).exists();
    }

    //Code gotten from https://github.com/UnderKoen/AdventOfCode/blob/master/src/main/java/nl/underkoen/adventofcode/solutions/SolutionUtils.java and slightly edited
    private static void getFromAOC(String year, String day) {
        try {
            String fixedDay = day;
            if (day.length() == 1) {
                 fixedDay = "0" + day;
            }


            new File(getResources(), year + "/" + fixedDay).mkdir();
            new File(getResources(), year + "/" + fixedDay + "/example" + fixedDay + ".txt").createNewFile();
            File input = new File(System.getProperty("user.dir") + "/input/" + year + "/" + fixedDay + "/input" + fixedDay + ".txt");
            input.createNewFile();


            String url = String.format("https://adventofcode.com/%s/day/%s/input", year, day);

            HttpGet httpGet = new HttpGet(url);
            if (session == null) session = getSession();

            httpGet.addHeader("cookie", String.format("session=%s", session));
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpEntity entity = httpclient.execute(httpGet).getEntity();

                FileWriter fileWriter = new FileWriter(input);
                fileWriter.write(EntityUtils.toString(entity));
                fileWriter.flush();
                fileWriter.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String getSession() throws IOException {
        File session = new File(getResources(), "session.txt");
        if (!session.exists()) {
            if (!session.createNewFile()) System.err.println("Couldn't create input file");
            System.err.println("Created input/session.txt please fill this with your session key");
            System.exit(-1);
        }

        try (Scanner scanner = new Scanner(new FileInputStream(session))) {
            if (!scanner.hasNextLine()) {
                System.err.println("Please fill input/session.txt with your session key");
                System.exit(-1);
            }
            return scanner.next();
        }
    }

    private static File getResources() {
        if (!resources.exists() && !resources.mkdir()) throw new IllegalArgumentException("Cant create input folder");
        return resources;
    }
}
