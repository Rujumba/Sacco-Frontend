package org.pahappa.systems.kimanyisacco.models;

public class Loan {
    private int id;
    private String name;
    private String date;
    private String amount;
    private String status;

    private String accountNumber;

    private String nin;

    private String reason;

    private int duration;

    private int amountPaidPerMonth;

    private String SourceOfIncome;

    //getter and setter methods

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount){
        this.amount=amount;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber=accountNumber;
    }

    public String getNin(){
        return nin;
    }

    public void setNin(String nin){
        this.nin=nin;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration){
        this.duration=duration;
    }

    public int getAmountPaidPerMonth(){
        return amountPaidPerMonth;
    }

    public void setAmountPaidPerMonth(int amountPaidPerMonth){
        this.amountPaidPerMonth=amountPaidPerMonth;
    }

    public String getSourceOfIncome(){
        return SourceOfIncome;
    }

    public void setSourceOfIncome(String SourceOfIncome){
        this.SourceOfIncome=SourceOfIncome;
    }



}
