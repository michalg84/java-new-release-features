package com.galka.features.java22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StreamGatherersTest {

    private final StreamGatherers gatherers = new StreamGatherers();
    @Test
    void wordsArrangeIs2() {
        List<String> words = List.of("dog", "mouse", "flower", "hat");

        double diff = gatherers.lengthDifference(words);

        Assertions.assertThat(diff).isEqualTo(2);
    }

}