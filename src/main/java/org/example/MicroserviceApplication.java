package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MicroserviceApplication {
    private final Map<String, Account> accounts = new HashMap<>();
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @PostMapping("/create")
    public String createAccount(){
        String accountId = generateAccountId();
        accounts.put(accountId, new Account(accountId));
        return accountId;
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable String id,  @RequestBody WithdrawRequest request) {
        if (accounts.containsKey(id)) {
            Account account = accounts.get(id);
            account.withdraw(request.amount);
            return "Withdraw $" + request.amount + " from account " + id;
        } else {
            return "Account not found";
        }
    }

    @PostMapping("deposit/{id}")
    public String deposit(@PathVariable String id, @RequestBody DepositRequest request) {
        if (accounts.containsKey(id)) {
            Account account = accounts.get(id);
            account.deposit(request.amount);
            return "Deposited $" + request.amount + " from account " + id;
        } else {
            return  "Account not found";
        }
    }

    private String generateAccountId() {
        return "ACC" + (accounts.size()+1);
    }

}
