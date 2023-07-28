package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class UserTransaction {

    private Long id;
    private String type;
    private String status;

    private Account account;

    private String method;

    private Date dateCreated;

    private float amount;

    //getter and setter methods


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

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account=account;
    }

    @Column (name = "transaction_method", nullable = false)
    public String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method=method;
    }

    @Column (name = "date_created", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
