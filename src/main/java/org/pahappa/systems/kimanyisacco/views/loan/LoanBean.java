package org.pahappa.systems.kimanyisacco.views.loan;

import org.pahappa.systems.kimanyisacco.controller.Hyperlinks;
import org.pahappa.systems.kimanyisacco.models.Account;
import org.pahappa.systems.kimanyisacco.models.Loan;
import org.pahappa.systems.kimanyisacco.models.User;
import org.pahappa.systems.kimanyisacco.services.SaccoServiceImp;
import org.pahappa.systems.kimanyisacco.services.SaccoServices;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "loanBean")
@ViewScoped
public class LoanBean {
    private SaccoServices saccoServices;

    private Account account;

    private Date systemTime;

    private User user;

    private int pendingRequests;

    private User currentUser;

    private Loan loan;

    FacesMessage message;

    private List<Loan> allLoansList;

    private List<Loan> loanRequestList;

    private List<Loan> userLoans;

    private final String base = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();


    public LoanBean(){
        this.saccoServices = new SaccoServiceImp();
        this.account = new Account();
        this.user = new User();
        this.loan= new Loan();
        this.allLoansList= new ArrayList<>();
        this.loanRequestList= new ArrayList<>();
        this.userLoans= new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        this.currentUser = getCurrentUser();
        this.loan= new Loan();
        loanRequestList= saccoServices.pendingLoanRequests();
        allLoansList= saccoServices.getAllLoans();
        userLoans = saccoServices.getLoanOfUser(currentUser.getAccount());
        pendingRequests = loanRequestList.size();
    }

    public void saveLoan(){
        systemTime = new Date();
        this.loan.setAccount(currentUser.getAccount());
        this.loan.setUser(currentUser);
        this.loan.setDateCreated(systemTime);
        this.loan.setStatus("pending");
        this.loan.setInterestRate("12%");
        this.loan.setTotalLoanAmount(this.loan.getLoanAmount() + (this.loan.getLoanAmount() * 0.12));
        saccoServices.createLoan(loan);
        System.out.println(this.loan.getDuration());
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Loan Request Submitted Successfully!");
        FacesContext.getCurrentInstance().addMessage("message", message);
    }


    //methods
    public User getCurrentUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        return (User) externalContext.getSessionMap().get("currentUser");
    }

    public void approveLoan(Loan loan) throws IOException {
        loan.setStatus("approved");
        saccoServices.updateLoanStatus(loan);
        FacesContext.getCurrentInstance().getExternalContext().redirect(base+ Hyperlinks.ADMINLOAN);
        addFlashMessage(FacesMessage.SEVERITY_INFO, "Success", "Member approved successfully");
        sendApprovalEmail(loan.getUser().getEmail());

    }

    private void addFlashMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        facesContext.addMessage("messages", new FacesMessage(severity, summary,detail));
}



    public void getAllLoans(){
        allLoansList= saccoServices.getAllLoans();

    }

    public void getAllLoanRequests(){
        loanRequestList= saccoServices.pendingLoanRequests();
    }

    public SaccoServices getSaccoServices() {
        return saccoServices;
    }

    public void setSaccoServices(SaccoServices saccoServices) {
        this.saccoServices = saccoServices;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Date getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Date systemTime) {
        this.systemTime = systemTime;
    }

    public void setAllLoans(List<Loan> allLoans) {
    }

    public List<Loan> getAllLoansList() {
        return allLoansList;
    }

    public void setAllLoansList(List<Loan> allLoansList) {
        this.allLoansList = allLoansList;
    }

    public List<Loan> getLoanRequestList() {
        return loanRequestList;
    }

    public void setLoanRequestList(List<Loan> loanRequestList) {
        this.loanRequestList = loanRequestList;
    }

    public List<Loan> getUserLoans() {
        return userLoans;
    }

    public void setUserLoans(List<Loan> userLoans) {
        this.userLoans = userLoans;
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
            message.setSubject("LOAN APPROVAL");
            message.setText("Dear Member,\n\nYour loan request has been approved. Please you can come to the sacco and recieve it!");

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

    public int getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(int pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
}
