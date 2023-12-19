package com.ryoma2pick.sandbox.concurrency.roundrobin_loadbalancer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {

    @Test
    void add_up_to_10_servers() throws ExceedsMaxException, DuplicateException {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 0; i < 10; i++) {
            lb.addServerUnsafe("server" + i);
        }
        assertEquals(10, lb.getServers().size());
        try {
            lb.addServerUnsafe("11th");
            fail();
        } catch (ExceedsMaxException e) {
        }
    }

    @Test
    void cannot_register_duplicate_server() throws ExceedsMaxException, DuplicateException {
        LoadBalancer lb = new LoadBalancer();
        String duplicate = "foo";
        lb.addServerUnsafe(duplicate);
        try {
            lb.addServerUnsafe(duplicate);
            fail();
        } catch (DuplicateException e) {
        }
    }

    @Test
    void since_thread_unsafe_we_can_register_11th_server() throws DuplicateException, ExceedsMaxException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 0; i < 9; i++) {
            lb.addServerUnsafe("server" + i);
        }

        Thread t1 = new Thread(() -> {
            try {
                lb.addServerUnsafe("server10");
            } catch (DuplicateException | ExceedsMaxException e) {
                fail();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lb.addServerUnsafe("server11");
            } catch (DuplicateException | ExceedsMaxException e) {
                fail();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(11, lb.getServers().size());
    }

    @Test
    void since_thread_safe_we_cannot_register_11th_server() throws DuplicateException, ExceedsMaxException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 0; i < 9; i++) {
            lb.addServer("server" + i);
        }

        Thread t1 = new Thread(() -> {
            try {
                lb.addServer("server10");
            } catch (DuplicateException | ExceedsMaxException e) {
                fail();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lb.addServer("server11");
                fail();
            } catch (DuplicateException | ExceedsMaxException e) {
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertEquals(10, lb.getServers().size());
    }

    @Test
    void return_servers_one_after_another() throws DuplicateException, ExceedsMaxException {
        LoadBalancer lb = new LoadBalancer();
        String server1 = "server1";
        String server2 = "server2";
        String server3 = "server3";
        lb.addServer(server1);
        lb.addServer(server2);
        lb.addServer(server3);
        assertEquals(server1, lb.roundRobinServerUnsafe());
        assertEquals(server2, lb.roundRobinServerUnsafe());
        assertEquals(server3, lb.roundRobinServerUnsafe());
        assertEquals(server1, lb.roundRobinServerUnsafe());
    }

    @Test
    void since_unsafe_it_could_return_the_same_server_successively() throws DuplicateException, ExceedsMaxException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        String server1 = "server1";
        String server2 = "server2";
        String server3 = "server3";
        lb.addServer(server1);
        lb.addServer(server2);
        lb.addServer(server3);

        Thread t1 = new Thread(() -> {
            System.out.println(lb.roundRobinServerUnsafe());
            System.out.println(lb.roundRobinServerUnsafe());
        });
        Thread t2 = new Thread(() -> {
            System.out.println(lb.roundRobinServerUnsafe());
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    void since_safe_it_returns_servers_one_after_another() throws DuplicateException, ExceedsMaxException, InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        String server1 = "server1";
        String server2 = "server2";
        String server3 = "server3";
        lb.addServer(server1);
        lb.addServer(server2);
        lb.addServer(server3);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(lb.roundRobinServer());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(lb.roundRobinServer());
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}