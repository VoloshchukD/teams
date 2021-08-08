package by.voloshchuk.servlet.filter;

import by.voloshchuk.entity.User;
import by.voloshchuk.servlet.command.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AccessFilter implements Filter {

    private final Map<User.UserRole, Set<String>> accessMap = new HashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User.UserRole role = (User.UserRole) request.getSession(true)
                .getAttribute(CommandAttribute.ROLE);
        if (role == null) {
            role = User.UserRole.GUEST;
        }
        String command = request.getParameter(RequestParameter.COMMAND);
        if (command == null || !accessMap.get(role).contains(command)) {
            request.getRequestDispatcher(CommandPath.ERROR_JSP).forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        Set<String> guestCommands = makeStringSet(Arrays.asList(
                CommandName.AUTHORIZATION,
                CommandName.AUTHORIZE,
                CommandName.REGISTRATION,
                CommandName.REGISTRATE,
                CommandName.ABOUT,
                CommandName.MAIN,
                CommandName.ERROR));
        accessMap.put(User.UserRole.GUEST, guestCommands);

        Set<String> commonCommands = makeStringSet(Arrays.asList(
                CommandName.LOGOUT,
                CommandName.DELETE_ACCOUNT,
                CommandName.LOCALE,
                CommandName.PROJECTS,
                CommandName.TO_TASKS,
                CommandName.PROFILE,
                CommandName.ABOUT,
                CommandName.MAIN,
                CommandName.ERROR,
                AsyncCommandName.EDIT,
                AsyncCommandName.LOAD_AVATAR,
                AsyncCommandName.UPDATE_USER_DETAIL,
                AsyncCommandName.UPDATE_USER_STATUS));

        Set<String> managerCommands = makeStringSet(Arrays.asList(
                CommandName.BILLS,
                CommandName.FIND_TECHNICAL_TASKS,
                CommandName.TO_BILL_CREATION,
                CommandName.CREATE_BILL,
                CommandName.TO_SEEK_EMPLOYEES,
                CommandName.TO_CREATE_PROJECT,
                CommandName.CREATE_PROJECT,
                CommandName.TO_PROJECT_BILLS,
                AsyncCommandName.ACCEPT_PAYMENT,
                AsyncCommandName.ADD_EMPLOYEE,
                AsyncCommandName.ADD_TASK,
                AsyncCommandName.EMPLOYEE_REQUIREMENTS,
                AsyncCommandName.LOAD_PROJECT_REQUIREMENTS,
                AsyncCommandName.LOAD_PROJECT_USERS,
                AsyncCommandName.SEEK_EMPLOYEES));
        managerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.MANAGER, managerCommands);

        Set<String> developerCommands = makeStringSet(Arrays.asList(
                AsyncCommandName.UPDATE_TASK_HOURS,
                AsyncCommandName.UPDATE_TASK_STATUS));
        developerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.DEVELOPER, developerCommands);

        Set<String> customerCommands = makeStringSet(Arrays.asList(
                CommandName.BILLS,
                CommandName.MAKE_PAYMENT,
                CommandName.TO_CREATE_REQUIREMENT,
                CommandName.TO_CREATE_TECHNICAL_TASK,
                CommandName.TO_PAYMENT_FORM,
                CommandName.TO_PROJECT_BILLS,
                CommandName.CREATE_TECHNICAL_TASK,
                CommandName.TECHNICAL_TASKS,
                CommandName.UPDATE_TECHNICAL_TASK,
                CommandName.DELETE_TECHNICAL_TASK,
                AsyncCommandName.ADD_REQUIREMENT,
                AsyncCommandName.EMPLOYEE_REQUIREMENTS,
                AsyncCommandName.LOAD_TASKS_INFORMATION));
        customerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.CUSTOMER, customerCommands);

        Set<String> adminCommands = new HashSet<>();
        adminCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.ADMIN, adminCommands);
    }

    private Set<String> makeStringSet(List<?> list) {
        return list.stream()
                .map(value -> transformString(String.valueOf(value)))
                .collect(Collectors.toSet());
    }

    private static final String COMMAND_SEPARATOR = "-";

    private static final String ENUM_COMMAND_SEPARATOR = "_";

    static String transformString(String command) {
        return command.toLowerCase().replaceAll(
                ENUM_COMMAND_SEPARATOR, COMMAND_SEPARATOR);
    }

}
