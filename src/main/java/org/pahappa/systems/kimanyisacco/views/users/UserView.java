package org.pahappa.systems.kimanyisacco.views.users;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.pahappa.systems.kimanyisacco.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "userView")
@SessionScoped
public class UserView {

    private List<User> users;

    String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    private String userTypes;

    public UserView(){
        this.users = new ArrayList<>();

        for(int i=0; i<5; i++){
            User user = new User();

            user.setEmail("user" + i + "@gmail.com");
            user.setPassword("password" + i);


            this.users.add(user);
        }
    }

    public List<User> getUsers(){
        return users;
    }
    public void setUsers(List<User> users){
        this.users = users;
    }

    //function to remove user
    public void removeUser(User user){
        this.users.remove(user);
    }
    //if the field selected is admin redirect to admin page or redirect to user page


    public String getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(String userTypes) {
        this.userTypes = userTypes;
    }

    public void doLogout() throws IOException {
        if(this.userTypes.equals("admin")) {
            //redirect to admin page
            FacesContext.getCurrentInstance().getExternalContext().redirect(base + "/dashboard/AdminDashboard.xhtml");
        }    else if(this.userTypes.equals("user") ){
            FacesContext.getCurrentInstance().getExternalContext().redirect(base + "/dashboard/Dashboard.xhtml");
        }else{
            System.out.println("Error");
        }
    }
}
