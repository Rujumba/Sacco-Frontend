package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    private String accountNumber;
    private double amount;


    @Id
    @Column(name = "account_number", nullable = false)
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber=accountNumber;
    }

    @Column(name = "amount", nullable = false, updatable = true, columnDefinition = "double default 0.0" )
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount=amount;
    }





}
