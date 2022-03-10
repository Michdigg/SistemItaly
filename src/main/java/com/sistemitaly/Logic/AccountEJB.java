package com.sistemitaly.Logic;

import com.google.gson.Gson;
import com.sistemitaly.Bean.GenericResponse;
import com.sistemitaly.Bean.TransactionListBodyResponse;
import com.sistemitaly.Bean.TransferBodyRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccountEJB {

    private static final String baseUrl = "https://sandbox.platfr.io";
    private static final String apiKey = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
    private static final long accountId = 14537780;
    private static final String balanceUrl = "/api/gbs/banking/v4.0/accounts/";

    private HttpClient client = HttpClient.newHttpClient();

    public GenericResponse balanceRead() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/balance"))
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        GenericResponse genericResponse = gson.fromJson(response.body(), GenericResponse.class);

        return genericResponse;
    }

    public GenericResponse transfer() throws IOException, InterruptedException {

        TransferBodyRequest transferBodyRequest = new TransferBodyRequest("", "",
                "", "", "");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/payments/money-transfers"))
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(transferBodyRequest.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        GenericResponse genericResponse = gson.fromJson(response.body(), GenericResponse.class);

        return genericResponse;
    }

    public TransactionListBodyResponse getTransactionList() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01"))
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        TransactionListBodyResponse transactionListBodyResponse = gson.fromJson(response.body(), TransactionListBodyResponse.class);

        return transactionListBodyResponse;
    }
}
