package com.sistemitaly.Bean;

/**
 * Bean di una transazione
 */
public class Transaction {

    public Transaction(String transactionId, String operationId, String accountingDate, String valueDate, TransactionType type, double amount, String currency, String description) {
        this.transactionId = transactionId;
        this.operationId = operationId;
        this.accountingDate = accountingDate;
        this.valueDate = valueDate;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public String transactionId;
    public String operationId;
    public String accountingDate;
    public String valueDate;
    public TransactionType type;
    public double amount;
    public String currency;
    public String description;
}
