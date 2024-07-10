package org.example.util;

import java.util.HashMap;
import java.util.Map;

public class Violation {
    private static final Map<org.example.model.Violation, Integer> violations = new HashMap<>();

    public static void putViolationInMap(org.example.model.Violation violation) {
        violations.merge(violation, 1, Integer::sum);
    }

    public static String getMostPopularViolation() {
        return violations.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey().toString())
                .orElse("");
    }
}
