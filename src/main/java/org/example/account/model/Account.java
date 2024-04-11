package org.example.account.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountName;

    private BigDecimal balance;

    public Account() {
        Random random = new Random();
        this.accountName = generateRandomString(16);
    }

    public Long getId() { return id; }

    public String getAccountName() {return accountName;}

    public void setAccountName(String accountName) {this.accountName = accountName;}

    public BigDecimal getBalance() {return balance;}

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10);
            sb.append(randomNumber);
        }
        return sb.toString();
    }
}
