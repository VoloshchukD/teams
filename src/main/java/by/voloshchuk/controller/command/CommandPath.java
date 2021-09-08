package by.voloshchuk.controller.command;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Constants with paths to move to.
 *
 * @author Daniil Voloshchuk
 */
public final class CommandPath {

    private CommandPath() {
    }

    private static final String PATH_MAIN_JSP = "path.main.jsp";

    private static final String PATH_ERROR_403_JSP = "path.error-403.jsp";

    private static final String PATH_ERROR_404_JSP = "path.error-404.jsp";

    private static final String PATH_ERROR_500_JSP = "path.error-500.jsp";

    private static final String PATH_ABOUT_JSP = "path.about.jsp";

    private static final String PATH_AUTHORIZATION_JSP = "path.authorization.jsp";

    private static final String PATH_REGISTRATION_JSP = "path.registration.jsp";

    private static final String PATH_PROJECTS_JSP = "path.projects.jsp";

    private static final String PATH_TECHNICAL_TASKS_JSP = "path.technical-tasks.jsp";

    private static final String PATH_CREATE_TECHNICAL_TASKS_JSP = "path.create-technical-tasks.jsp";

    private static final String PATH_BILLS_JSP = "path.bills.jsp";

    private static final String PATH_PAYMENT_FORM_JSP = "path.payment.jsp";

    private static final String PATH_PROFILE_JSP = "path.profile.jsp";

    private static final String PATH_PROJECT_CREATION_JSP = "path.project-creation.jsp";

    private static final String PATH_SEEK_EMPLOYEES_JSP = "path.seek-employees.jsp";

    private static final String PATH_REQUIREMENT_CREATION_JSP = "path.requirement-creation.jsp";

    private static final String PATH_TASKS_JSP = "path.tasks.jsp";

    private static final String PATH_BILL_CREATION_JSP = "path.bill-creation.jsp";

    private static final String PATH_MAIN = "path.main";

    private static final String PATH_TO_CREATE_REQUIREMENT = "path.requirement-creation";

    private static final String PATH_TO_CREATE_PROJECT = "path.project-creation";

    private static final String PATH_TO_PROJECTS = "path.projects";

    private static final String PATH_TO_BILLS = "path.bills";

    private static final String PATH_TO_TECHNICAL_TASKS = "path.technical-tasks";

    private static final String PATH_TASKS = "path.tasks";

    private static final String PARAMETER_TECHNICAL_TASK_ID = "parameter.technical-task-id";

    private static final String PATH_REQUIREMENTS = "path.requirements";

    private static final String PATH_TO_PROJECT_BILLS = "path.project-bills";

    private static final String PATH_BILL_CREATION = "path.bill-creation";

    private static final String RESOURCE_NAME = "path";

    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle(RESOURCE_NAME, Locale.getDefault());

    public static final String MAIN_JSP = resourceBundle.getString(PATH_MAIN_JSP);

    public static final String ERROR_403_JSP = resourceBundle.getString(PATH_ERROR_403_JSP);

    public static final String ERROR_404_JSP = resourceBundle.getString(PATH_ERROR_404_JSP);

    public static final String ERROR_500_JSP = resourceBundle.getString(PATH_ERROR_500_JSP);

    public static final String ABOUT_JSP = resourceBundle.getString(PATH_ABOUT_JSP);

    public static final String AUTHORIZATION_JSP = resourceBundle.getString(PATH_AUTHORIZATION_JSP);

    public static final String REGISTRATION_JSP = resourceBundle.getString(PATH_REGISTRATION_JSP);

    public static final String PROJECTS_JSP = resourceBundle.getString(PATH_PROJECTS_JSP);

    public static final String TECHNICAL_TASKS_JSP = resourceBundle.getString(PATH_TECHNICAL_TASKS_JSP);

    public static final String CREATE_TECHNICAL_TASKS_JSP = resourceBundle.getString(PATH_CREATE_TECHNICAL_TASKS_JSP);

    public static final String BILLS_JSP = resourceBundle.getString(PATH_BILLS_JSP);

    public static final String PAYMENT_FORM_JSP = resourceBundle.getString(PATH_PAYMENT_FORM_JSP);

    public static final String PROFILE_JSP = resourceBundle.getString(PATH_PROFILE_JSP);

    public static final String PROJECT_CREATION_JSP = resourceBundle.getString(PATH_PROJECT_CREATION_JSP);

    public static final String SEEK_EMPLOYEES_JSP = resourceBundle.getString(PATH_SEEK_EMPLOYEES_JSP);

    public static final String REQUIREMENT_CREATION_JSP = resourceBundle.getString(PATH_REQUIREMENT_CREATION_JSP);

    public static final String TASKS_JSP = resourceBundle.getString(PATH_TASKS_JSP);

    public static final String BILL_CREATION_JSP = resourceBundle.getString(PATH_BILL_CREATION_JSP);

    public static final String MAIN = resourceBundle.getString(PATH_MAIN);

    public static final String TO_CREATE_REQUIREMENT = resourceBundle.getString(PATH_TO_CREATE_REQUIREMENT);

    public static final String TO_CREATE_PROJECT = resourceBundle.getString(PATH_TO_CREATE_PROJECT);

    public static final String TO_PROJECTS = resourceBundle.getString(PATH_TO_PROJECTS);

    public static final String TO_BILLS = resourceBundle.getString(PATH_TO_BILLS);

    public static final String TECHNICAL_TASKS = resourceBundle.getString(PATH_TO_TECHNICAL_TASKS);

    public static final String TASKS = resourceBundle.getString(PATH_TASKS);

    public static final String TECHNICAL_TASK_ID_PARAMETER = resourceBundle.getString(PARAMETER_TECHNICAL_TASK_ID);

    public static final String REQUIREMENTS = resourceBundle.getString(PATH_REQUIREMENTS);

    public static final String TO_PROJECT_BILLS = resourceBundle.getString(PATH_TO_PROJECT_BILLS);

    public static final String BILL_CREATION = resourceBundle.getString(PATH_BILL_CREATION);

}
