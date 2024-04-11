package org.example.account.service;

import org.example.account.model.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    public String createAccount();
    public Account deposit(String accountName, BigDecimal amount);
    public Account withdraw(String accountName, BigDecimal amount);
}
