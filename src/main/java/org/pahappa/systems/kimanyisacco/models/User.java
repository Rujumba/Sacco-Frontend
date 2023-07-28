package org.pahappa.systems.kimanyisacco.models;
import org.pahappa.systems.kimanyisacco.constants.EmployStatus;
import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.constants.IncomeRange;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private String name;
    private EmployStatus employmentStatus;

    private String contact;

    private Gender gender;

    private IncomeRange incomeRange;

    private String dateOfBirth;

    private String email;

    private String password;

    private Account account;

    private String status;

    //getter and setter methods

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

    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "account_number")
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
