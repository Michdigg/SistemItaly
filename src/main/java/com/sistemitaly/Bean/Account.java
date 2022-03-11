package com.sistemitaly.Bean;

/**
 * Bean per l'astrazione dell'account
 */
public class Account {

    private long accountId;

    private float balance;

    public Account(long accountId, float balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
