package by.voloshchuk.servlet.command;

public final class CommandPath {

    private CommandPath() {
    }

    public static final String MAIN_JSP = "/jsp/main.jsp";

    public static final String ERROR_JSP = "/jsp/error.jsp";

    public static final String ABOUT_JSP = "/jsp/about.jsp";

    public static final String AUTHORIZATION_JSP = "/jsp/authorization.jsp";

    public static final String REGISTRATION_JSP = "/jsp/registration.jsp";

    public static final String PROJECTS_JSP = "/jsp/projects.jsp";

    public static final String TECHNICAL_TASKS_JSP = "/jsp/technical_tasks.jsp";

    public static final String BILLS_JSP = "/jsp/bills.jsp";

    public static final String PROFILE_JSP = "/jsp/profile.jsp";

    public static final String PROJECT_CREATION_JSP = "/jsp/project_creation.jsp";

    public static final String SEEK_EMPLOYEES_JSP = "/jsp/seek_employees.jsp";

    public static final String MAIN = "/controller?command=main";

    public static final String SEEK_EMPLOYEES = "/controller?command=to-seek-employees";

}
