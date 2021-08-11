package by.voloshchuk.tag;

import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Tag for viewing user badge on header.
 *
 * @author Daniil Voloshchuk
 */
public class UserBadgeTag extends TagSupport {

    private User.UserRole role;

    public static final String OPENING_TAGS_PART = "<div class=\"badge\"><span class=\"gray\">";

    public static final String MESSAGE_NAME_PART = "local.role-tag.";

    public static final String CLOSING_TAGS_PART = "</span></div>";

    public static final String RESOURCE_NAME = "local";

    public User.UserRole getRole() {
        return role;
    }

    public void setRole(User.UserRole role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        String roleAsString = role.toString();
        Locale locale = pageContext.getRequest().getLocale();
        if (pageContext.getSession().getAttribute(CommandAttribute.LOCAL) != null) {
            String localeLanguage = pageContext.getSession().getAttribute(
                    CommandAttribute.LOCAL).toString();
            locale = new Locale(localeLanguage);
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
        String localizedMessage = resourceBundle.getString(
                MESSAGE_NAME_PART + roleAsString.toLowerCase());
        try {
            pageContext.getOut().write(OPENING_TAGS_PART);
            pageContext.getOut().write(localizedMessage);
            pageContext.getOut().write(CLOSING_TAGS_PART);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

}
