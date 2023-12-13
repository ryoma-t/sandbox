package com.ryoma2pick.sandbox.concurrency.bank_account;

import com.ryoma2pick.sandbox.concurrency.bank_account.InsufficientBalanceException;
import com.ryoma2pick.sandbox.concurrency.bank_account.ThreadSafeAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ThreadSafeAccountTest {

    @Test
    void threadInterleavingShouldKeepConsistency_twoThreadsDeposit() {
        // setup
        ThreadSafeAccount account = new ThreadSafeAccount("foo", new BigDecimal("0"));
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n",
                        Thread.currentThread().getName(),
                        account.getBalance()
                );
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n",
                        Thread.currentThread().getName(),
                        account.getBalance()
                );
            }
        });
        // execute
        t1.start();
        t2.start();
        // verify
        try {
            t1.join();
            t2.join();
            System.out.println(account.getBalance());
            assertEquals(0, new BigDecimal("2000").compareTo(account.getBalance()));
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    void threadInterleavingShouldKeepConsistency_oneDepositAndAnotherWithdraw() {
        // setup
        ThreadSafeAccount account = new ThreadSafeAccount("foo", new BigDecimal("1000"));
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n",
                        Thread.currentThread().getName(),
                        account.getBalance()
                );
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                try {
                    account.withdraw(new BigDecimal("1"));
                    System.out.printf("%s: %s%n",
                            Thread.currentThread().getName(),
                            account.getBalance()
                    );
                } catch (InsufficientBalanceException e) {
                }
            }
        });
        // execute
        t1.start();
        t2.start();
        // verify
        try {
            t1.join();
            t2.join();
            System.out.println(account.getBalance());
            assertEquals(0, new BigDecimal("1000").compareTo(account.getBalance()));
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    void name() throws InterruptedException {
        // setup
        ThreadSafeAccount account1 = new ThreadSafeAccount("foo", new BigDecimal("100"));
        ThreadSafeAccount account2 = new ThreadSafeAccount("bar", new BigDecimal("200"));
        Thread t1 = new Thread(() -> {
            try {
                account1.transfer(account2, new BigDecimal(10));
            } catch (InterruptedException e) {
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                account2.transfer(account1, new BigDecimal(20));
            } catch (InterruptedException e) {
            }
        });
        // execute
        t2.start();
        t1.start();
        // verify
        t1.join();
        t2.join();
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        assertEquals(new BigDecimal("300"), account1.getBalance().add(account2.getBalance()));
    }

}