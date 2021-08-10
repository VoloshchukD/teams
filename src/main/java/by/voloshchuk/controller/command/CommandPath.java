package by.voloshchuk.controller.command;

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

    public static final String CREATE_TECHNICAL_TASKS_JSP = "/jsp/technical_task_creation.jsp";

    public static final String BILLS_JSP = "/jsp/bills.jsp";

    public static final String PAYMENT_FORM_JSP = "/jsp/payment_form.jsp";

    public static final String PROFILE_JSP = "/jsp/profile.jsp";

    public static final String PROJECT_CREATION_JSP = "/jsp/project_creation.jsp";

    public static final String SEEK_EMPLOYEES_JSP = "/jsp/seek_employees.jsp";

    public static final String REQUIREMENT_CREATION_JSP = "/jsp/requirement_creation.jsp";

    public static final String TASKS_JSP = "/jsp/tasks.jsp";

    public static final String BILL_CREATION_JSP = "/jsp/bill_creation.jsp";

    public static final String MAIN = "/controller?command=main";

    public static final String SEEK_EMPLOYEES = "/controller?command=to-seek-employees";

    public static final String TO_CREATE_REQUIREMENT = "/controller?command=to-create-requirement";

    public static final String TO_CREATE_PROJECT = "/controller?command=to-create-project";

    public static final String TO_PROJECTS = "/controller?command=projects&state=IN_PROGRESS";

    public static final String TO_BILLS = "/controller?command=bills";

    public static final String TECHNICAL_TASKS = "/controller?command=technical-tasks";

    public static final String PROJECT_BILLS = "?command=to-project-bills&project-id=";

    public static final String TASKS = "?command=to-tasks&project-id=";

    public static final String REQUIREMENTS = "?command=to-create-requirement&technical-task-id=";

}
