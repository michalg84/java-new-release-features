package com.galka.features.java22;

import java.util.List;
import java.util.stream.Gatherers;

public class StreamGatherers {

    final double lengthDifferenceSliding(List<String> words) {
        return words.stream()
                .gather(Gatherers.windowSliding(2))
                .peek(w -> System.out.println(w.getFirst() + " - " + w.getLast()))
                .map(w -> Math.abs(w.getFirst().length() - w.getLast().length()))
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    final double lengthDifferenceFixed(List<String> words) {
        return words.stream()
                .gather(Gatherers.windowFixed(2))
                .peek(w -> System.out.println(w.getFirst() + " - " + w.getLast()))
                .map(w -> Math.abs(w.getFirst().length() - w.getLast().length()))
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    final List<String> incrementalAccumulator(List<Integer> words) {
        return words.stream()
                .gather(Gatherers.scan(() -> "Acu: ", (a, b) -> a + b))
                .toList();

    }

}
