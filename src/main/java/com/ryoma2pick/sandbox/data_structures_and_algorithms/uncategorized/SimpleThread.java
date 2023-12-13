package org.example;

public class SimpleThread {

    public static void main(String[] args) throws InterruptedException {
        printCurrentThread("*** test of interrupting Thread.sleep method start");
        Thread t1 = new Thread(new MyThread1());
        t1.start();
        t1.interrupt();
        t1.join();

        printCurrentThread("*** test of interrupting Thread.join method start");
        Thread t2 = new Thread(new MyThread2());
        t2.start();
        t2.interrupt();
        t2.join();
    }

    static String[] STRINGS = new String[]{"ne", "ushi", "tora"};

    static void printCurrentThread(String string) {
        System.out.format("%s: %s \n", Thread.currentThread().getName(), string);
    }

    static class MyThread1 implements Runnable {

        @Override
        public void run() {
            try {
                for (String s : STRINGS) {
                    printCurrentThread(s);
                    Thread.sleep(4000);
                }
            } catch (InterruptedException e) {
                printCurrentThread("Interrupted");
            }
        }

    }

    static class MyThread2 implements Runnable {

        @Override
        public void run() {
            Thread t1 = new Thread(new MyThread1());
            try {
                t1.start();
                t1.join(1000 * 10);
            } catch (InterruptedException e) {
                printCurrentThread("Interrupted");
                t1.interrupt();
            }
        }

    }

}
