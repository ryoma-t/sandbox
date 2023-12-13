package com.ryoma2pick.sandbox.concurrency.round_robin;

import com.ryoma2pick.sandbox.concurrency.round_robin.ThreadUnsafeRoundRobinLoadBalancer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadUnsafeRoundRobinLoadBalancerTest {

    @Test
    void shouldReturnServer() throws InterruptedException {
        // setup
        ThreadUnsafeRoundRobinLoadBalancer lb = new ThreadUnsafeRoundRobinLoadBalancer();
        // execute & verify
        assertEquals("server1", lb.server());
        assertEquals("server2", lb.server());
        assertEquals("server3", lb.server());
        assertEquals("server1", lb.server());
    }

    @Test
    void shouldLeadToInconsistency() throws InterruptedException {
        // setup
        ThreadUnsafeRoundRobinLoadBalancer lb = new ThreadUnsafeRoundRobinLoadBalancer();
        Thread t1 = new Thread(() -> {
            try {
                String server = lb.server();
                System.out.printf("%s: %s%n",
                        Thread.currentThread().getName(),
                        server);
            } catch (InterruptedException e) {
            }
        });
        // execute & verify
        assertEquals("server1", lb.server());
        t1.start();
        // if concurrency control works, it should return "server3"
        assertEquals("server2", lb.server());
    }

}