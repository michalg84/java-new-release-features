package com.galka.features.java22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VirtualThreadsTest {
    @Test
    void virtualThreadsExample() {
        VirtualThreads virtualThreads = new VirtualThreads();
        virtualThreads.virtualThreadExampleWaitingToComplete();
    }

    @Test
    void creatingThreadsWithExecutorService() {
        VirtualThreads virtualThreads = new VirtualThreads();
        int i = virtualThreads.withExecutorService();
        Assertions.assertThat(i).isEqualTo(1000);
    }

    @Test
    void creatingThreadsWithVirtualFactory() {

        VirtualThreads virtualThreads = new VirtualThreads();
        int i = virtualThreads.withVirtualFactory();
        Assertions.assertThat(i).isEqualTo(1000);
    }
}