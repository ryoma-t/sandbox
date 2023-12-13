package com.ryoma2pick.sandbox.data_structures_and_algorithms.uncategorized;

public class GuardedBlock {

    /*
    https://docs.oracle.com/javase/tutorial/essential/concurrency/guardmeth.html

    - Let's use guarded blocks to create a Producer-Consumer application.
    - This kind of application shares data between two threads:
        - the producer, that creates the data
        - the consumer, that does something with it
    - The two threads communicate using a shared object.
        - Coordination is essential:
        - the consumer thread must not attempt to retrieve the data before the producer thread has delivered it
    - the producer thread must not attempt to deliver new data if the consumer hasn't retrieved the old data
     */

    public static void main(String[] args) {
        Drop drop = new Drop();
        new Thread(new Producer(drop)).start();
        new Thread(new Consumer(drop)).start();
    }

    static class Drop {

        boolean empty = true;
        String string;

        synchronized String take() {
            while (empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            empty = true;
            notifyAll();
            return string;
        }

        synchronized void put(String s) {
            while (!empty) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            empty = false;
            string = s;
            notifyAll();
        }

    }

    static class Producer implements Runnable {

        Drop drop;

        Producer(Drop drop) {
            this.drop = drop;
        }

        @Override
        public void run() {
            String[] strings = new String[]{"hoge", "fuga", "foo", "bar"};
            for (String s : strings) {
                drop.put(s);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                }
            }
            drop.put("done");
        }

    }

    static class Consumer implements Runnable {

        Drop drop;

        Consumer(Drop drop) {
            this.drop = drop;
        }

        @Override
        public void run() {
            while (true) {
                String s = drop.take();
                System.out.println(s);
                if (s.equals("done")) break;
            }
        }

    }

}

