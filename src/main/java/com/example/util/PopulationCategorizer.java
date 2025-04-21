package com.example.util;

public class PopulationCategorizer {
    public static String categorize(long population) {
        if (population < 1_000_000) return "SMALL";
        else if (population <= 10_000_000) return "MEDIUM";
        else return "LARGE";
    }
}
