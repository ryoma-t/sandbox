package com.ryoma2pick.sandbox.concurrency.round_robin;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class ThreadSafeRoundRobinLoadBalancer {

    private final Lock lock = new ReentrantLock();
    private static final String[] SERVERS = new String[]{
            "server1",
            "server2",
            "server3"
    };
    private int count;

    String server() throws InterruptedException {
        lock.lock();
        try {
            String server = SERVERS[(count) % SERVERS.length];
            Thread.sleep(10); // intend to cause an inconsistency
            count++;
            return server;
        } finally {
            lock.unlock();
        }
    }

}
