package com.sistemitaly.Controller;
import com.sistemitaly.Bean.*;
import com.sistemitaly.Error.KOException;
import com.sistemitaly.Logic.AccountEJB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class AccountController {

    private static final long accountId = 14537780;

    private AccountEJB accountEJB = new AccountEJB();

    /**
     * Endpoint per la visualizzazione del saldo
     * @return ResponseEntity<Account>
     */
    @GetMapping("/balance")
    ResponseEntity<Account> balance() {

        try {

            return new ResponseEntity<>(new Account(this.accountId, this.accountEJB.balanceRead().payload.balance), HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /**
     * Endpoint per il bonifico
     * @param transferBodyRequest
     * @return ResponseEntity<TransferResponse>
     */
    @PostMapping("/transfer")
    ResponseEntity<TransferResponse> transfer(TransferBodyRequest transferBodyRequest) {
        try {

            // In questo caso la risposta sarebbe cambiata con uno codice di transazione avvenuta correttamente,
            // ai fini dell'esercizio non era previsto

            this.accountEJB.transfer(transferBodyRequest);
            return new ResponseEntity<>(new TransferResponse("API001", "Transazione eseguita con successo"), HttpStatus.OK);

        } catch (KOException koException) {

            return new ResponseEntity<>(new TransferResponse("API000",
                    "Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780"), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * Endpoint per la lista movimenti
     * @param fromAccountingDate
     * @param toAccountingDate
     * @return ResponseEntity<ArrayList<Transaction>>
     */
    @GetMapping("/transaction-list/{fromAccountingDate}/{toAccountingDate}")
    ResponseEntity<ArrayList<Transaction>> getTransactionList(@PathVariable String fromAccountingDate, @PathVariable String toAccountingDate) {

        try {

            return new ResponseEntity<>(this.accountEJB.getTransactionList(fromAccountingDate, toAccountingDate).payload.list,
                    HttpStatus.OK);

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
