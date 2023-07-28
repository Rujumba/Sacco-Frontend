package org.pahappa.systems.kimanyisacco.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "loans")
public class Loan {


    private Date dateCreated;
    private double loanAmount;
    private String status;
    private String reason;

    private Date duration;

    private int amountPaidPerMonth;

    private String SourceOfIncome;

    private String interestRate;

    private double TotalLoanAmount;

    private Account account;

    private Long loanID;

    private User user;

    //getter and setter methods




    @Column (name = "loan_status", nullable = false)
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    @Column (name = "loan_reason", nullable = false)
    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

    @Column (name = "loan_duration", nullable = false)
    public Date getDuration(){
        return duration;
    }

    public void setDuration(Date duration){
        this.duration=duration;
    }

    @Column (name = "loan_amount_monthly", nullable = false)
    public int getAmountPaidPerMonth(){
        return amountPaidPerMonth;
    }

    public void setAmountPaidPerMonth(int amountPaidPerMonth){
        this.amountPaidPerMonth=amountPaidPerMonth;
    }

    @Column (name = "source_of_income", nullable = false)
    public String getSourceOfIncome(){
        return SourceOfIncome;
    }

    public void setSourceOfIncome(String SourceOfIncome){
        this.SourceOfIncome=SourceOfIncome;
    }


    @Column (name = "interest_rate", nullable = false)
    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }


    @Column (name = "total_loan_amount", nullable = false)
    public double getTotalLoanAmount() {
        return TotalLoanAmount;
    }

    public void setTotalLoanAmount(double totalLoanAmount) {
        TotalLoanAmount = totalLoanAmount;
    }

    @Column (name = "loan_amount", nullable = false)
    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLoanID(Long loanID) {
        this.loanID = loanID;
    }

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    public Long getLoanID() {
        return loanID;
    }

    @Column (name = "date_created", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
