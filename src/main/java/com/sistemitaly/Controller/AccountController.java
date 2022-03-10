package com.sistemitaly.Controller;
import com.sistemitaly.Bean.Account;
import com.sistemitaly.Bean.GenericResponse;
import com.sistemitaly.Bean.Transaction;
import com.sistemitaly.Logic.AccountEJB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class AccountController {

    private static final long accountId = 14537780;

    @GetMapping("/balance")
    Account balance() {
        AccountEJB accountEJB = new AccountEJB();
        try {
            return new Account(this.accountId, accountEJB.balanceRead().payload.balance);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/transfer")
    GenericResponse transfer() {
        AccountEJB accountEJB = new AccountEJB();
        try {
            return accountEJB.transfer();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/transaction-list")
    ArrayList<Transaction> getTransactionList() {
        AccountEJB accountEJB = new AccountEJB();
        try {
            return accountEJB.getTransactionList().payload.list;
        } catch (Exception e) {
            return null;
        }
    }
}
