package com.galka.features.java22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreads {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public void virtualThreadExampleWaitingToComplete() {
        Thread virtualThread = Thread.ofVirtual().name("I'm Virtual").factory().newThread(() -> {
            System.out.println("virtual thread name: " + Thread.currentThread().getName());
        });

        virtualThread.start();

        try {
            virtualThread.join(); // virtualThread.join() wait for virtual threads to complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("VT ended");
    }

    public int withExecutorService() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 1_000; i++) {
                executor.submit(() -> {
                    counter.incrementAndGet();
                });
            }
        }
        System.out.println("Counter value: " + counter);
        return counter.get();
    }

    public int withVirtualFactory() {
        ThreadFactory factory = Thread.ofVirtual().factory();
        for (int i = 0; i < 1_000; i++) {
            factory.newThread(() -> {
                counter.incrementAndGet();
            }).start();
        }
        System.out.println("Counter value: " + counter);
        return counter.get();
    }
}
