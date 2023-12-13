package com.ryoma2pick.sandbox.concurrency.bank_account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
public class ThreadSafeAccount {

    private final Lock lock = new ReentrantLock();
    private String id;
    private BigDecimal balance;

    synchronized void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    synchronized void withdraw(BigDecimal amount) throws InsufficientBalanceException {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException();
        }
        balance = balance.subtract(amount);
    }

    void transfer(ThreadSafeAccount target, BigDecimal amount) throws InterruptedException {
        if (this.equals(target)) {
            return;
        }

        boolean myLock = false;
        boolean targetLock = false;

        try {
            myLock = lock.tryLock();
            targetLock = target.getLock().tryLock();
            if (myLock && targetLock) {
                balance = balance.subtract(amount);
                target.setBalance(target.getBalance().add(amount));
            } else {
                System.out.println("failed to acquire locks");
            }
        } finally {
            if (myLock) {
                lock.unlock();
            }
            if (targetLock) {
                target.getLock().unlock();
            }
        }
    }

}
