package com.galka.features.java22;

import java.util.List;
import java.util.stream.Gatherers;

public class StreamGatherers {

    final double lengthDifference(List<String> words) {
        return words.stream()
                .gather(Gatherers.windowSliding(2))
                .peek(w -> System.out.println(w.getFirst() + " - " + w.getLast()))
                .map(w -> Math.abs(w.getFirst().length() - w.getLast().length()))
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }
}
