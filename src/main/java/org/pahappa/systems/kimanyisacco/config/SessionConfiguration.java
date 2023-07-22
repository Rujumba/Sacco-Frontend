package org.pahappa.systems.kimanyisacco.config;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.pahappa.systems.kimanyisacco.models.User;


public class SessionConfiguration {


    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            AnnotationConfiguration configuration = new AnnotationConfiguration();
            configuration.configure(); // Loads hibernate.cfg.xml configuration

            // Add annotated classes here
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Transaction.class);
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }


}

//package org.pahappa.systems.kimanyisacco.views.authentication;
//
//        import java.io.IOException;
//        import java.io.Serializable;
//
//        import javax.faces.bean.ManagedBean;
//        import javax.faces.bean.SessionScoped;
//        import javax.faces.context.ExternalContext;
//        import javax.faces.context.FacesContext;
//
//        import org.pahappa.systems.kimanyisacco.controllers.Hyperlinks;
//        import org.pahappa.systems.kimanyisacco.models.Members;
//
//@ManagedBean(name = "userSessionBean")
//@SessionScoped
//public class UserSessionBean implements Serializable {
//    private Members loggedInUser;
//
//    public Members getLoggedInUser() {
//        return loggedInUser;
//    }
//
//    public void setLoggedInUser(Members loggedInUser) {
//        this.loggedInUser = loggedInUser;
//    }
//
//    public void logout() throws IOException {
//        // Invalidate the session and redirect to the login page
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.invalidateSession();
//        ec.redirect(ec.getRequestContextPath() + Hyperlinks.login);
//    }
//}