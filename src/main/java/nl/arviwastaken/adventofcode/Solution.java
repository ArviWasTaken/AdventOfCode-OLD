package nl.arviwastaken.adventofcode;

public abstract class Solution {

     public static void main(String[] args) throws Exception {
         String clsN = System.getProperty("sun.java.command");
         clsN = clsN.split(" ")[0];
         if (clsN.contains("/")) clsN = clsN.split("/")[1];

         Class<?> cls = Class.forName(clsN, false, Thread.currentThread().getContextClassLoader());
         if (Solution.class.isAssignableFrom(cls)) {
             Class<? extends Solution> day = (Class<? extends Solution>) cls;
             Solution solution = day.getConstructor().newInstance();
             solution.run();
         }
         System.exit(0);

     }

    public abstract void run();

    public abstract String part1(Object input);

    public abstract String part2(Object input);

    public abstract Object prepareInput();


}
