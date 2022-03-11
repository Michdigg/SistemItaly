package com.sistemitaly.Bean;

import java.util.Date;

/**
 * Payload di una risposta generica
 */
public class GenericResponsePayload {

    public Date date;

    public float balance;

    public float availableBalance;

    public String currency;

    @Override
    public String toString() {
        return "BalanceResponsePayload{" +
                "date=" + date.toString() +
                ", balance=" + balance +
                ", availableBalance=" + availableBalance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
