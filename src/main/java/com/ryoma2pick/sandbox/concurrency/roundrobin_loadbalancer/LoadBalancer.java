package com.ryoma2pick.sandbox.concurrency.roundrobin_loadbalancer;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class LoadBalancer {

    private Map<Integer, String> servers;
    private static final int MAX_SIZE = 10;
    private int roundRobinCount = 0;
    private final Lock serverSizeLock = new ReentrantLock();
    private final Lock roundRobinCountLock = new ReentrantLock();

    public LoadBalancer() {
        servers = new HashMap<>();
    }

    public void addServerUnsafe(String server) throws ExceedsMaxException, DuplicateException {
        if (servers.size() >= MAX_SIZE) {
            throw new ExceedsMaxException();
        }
        if (servers.containsValue(server)) {
            throw new DuplicateException();
        }

        // intend to cause concurrent access
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int index = servers.size() + 1;
        servers.put(index, server);
    }

    public void addServer(String server) throws ExceedsMaxException, DuplicateException {
        serverSizeLock.lock();
        try {
            if (servers.size() >= MAX_SIZE) {
                throw new ExceedsMaxException();
            }
            if (servers.containsValue(server)) {
                throw new DuplicateException();
            }

            // intend to cause concurrent access
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int index = servers.size() + 1;
            servers.put(index, server);
        } finally {
            serverSizeLock.unlock();
        }
    }

    public String roundRobinServerUnsafe() {
        int index = roundRobinCount % getServers().size() + 1;

        // intend to cause concurrent access
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        roundRobinCount++;
        return servers.get(index);
    }

    public String roundRobinServer() {
        roundRobinCountLock.lock();
        try {
            int index = roundRobinCount % getServers().size() + 1;

            // intend to cause concurrent access
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            roundRobinCount++;
            return servers.get(index);
        } finally {
            roundRobinCountLock.unlock();
        }
    }

}
