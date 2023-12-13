package com.ryoma2pick.sandbox.concurrency.round_robin;

import com.ryoma2pick.sandbox.concurrency.round_robin.ThreadSafeRoundRobinLoadBalancer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadSafeRoundRobinLoadBalancerTest {

    @Test
    void canIncrementCounterExclusively() throws InterruptedException {
        // setup
        ThreadSafeRoundRobinLoadBalancer lb = new ThreadSafeRoundRobinLoadBalancer();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                try {
                    String server = lb.server();
                    System.out.printf("%s: (i=%d) %s%n",
                            Thread.currentThread().getName(),
                            i,
                            server);
                } catch (InterruptedException e) {
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 300; i++) {
                try {
                    String server = lb.server();
                    System.out.printf("%s: (i=%d) %s%n",
                            Thread.currentThread().getName(),
                            i,
                            server);
                } catch (InterruptedException e) {
                }
            }
        });
        // execute
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // verify
        assertEquals("server1", lb.server());
    }

}