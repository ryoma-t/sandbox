package com.ryoma2pick.sandbox.concurrency.bank_account;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ThreadUnsafeAccount {

    private String id;
    private BigDecimal balance;

    void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    void withdraw(BigDecimal amount) throws InsufficientBalanceException {
        if (amount.compareTo(balance) > 0) {
            throw new InsufficientBalanceException();
        }
        balance = balance.subtract(amount);
    }

    void transfer(ThreadUnsafeAccount target, BigDecimal amount) throws InterruptedException {
        if (this.equals(target)) {
            return;
        }
        synchronized (this) {
            Thread.sleep(1000); // we want it to cause a deadlock
            synchronized (target) {
                balance = balance.subtract(amount);
                target.setBalance(target.getBalance().add(amount));
            }
        }
    }

}
