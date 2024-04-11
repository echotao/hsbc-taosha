package org.example.account.controller;

import org.example.account.exception.InsufficientBalanceException;
import org.example.account.model.Account;
import org.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount() {
        String accountName = accountService.createAccount();
        return ResponseEntity.ok(accountName);
    }

    @PostMapping("/account/{accountName}/depositAction")
    public ResponseEntity<String> deposit(@PathVariable String accountName, @RequestParam BigDecimal amount) {
        try {
            BigDecimal newBalance = accountService.deposit(accountName, amount).getBalance();
            return ResponseEntity.ok("Deposited $" + amount + " from account " + accountName + ", new balance $" + newBalance);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/account/{accountName}/withdrawAction")
    public ResponseEntity<String> withdraw(@PathVariable String accountName, @RequestParam BigDecimal amount) {
        try {
            BigDecimal newBalance = accountService.withdraw(accountName, amount).getBalance();
            return ResponseEntity.ok("Withdrawn $" + amount + " from account " + accountName + ", new balance $" + newBalance);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
