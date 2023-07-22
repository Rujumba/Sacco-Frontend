package org.pahappa.systems.kimanyisacco.navigation;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Contains the links to the different pages with in the application.
 * It is to help us navigate between the pages in the application easily.
 */
@ManagedBean(name = "navigation")
@ApplicationScoped //There should be only one instance of the class created for the entire application
public class Navigation {

    private final String dashboard = "/pages/dashboard/Dashboard.xhtml";

    private final String adminDashboard = "/pages/dashboard/AdminDashboard.xhtml";

    private final String landing = "/pages/landing/Landing.xhtml";

    private final String transaction = "/pages/transaction/UserTransaction.xhtml";

    private final String login = "/pages/authentication/Login.xhtml";

    private final String register = "/pages/authentication/Register.xhtml";

    private final String loan = "/pages/loan/Loan.xhtml";

    public String getDashboard() {
        return dashboard;
    }

    public String getLanding() {
        return landing;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getLogin() {
        return login;
    }

    public String getLoan() {
        return loan;
    }

    public String getRegister() {
        return register;
    }

    public String getAdminDashboard() {
        return adminDashboard;
    }
}
