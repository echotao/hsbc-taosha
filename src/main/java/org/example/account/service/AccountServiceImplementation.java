package org.example.account.service;

import com.fasterxml.jackson.databind.JsonSerializable;
import org.example.account.exception.InsufficientBalanceException;
import org.example.account.model.Account;
import org.example.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImplementation implements AccountService{
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);;

    @Autowired
    private AccountRepository accountRepository;

    public String createAccount() {
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account).getAccountName();
    }

    public Account deposit(String accountName, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            logger.info("Invalid deposit amount");
            throw new IllegalArgumentException("Invalid deposit amount");
        } else {
            Account account = accountRepository.findByAccountName(accountName)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found"));
            BigDecimal newBalance = account.getBalance().add(amount);
            account.setBalance(newBalance);
            accountRepository.save(account);
            return account;
        }
    }

    public Account withdraw(String accountName, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <0) {
            logger.info("Invalid deposit amount");
            throw new IllegalArgumentException("Invalid withdraw amount");
        } else {
            Account account = accountRepository.findByAccountName(accountName)
                    .orElseThrow(() -> new IllegalArgumentException("Account not found"));
            BigDecimal newBalance = account.getBalance().subtract(amount);
            if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
                throw new InsufficientBalanceException("Insufficient funds");
            }
            account.setBalance(newBalance);
            accountRepository.save(account);
            return account;
        }
    }

}
