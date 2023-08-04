package org.pahappa.systems.kimanyisacco.views.account;

import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.constants.EmployStatus;
import org.pahappa.systems.kimanyisacco.constants.Gender;
import org.pahappa.systems.kimanyisacco.constants.IncomeRange;
import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;



@ManagedBean(name = "accountBean")
@ViewScoped
public class Account {

    private EmployStatus employStatus;

    private final SaccoServices saccoServices;

    private Gender gender;

    private String name;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private IncomeRange incomeRange;

    private User user = new User();

    private User currentUser;
    public Account(){

        this.saccoServices = new SaccoServiceImp();
    }

    @PostConstruct
    public void init(){
        this.user = new User();
        this.currentUser = getCurrentUser();
    }

    public void updateUserName() throws IOException {
        System.out.println(this.user.getName());
        currentUser.setName(this.user.getName());
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public void updateUserContact() throws IOException {
        System.out.println(this.user.getContact());
        currentUser.setContact(this.user.getContact());
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public void updateUserBirth() throws IOException {
        System.out.println(this.user.getDateOfBirth());
        currentUser.setDateOfBirth(this.user.getDateOfBirth());
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public void updateUserIncome() throws IOException {
        System.out.println(this.user.getIncomeRange());
        currentUser.setIncomeRange(this.user.getIncomeRange());
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public void updateUserGender() throws IOException {
        System.out.println(this.user.getGender());
        currentUser.setGender(this.user.getGender());
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public void updateUserEmploy() throws IOException {
        System.out.println(this.user.getEmploymentStatus());
        currentUser.setEmploymentStatus(this.user.getEmploymentStatus());
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
        saccoServices.updateUser(currentUser);
    }

    public void updateUserPassword() throws IOException {

        currentUser.setPassword( BCrypt.hashpw(this.user.getPassword(), BCrypt.gensalt()));
        saccoServices.updateUser(currentUser);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ACCOUNT);
    }

    public User getCurrentUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EmployStatus[] getEmployStatus() {
        return EmployStatus.values();
    }

    public void setEmployStatus(EmployStatus employStatus) {
        this.employStatus = employStatus;
    }

    public Gender[] getGender() {
        return Gender.values();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
