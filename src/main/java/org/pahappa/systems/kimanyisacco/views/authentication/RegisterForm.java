package org.pahappa.systems.kimanyisacco.views.authentication;


import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.constants.EmployStatus;
import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.constants.IncomeRange;
import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;

import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@ManagedBean (name = "registerForm")
@RequestScoped
public class RegisterForm {
    private String firstName;
    private String lastName;
    private String fullName;

    private User user;

    private SaccoServices saccoServices;

    private EmployStatus employStatus;

    private Gender gender;

    private boolean termsAccepted;

    private IncomeRange incomeRange;

    FacesMessage message;

    String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    List<User> users= new ArrayList<>();

    public RegisterForm(){
        this.user = new User();
        this.saccoServices =new SaccoServiceImp();
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    public void registerUsers() throws IOException {
        fullName = firstName + " " + lastName;
        this.user.setName(fullName);
        System.out.println("called");
        boolean num= saccoServices.numberOfUsers();
        System.out.println("check1");
        String password = hashPassword(this.user.getPassword());
        this.user.setPassword(password);

        if (num){

            System.out.println("check_empty");
            saccoServices.registerUser(this.user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User Registered Successfully"));
            FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.LOGIN);

        }else {
            boolean emailExists = saccoServices.emailExists(this.user.getEmail());
            System.out.println("checkEmail");

            if(emailExists){

                System.out.println("check2");
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Warning", "Email already exists!");
                FacesContext.getCurrentInstance().addMessage("messages", message);


            }else{
                System.out.println("check3");
                System.out.println(this.user.getEmail());
                saccoServices.registerUser(this.user);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User registered successfully!");

                FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.LOGIN);
                FacesContext.getCurrentInstance().addMessage("message", message);
            }
        }
        this.user = new User();


    }

    public void redirectLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.LOGIN);
    }

    public Date getMaxSelectableDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxDate = currentDate.minusYears(18);
        return Date.from(maxDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public EmployStatus[] getEmployStatus() {
        return EmployStatus.values();
    }

    public void setEmployStatus(EmployStatus employStatus) {
        this.employStatus = employStatus;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public IncomeRange[] getIncomeRange() {
        return IncomeRange.values();
    }

    public void setIncomeRange(IncomeRange incomeRange) {
        this.incomeRange = incomeRange;
    }

    public Gender[] getGender(){
        return Gender.values();
    }

    private String hashPassword(String password) {
        // Use a strong hashing algorithm
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
