package com.galka.features.java22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

//Test doesn't test all edge cases, but just invokes and presents Gatherers functionalities
class StreamGatherersTest {

    private final StreamGatherers gatherers = new StreamGatherers();

    @Test
    @DisplayName("Should return 2 as average length difference between all adjacent words ")
        // dog - mouse
        // mouse - flower
        // flower - hat
    void windowSliding_wordsArrangeIs2() {
        List<String> words = List.of("dog", "mouse", "flower", "hat");

        double diff = gatherers.lengthDifferenceSliding(words);

        Assertions.assertThat(diff).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return 2,5 as average length difference between all pairs words ")
        // dog - mouse
        // flower - hat
    void windowFixed_wordsArrangeIs2() {

        List<String> words = List.of("dog", "mouse", "flower", "hat");
        double diff = gatherers.lengthDifferenceFixed(words);

        Assertions.assertThat(diff).isEqualTo(2.5D);
    }

    @Test
    @DisplayName("Should return list of initial + sequentially accumulated values")
    void scan_incrementalAccumulator() {
        List<Integer> integers = List.of(9, 9, 7);
        List<String> strings = gatherers.incrementalAccumulator(integers);
        Assertions.assertThat(strings).containsExactly("Acu: 9", "Acu: 99", "Acu: 997");
    }
}