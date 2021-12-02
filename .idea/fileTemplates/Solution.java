#set($String = "abc")
#set($Int = 10)
#set($NAME = $String.format("Day%02d", $Int.parseInt($Day)))
#set($Year = $PACKAGE_NAME.split("year")[1])
#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end


import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;
import java.util.List;
import java.util.stream.Collectors;

#parse("File Header.java")
public class ${NAME} extends Solution<${OutputObjectType}> {

    @Override
    public void run() {
        ${OutputObjectType} input = prepareInput();
        System.out.println("Solution day ${Day}, part 1: " + part1(input));
        System.out.println("Solution day ${Day}, part 2: " + part2(input));
    }

    @Override
    public String part1(${OutputObjectType} input) {
        // Put your solution for part 1 here
        return null;
    }

    @Override
    public String part2(${OutputObjectType} input) {
        // Put your solution for part 2 here
        return null;
    }

    @Override
    public ${OutputObjectType} prepareInput() {
        List<String> input = InputUtil.getInput("${Year}", "${Day}", false);
        ${OutputObjectType} output;
        // Put the code to convert from a list with lines to usuable objects here
        
        #if (${OutputObjectType} == "List<Integer>")output = input.stream().map(Integer::parseInt).collect(Collectors.toList());#end
        return output;
    }
}