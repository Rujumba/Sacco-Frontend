package org.pahappa.systems.kimanyisacco.views.authentication;


import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.constants.EmployStatus;
import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.constants.IncomeRange;
import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;

import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean (name = "registerForm")
@RequestScoped
public class RegisterForm {

    private User user;

    private SaccoServices saccoServices;

    private EmployStatus employStatus;

    private Gender gender;

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
        System.out.println("called");
        users= saccoServices.getAllUsers();
        System.out.println("check1");
        if (users.isEmpty()){
            System.out.println("check_empty");
            saccoServices.registerUser(this.user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User Registered Successfully"));
            this.user = new User();
        }else {
            for (User user: users){

                if((user.getEmail()).equals(this.user.getEmail())){
                    System.out.println("check2");
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Email already exists!");
                    FacesContext.getCurrentInstance().addMessage("myForm:messages", message);
                }else{
                    System.out.println("check3");
                    System.out.println(user.getEmail());
                    saccoServices.registerUser(this.user);
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User registered successfully!");
                    FacesContext.getCurrentInstance().addMessage("myForm:messages", message);
                    FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.LOGIN);
                }
                this.user = new User();

            }
        }


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
}
