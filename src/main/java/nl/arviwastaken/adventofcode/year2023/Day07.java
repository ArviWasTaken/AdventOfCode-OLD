package nl.arviwastaken.adventofcode.year2023;

import nl.arviwastaken.adventofcode.Solution;
import nl.arviwastaken.adventofcode.utils.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day07 extends Solution<List<String>> {

    @Override
    public void run() {
        List<String> input = prepareInput();
        System.out.println("Solution day 7, part 1: " + part1(input));
        System.out.println("Solution day 7, part 2: " + part2(input));
    }

    @Override
    public String part1(List<String> input) {
        List<Hand> output =  new ArrayList<>();
        // Put the code to convert from a list with lines to usuable objects here
        for (String s: input) {
            String[] split = s.split(" ");
            output.add(new Hand(split[0], Integer.parseInt(split[1]), calculateTypePart1(split[0])));
        }
        // Put your solution for part 1 here
        int inputLength = output.size();
        // sort list
        boolean changed = true;
        while (changed) {
            changed = false;
            // go trough entire list
            for (int i = 0; i < inputLength - 1; i++) {
                Hand current = output.get(i);
                Hand next = output.get(i + 1);

                // if current hand is stronger then next hand swap and put changed to true
                if (current.getValue() > next.getValue()) {
                    output.set(i, next);
                    output.set(i + 1, current);
                    changed = true;
                } else  if (current.getValue() == next.getValue()){
                    // it means the hand are of similair strength so now we gotta check all chars
                    for (int j = 0; j < 5; j++) {
                        char curChar = current.cards.charAt(j);
                        char nextChar = next.cards.charAt(j);

                        // continue till we find first difference
                        if (curChar == nextChar) continue;

                        // we only gotta do something if the currentcard is higher then nextCard
                        // if we get here both cards are a Letter and need to be compared
                        String letterOrder = "23456789TJQKA"; // Order of letters from lowest to highest value
                        if (letterOrder.indexOf(curChar) > letterOrder.indexOf(nextChar)) {
                            output.set(i, next);
                            output.set(i + 1, current);
                            changed = true;
                        }
                        break;
                    }
                }
            }
        }

        // calculate all winings
        int total = 0;
        for (int i = 0; i < inputLength; i++) {
            int curHandWin = output.get(i).bid * (i + 1);
            total += curHandWin;
        }
        return String.valueOf(total);
    }

    @Override
    public String part2(List<String> input) {
        // Put your solution for part 2 here
        List<Hand> output =  new ArrayList<>();
        // Put the code to convert from a list with lines to usuable objects here
        for (String s: input) {
            String[] split = s.split(" ");
            output.add(new Hand(split[0], Integer.parseInt(split[1]), calculateTypePart2(split[0])));
        }

        int inputLength = output.size();
        // sort list
        boolean changed = true;
        while (changed) {
            changed = false;
            // go trough entire list
            for (int i = 0; i < inputLength - 1; i++) {
                Hand current = output.get(i);
                Hand next = output.get(i + 1);

                // if current hand is stronger then next hand swap and put changed to true
                if (current.getValue() > next.getValue()) {
                    output.set(i, next);
                    output.set(i + 1, current);
                    changed = true;
                } else  if (current.getValue() == next.getValue()){
                    // it means the hand are of similair strength so now we gotta check all chars
                    for (int j = 0; j < 5; j++) {
                        char curChar = current.cards.charAt(j);
                        char nextChar = next.cards.charAt(j);

                        // continue till we find first difference
                        if (curChar == nextChar) continue;

                        // we only gotta do something if the currentcard is higher then nextCard
                        // if we get here both cards are a Letter and need to be compared
                        String letterOrder = "J23456789TQKA"; // Order of letters from lowest to highest value
                        if (letterOrder.indexOf(curChar) > letterOrder.indexOf(nextChar)) {
                            output.set(i, next);
                            output.set(i + 1, current);
                            changed = true;
                        }
                        break;
                    }
                }
            }
        }

        // calculate all winings
        int total = 0;
        for (int i = 0; i < inputLength; i++) {
            int curHandWin = output.get(i).bid * (i + 1);

            total += curHandWin;
        }
        return String.valueOf(total);
    }

    @Override
    public List<String> prepareInput() {
        List<String> input = InputUtil.getInput("2023", "7", false);

        return input;
    }

    public Type calculateTypePart1(String hand) {
       List<Character> checked = new ArrayList<>();
       List<Long> matched = new ArrayList<>();

       // for every card in hand count how many occurences are there
        for (int i = 0; i < hand.length(); i++) {
            Character c = hand.charAt(i);
            // if hand is already checked continue to next card in hand
            if (checked.stream().anyMatch(n -> (n.compareTo(c)) == 0)) continue;
            // add new card to checked
            checked.add(c);
            // count occurence of card and add to list
            matched.add(hand.chars().filter(ch -> (ch == c)).count());
        }

        if (matched.size() == 1) return Type.FIVEAKIND;
        if (matched.size() == 2) return matched.contains(4L) ? Type.FOURAKIND : Type.FULLHOUSE;
        if (matched.size() == 3) return matched.contains(3L) ? Type.THREEAKIND : Type.TWOPAIR;
        if (matched.size() == 4) return Type.ONEPAIR;
        if (matched.size() == 5) return Type.HIGHCARD;

        throw new RuntimeException("hand is longer then 5 cards");
    }

    public Type calculateTypePart2(String hand) {
        List<Character> checked = new ArrayList<>();

        HashMap<Character, Long> occurences = new HashMap<>();

        // for every card in hand count how many occurences are there
        for (int i = 0; i < hand.length(); i++) {
            Character c = hand.charAt(i);
            // if hand is already checked continue to next card in hand
            if (checked.stream().anyMatch(n -> (n.compareTo(c)) == 0)) continue;
            // add new card to checked
            checked.add(c);
            // count occurence of card and add to list
            occurences.put(c, hand.chars().filter(ch -> (ch == c)).count());
        }

        if (checked.contains('J') && occurences.size() > 1) {
            long amountOfJ = occurences.get('J');
            occurences.remove('J');
            long maxPairAmount =  Collections.max(occurences.values());

            List<Character> maxes = occurences.keySet().stream().filter(k -> occurences.get(k) == maxPairAmount).collect(Collectors.toList());


            occurences.put(maxes.get(0), maxPairAmount + amountOfJ);
        }

        if (occurences.size() == 1) return Type.FIVEAKIND;
        if (occurences.size() == 2) return occurences.containsValue(4L) ? Type.FOURAKIND : Type.FULLHOUSE;
        if (occurences.size() == 3) return occurences.containsValue(3L) ? Type.THREEAKIND : Type.TWOPAIR;
        if (occurences.size() == 4) return Type.ONEPAIR;
        if (occurences.size() == 5) return Type.HIGHCARD;

        throw new RuntimeException("hand is longer then 5 cards");
    }
}

class Hand {
    public String cards;
    public int bid;

    public Type type;

    public Hand(String cards, int bid, Type type) {
        this.cards = cards;
        this.bid = bid;
        this.type = type;
    }

    public int getValue() {
        return type.value;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards='" + cards + '\'' +
                ", bid=" + bid +
                ", type=" + type +
                '}';
    }
}


enum Type {
    FIVEAKIND(7),
    FOURAKIND(6),
    FULLHOUSE(5),
    THREEAKIND(4),
    TWOPAIR(3),
    ONEPAIR(2),
    HIGHCARD(1);

    public final int value;
    Type(int value) {
        this.value = value;
    }
}