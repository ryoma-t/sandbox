package com.ryoma2pick.sandbox.concurrency.bank_account;

import com.ryoma2pick.sandbox.concurrency.bank_account.InsufficientBalanceException;
import com.ryoma2pick.sandbox.concurrency.bank_account.ThreadUnsafeAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ThreadUnsafeAccountTest {

    @Test
    void depositShouldAddAmount() {
        // setup
        ThreadUnsafeAccount account = new ThreadUnsafeAccount("foo", new BigDecimal("0"));
        // execute
        account.deposit(new BigDecimal("100"));
        // verify
        BigDecimal expected = new BigDecimal("100");
        assertEquals(0, expected.compareTo(account.getBalance()));
    }

    @Test
    void withdrawShouldSubtractAmount() {
        // setup
        ThreadUnsafeAccount account = new ThreadUnsafeAccount("foo", new BigDecimal("100"));
        try {
            // execute
            account.withdraw(new BigDecimal("30"));
            BigDecimal expected = new BigDecimal("70");
            // verify
            assertEquals(0, expected.compareTo(account.getBalance()));
        } catch (InsufficientBalanceException e) {
            fail();
        }
    }

    @Test
    void withdrawShouldFailIfAmountIsGreaterThanBalance() {
        // setup
        ThreadUnsafeAccount account = new ThreadUnsafeAccount("foo", new BigDecimal("100"));
        // execute
        // verify
        try {
            account.withdraw(new BigDecimal("101"));
            fail();
        } catch (InsufficientBalanceException e) {
        }
    }

    @Test
    void transferShouldTransferAmount() throws InterruptedException {
        // setup
        ThreadUnsafeAccount account1 = new ThreadUnsafeAccount("foo", new BigDecimal("100"));
        ThreadUnsafeAccount account2 = new ThreadUnsafeAccount("bar", new BigDecimal("100"));
        // execute
        account1.transfer(account2, new BigDecimal(50));
        // verify
        assertEquals(new BigDecimal("50"), account1.getBalance());
        assertEquals(new BigDecimal("150"), account2.getBalance());
    }

    @Test
        // it often fails
    void threadInterleavingCanLeadToInconsistency_twoThreadsDeposit() {
        // setup
        ThreadUnsafeAccount account = new ThreadUnsafeAccount("foo", new BigDecimal("0"));
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n", Thread.currentThread().getName(), account.getBalance());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n", Thread.currentThread().getName(), account.getBalance());
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
            assertEquals(1, new BigDecimal("20000").compareTo(account.getBalance()));
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
        // it often fails
    void threadInterleavingCanLeadToInconsistency_oneDepositAndAnotherWithdraw() {
        // setup
        ThreadUnsafeAccount account = new ThreadUnsafeAccount("foo", new BigDecimal("20000"));
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                account.deposit(new BigDecimal("1"));
                System.out.printf("%s: %s%n", Thread.currentThread().getName(), account.getBalance());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                try {
                    account.withdraw(new BigDecimal("1"));
                    System.out.printf("%s: %s%n", Thread.currentThread().getName(), account.getBalance());
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
            assertNotEquals(0, new BigDecimal("20000").compareTo(account.getBalance()));
        } catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    void threadInterleavingCanLeadToDeadLock() {
        // setup
        ThreadUnsafeAccount account1 = new ThreadUnsafeAccount("foo", new BigDecimal("100"));
        ThreadUnsafeAccount account2 = new ThreadUnsafeAccount("bar", new BigDecimal("200"));
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
        t1.start();
        t2.start();
        // verify
        long start = System.currentTimeMillis();
        try {
            t1.join(1000 * 3);
            t2.join(1000 * 3);
            assertEquals(new BigDecimal("100"), account1.getBalance());
            assertEquals(new BigDecimal("200"), account2.getBalance());
            if (System.currentTimeMillis() - start > 1000 * 2) return;
            fail();
        } catch (InterruptedException e) {
            fail();
        }
    }

}