package org.pahappa.systems.kimanyisacco.models;
import org.pahappa.systems.kimanyisacco.constants.EmployStatus;
import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.constants.IncomeRange;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private long id;

    private String name;
    private EmployStatus employmentStatus;

    private String contact;

    private Gender gender;

    private IncomeRange incomeRange;

    private String dateOfBirth;

    private String email;

    private String password;

    private String confirmPassword;

    private String accountNumber;

    private String status;

    //getter and setter methods

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Column(name = "client_name", nullable = false)
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "employ_status", nullable = false)
    public EmployStatus getEmploymentStatus(){
        return employmentStatus;
    }
    public void setEmploymentStatus(EmployStatus employmentStatus){
        this.employmentStatus = employmentStatus;
    }

    @Column(name = "contact", nullable = false, unique = true)
    public String getContact(){
        return contact;
    }
    public void setContact(String contact){
        this.contact=contact;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    public Gender getGender(){
        return gender;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "income_range", nullable = false)
    public IncomeRange getIncomeRange(){
        return incomeRange;
    }

    public void setIncomeRange(IncomeRange incomeRange){
        this.incomeRange = incomeRange;
    }


    @Column(name = "date_of_birth", nullable = false)
    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @Column(name = "email", nullable = false, unique = true)
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getConfirmPassword(){
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

    @Column(name = "account_number", nullable = false)
    public String getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
