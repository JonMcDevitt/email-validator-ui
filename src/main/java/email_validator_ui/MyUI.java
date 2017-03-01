package email_validator_ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;
import email_validator.*;

import javax.servlet.annotation.WebServlet;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField email = new TextField();
        email.setCaption("Type your email here:");

        Button button = new Button("Validate email");
        button.addClickListener((Button.ClickListener) clickEvent -> {
            EmailValidator validator = new EmailValidator();
            if(validator.validate(email.getValue())) {
                Notification.show("Email is valid", Notification.Type.HUMANIZED_MESSAGE);
            } else {
                Notification.show("Invalid email", Notification.Type.ERROR_MESSAGE);
            }
        });

        layout.addComponents(email, button);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
