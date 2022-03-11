package com.sistemitaly.Logic;

import com.google.gson.Gson;
import com.sistemitaly.Bean.GenericResponse;
import com.sistemitaly.Bean.Transaction;
import com.sistemitaly.Bean.TransactionListBodyResponse;
import com.sistemitaly.Bean.TransferBodyRequest;
import com.sistemitaly.Error.KOException;
import com.sistemitaly.Persistence.AccountDAO;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

/**
 * EJB usato per la logica del controller AccountController
 */
public class AccountEJB {

    /**
     * Di seguito le costanti applicative
     */

    private static final String baseUrl = "https://sandbox.platfr.io";
    private static final String apiKey = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
    private static final long accountId = 14537780;

    private static final String accountsUrl = "/api/gbs/banking/v4.0/accounts/";
    private static final String balanceUrl = "/balance";
    private static final String transferUrl  = "/payments/money-transfers";
    private static final String transactionListUrl = "/transactions";

    /**
     * Http Client utile all'intera classe
     */
    private HttpClient client = HttpClient.newHttpClient();

    /**
     * DAO For writing transaction on DB
     */
    private AccountDAO accountDAO = new AccountDAO();

    /**
     * Metodo per l'invio della richiesta del saldo.
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public GenericResponse balanceRead() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s%s%s%s", this.baseUrl, this.accountsUrl, String.valueOf(this.accountId),
                        this.balanceUrl)))
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        GenericResponse genericResponse = gson.fromJson(response.body(), GenericResponse.class);

        return genericResponse;
    }

    /**
     * Metodo per l'invio della richiesta del bonifico
     * @param transferBodyRequest
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws KOException
     */
    public GenericResponse transfer(TransferBodyRequest transferBodyRequest) throws IOException, InterruptedException, KOException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s%s%s%s", this.baseUrl, this.accountsUrl, String.valueOf(this.accountId),
                        this.transferUrl)))
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(transferBodyRequest.toString()))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        GenericResponse genericResponse = gson.fromJson(response.body(), GenericResponse.class);

        if (genericResponse.status.equals("KO")) {
            throw new KOException();
        }

        return genericResponse;
    }

    /**
     * Metodo per l'invio della richiesta della lista movimenti
     * @param fromAccountingDate
     * @param toAccountingDate
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public TransactionListBodyResponse getTransactionList(String fromAccountingDate, String toAccountingDate) throws IOException, InterruptedException, URISyntaxException {

        HttpGet someHttpGet = new HttpGet(String.format("%s%s%s%s", this.baseUrl, this.accountsUrl, String.valueOf(this.accountId),
                this.transactionListUrl));

        URI uri = new URIBuilder(someHttpGet.getURI())
                .addParameter("fromAccountingDate", fromAccountingDate)
                .addParameter("toAccountingDate", toAccountingDate)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .headers("Auth-Schema", "S2S", "apikey", this.apiKey)
                .GET()
                .build();

        HttpResponse<String> response = this.client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();

        TransactionListBodyResponse transactionListBodyResponse = gson.fromJson(response.body(), TransactionListBodyResponse.class);

        // Scrittura su DB
        for(Transaction transaction: transactionListBodyResponse.payload.list) {
            accountDAO.insertTransaction(transaction);
        }

        return transactionListBodyResponse;
    }
}
