package com.sistemitaly.Bean;

/**
 * Risposta della richiesta per il bonifico
 */
public class TransferResponse {
    public String code;

    public String description;

    public TransferResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
