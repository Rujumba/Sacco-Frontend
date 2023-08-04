package org.pahappa.systems.kimanyisacco.views.account;

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
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@ManagedBean(name = "adminAccount")
@ViewScoped
public class AdminAccount {
    private User user;

    private int numberOfMembers;

    private SaccoServices saccoServices;

    private List<User> userList;

    private List<User> allUserList;
    private List<User> rejectedList;

    private User currentUser;

    String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

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

    public AdminAccount(){
        this.user = new User();
        this.saccoServices = new SaccoServiceImp();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @PostConstruct
    public void init() {
        System.out.println("Current user " + getCurrentUser());
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
        allUserList = saccoServices.getAllUsers();
        numberOfMembers = allUserList.size();
        userList = saccoServices.getAllUsersOfStatus("pending");
        rejectedList = saccoServices.getAllUsersOfStatus("rejected");

    }
    public User getCurrentUser() {
        // Used to fetch currently logged in user
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }

    public void verifyUser(User user) throws IOException {
        user.setStatus("verified");
        saccoServices.updateUser(user);
        System.out.println("User verified successfully");
        FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.ADMINACCOUNT);
        sendApprovalEmail(user.getEmail());
//        getUsersOfStatus();
        //message = "User verified successfully";
    }

    public void rejectUser(User user) throws IOException {
        user.setStatus("rejected");
        saccoServices.updateUser(user);
        System.out.println("User verified successfully");
        FacesContext.getCurrentInstance().getExternalContext().redirect(base + Hyperlinks.ADMINACCOUNT);
        sendRejectedEmail(user.getEmail());

    }


    private void sendApprovalEmail(String recipientEmail) {
        // Configure the email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        // Set up the session with the authentication details
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rujumbaleonard2@gmail.com", "emwimijkiwdemewg");
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rujumbaleonard2@gmail.com","KIMWANYI SACCO"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("SACCO Membership Approval");
            message.setText("Dear Member,\n\nYour account has been approved. You can now log in and start your savings journey with Kimwanyi SACCO.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception if the email sending fails
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception if there's an issue with the encoding
        }
    }

    private void sendRejectedEmail(String recipientEmail) {
        // Configure the email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");


        // Set up the session with the authentication details
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rujumbaleonard2@gmail.com", "emwimijkiwdemewg");
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rujumbaleonard2@gmail.com","KIMWANYI SACCO"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("SACCO Membership Approval");
            message.setText("Dear Member,\n\nYour account has been approved. You can now log in and start your savings journey with Kimwanyi SACCO.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception if the email sending fails
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // Handle the exception if there's an issue with the encoding
        }
    }


    public List<User> getAllUserList() {
        return allUserList;
    }

    public void setAllUserList(List<User> allUserList) {
        this.allUserList = allUserList;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public List<User> getRejectedList() {
        return rejectedList;
    }

    public void setRejectedList(List<User> rejectedList) {
        this.rejectedList = rejectedList;
    }
}
