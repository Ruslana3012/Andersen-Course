package org.example.service;

import org.example.model.Violation;

import java.util.HashMap;
import java.util.Map;

public class ViolationService {
    private static final Map<Violation, Integer> violations = new HashMap<>();

    public static void putViolationInMap(Violation violation) {
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
