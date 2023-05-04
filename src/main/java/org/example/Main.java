package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<String, Integer> countWords(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Error: null");
        }
        if (input.isEmpty()) {
            return Collections.emptyMap();
        }

        String[] words = input.split("[^a-zA-Z]+");
        Map<String, Integer> result = new HashMap<>();
        for (String word : words) {
            result.put(word, result.getOrDefault(word, 0) + 1);
        }
        return result;
    }

    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String input = scanner.nextLine();
        Map<String, Integer> wordCounts = countWords(input);
        for (Map.Entry<String, Integer> words : wordCounts.entrySet()) {
            System.out.println(words.getKey() + " = " + words.getValue());
        }
    }
}