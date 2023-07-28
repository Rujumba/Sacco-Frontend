package org.pahappa.systems.kimanyisacco.views.authentication;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.mindrot.jbcrypt.BCrypt;
import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

@ManagedBean(name = "loginForm")
@SessionScoped
public class LoginForm{

    //variables

    private User user;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    private SaccoServices saccoServices;

    private List<User> users= new ArrayList<>();

    FacesMessage message;

    private User currentUser;

    //getters and setters

    public LoginForm(){

        this.user = new User();
        this.saccoServices =new SaccoServiceImp();
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }


    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    //methods
    @PostConstruct
    public void init() {
        currentUser = getCurrentUser();

        // Prevent non authenticated users from accessing this page
        try {
            if (currentUser == null) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                externalContext.getSessionMap().put("currentUser", null);
                externalContext.redirect(externalContext.getRequestContextPath() + Hyperlinks.LOGIN);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void doLogin() throws IOException {

        User loggedUser = saccoServices.do_Login(this.user.getEmail());

        if(loggedUser != null){
            if ( BCrypt.checkpw(this.user.getPassword(), loggedUser.getPassword()) && loggedUser.getStatus().equals("verified")){
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();

                if(this.user.getEmail().contains("admin@gmail.com")){
                    externalContext.getSessionMap().put("currentUser", loggedUser);
                    FacesContext.getCurrentInstance().getExternalContext().redirect(base+Hyperlinks.ADMINDASHBOARD);
                }else{
                    externalContext.getSessionMap().put("currentUser", loggedUser);
                    FacesContext.getCurrentInstance().getExternalContext().redirect(base+Hyperlinks.DASHBOARD);
                }
            }else {
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Warning", "Invalid credentials or your account is not verified!");
                FacesContext.getCurrentInstance().addMessage("LoginForm:messages", message);
            }

        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Warning", "Invalid credentials or your account is not verified!");
            FacesContext.getCurrentInstance().addMessage("LoginForm:messages", message);
        }


    }

    public void doLogout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getSessionMap().put("currentUser", null);
        externalContext.redirect(base+Hyperlinks.LOGIN);

    }

    public User getCurrentUser() {
        // Used to fetch currently logged in user
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }

    private String hashPassword(String password) {
        // Use a strong hashing algorithm
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
