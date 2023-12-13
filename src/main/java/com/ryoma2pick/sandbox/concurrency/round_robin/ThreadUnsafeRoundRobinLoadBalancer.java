package com.ryoma2pick.sandbox.concurrency.round_robin;

import lombok.Data;

@Data
public class ThreadUnsafeRoundRobinLoadBalancer {

    private static final String[] SERVERS = new String[]{
            "server1",
            "server2",
            "server3"
    };
    private int count;

    String server() throws InterruptedException {
        String server = SERVERS[(count) % SERVERS.length];
        Thread.sleep(1000); // intend to cause an inconsistency
        count++;
        return server;
    }

}
