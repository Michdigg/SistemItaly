package com.sistemitaly.Bean;

/**
 * Bean per la mappatura della richiesta per un bonifico
 */
public class TransferBodyRequest {
    public String receiverName;

    public String description;

    public String currency;

    public String amount;

    public String executionDate;

    public TransferBodyRequest(String receiverName, String description, String currency, String amount, String executionDate) {
        this.receiverName = receiverName;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.executionDate = executionDate;
    }

    @Override
    public String toString() {
        return "{" +
                "receiverName='" + receiverName + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", executionDate='" + executionDate + '\'' +
                '}';
    }
}
