package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    private String name;
    private String amount;
    private String type;
    private String status;

    private String accountNumber;

    private String method;

    private Date dateCreated;

    private String email;

    //getter and setter methods


    @Column(name = "amount", nullable = false)
    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount){
        this.amount=amount;
    }

    @Column (name = "transaction_type", nullable = false)
    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    @Column (name = "transaction_status", nullable = false)
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    @Column (name = "account_number", nullable = false)
    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber=accountNumber;
    }

    @Column (name = "transaction_method", nullable = false)
    public String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method=method;
    }



    @Column (name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column (name = "date_created", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
