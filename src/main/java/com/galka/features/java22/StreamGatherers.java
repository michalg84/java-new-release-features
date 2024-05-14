package com.galka.features.java22;

import java.util.Arrays;
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
                .peek(w -> System.out.println(STR."\{w.getFirst()} - \{w.getLast()}"))
                .map(w -> Math.abs(w.getFirst().length() - w.getLast().length()))
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    final List<String> incrementalAccumulator(List<Integer> nums) {
        return nums.stream()
                .gather(Gatherers.scan(() -> "Acu: ", (a, b) -> a + b))
                .toList();

    }

    final long parallelOldWay(int[] nums) {
        long start = System.currentTimeMillis();
        Arrays.stream(nums).parallel()
                .boxed()
                .map(p -> this.invokeExternalService(p))
                .toArray();
        return System.currentTimeMillis() - start;
    }

    final long parallelWithStreamGathererConcurrentMap(int threads, int[] nums) {
        long start = System.currentTimeMillis();
        Arrays.stream(nums).parallel()
                .boxed()
                .gather(Gatherers.mapConcurrent(threads, p -> this.invokeExternalService(p)))
                .toArray();
        return System.currentTimeMillis() - start;
    }

    private int invokeExternalService(int num) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return num + 1;
    }

}
