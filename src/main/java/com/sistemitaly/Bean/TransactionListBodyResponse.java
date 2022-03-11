package com.sistemitaly.Bean;

import java.util.ArrayList;

/**
 * Risposta della richiesta della lista movimenti
 */
public class TransactionListBodyResponse{
    public String status;
    public ArrayList<Object> error;
    public TransactionListPayload payload;
}


