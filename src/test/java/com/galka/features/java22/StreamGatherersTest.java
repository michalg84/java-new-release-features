package com.galka.features.java22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

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

    @Test
    @Disabled("takes over 30 seconds")
    void mapConcurrentCompare() throws InterruptedException {
        int[] range = IntStream.range(0, 10000).toArray();
        //all bellow functions call an external service stub that takes 10 milliseconds for each of 10000 numbers
        long warmUpOnly = gatherers.parallelWithStreamGathererConcurrentMap(1000, range);
        Thread.sleep(200);
        long old = gatherers.parallelOldWay(range);
        long threadsNo10000 = gatherers.parallelWithStreamGathererConcurrentMap(10000, range);
        Thread.sleep(200);
        long threadsNo100 = gatherers.parallelWithStreamGathererConcurrentMap(100, range);
        Thread.sleep(200);
        long threadsNo1000 = gatherers.parallelWithStreamGathererConcurrentMap(1000, range);
        Thread.sleep(200);
        long threadsNo16 = gatherers.parallelWithStreamGathererConcurrentMap(16, range);

        System.out.printf("Old stream parallel took : %s milliseconds %n",old);
        System.out.printf("Stream Gatherers with %s threads took : %s milliseconds %n", 16, threadsNo16);
        System.out.printf("Stream Gatherers with %s threads took : %s milliseconds %n", 100, threadsNo100);
        System.out.printf("Stream Gatherers with %s threads took : %s milliseconds %n", 1000, threadsNo1000);
        System.out.printf("Stream Gatherers with %s threads took : %s milliseconds %n", 10000, threadsNo10000);

        //        Old stream parallel took : 19775
        //        Stream Gatherers with 16 threads took : 9888
        //        Stream Gatherers with 100 threads took : 1591
        //        Stream Gatherers with 1000 threads took : 157
        //        Stream Gatherers with 10000 threads took : 46
        Assertions.assertThat(old)
                .isGreaterThan(threadsNo16)
                .isGreaterThan(threadsNo100)
                .isGreaterThan(threadsNo1000)
                .isGreaterThan(threadsNo10000);
    }
}