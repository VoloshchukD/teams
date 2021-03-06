package by.voloshchuk.controller.filter;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.User;
import by.voloshchuk.util.StringFormatter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Filter for limiting user access to commands.
 *
 * @author Daniil Voloshchuk
 */
public class AccessFilter implements Filter {

    private final Map<User.UserRole, Set<String>> accessMap = new HashMap<>();

    private void initGuestCommands() {
        Set<String> guestCommands = makeStringSet(Arrays.asList(
                CommandName.AUTHORIZATION,
                CommandName.AUTHORIZE,
                CommandName.REGISTRATION,
                CommandName.REGISTRATE,
                CommandName.ABOUT,
                CommandName.MAIN,
                CommandName.ERROR));
        accessMap.put(User.UserRole.GUEST, guestCommands);
    }

    private Set<String> initCommonCommands() {
        return makeStringSet(Arrays.asList(
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
    }

    private void initManagerCommands(Set<String> commonCommands) {
        Set<String> managerCommands = makeStringSet(Arrays.asList(
                CommandName.BILLS,
                CommandName.FIND_TECHNICAL_TASKS,
                CommandName.TO_BILL_CREATION,
                CommandName.CREATE_BILL,
                CommandName.TO_SEEK_EMPLOYEES,
                CommandName.TO_CREATE_PROJECT,
                CommandName.CREATE_PROJECT,
                CommandName.TO_PROJECT_BILLS,
                CommandName.UPDATE_PROJECT,
                CommandName.DELETE_PROJECT,
                CommandName.UPDATE_BILL,
                CommandName.DELETE_BILL,
                CommandName.UPDATE_TASK,
                CommandName.FINISH_PROJECT,
                AsyncCommandName.DELETE_TASK,
                AsyncCommandName.ACCEPT_PAYMENT,
                AsyncCommandName.ADD_EMPLOYEE,
                AsyncCommandName.DELETE_EMPLOYEE,
                AsyncCommandName.ADD_TASK,
                AsyncCommandName.EMPLOYEE_REQUIREMENTS,
                AsyncCommandName.LOAD_PROJECT_REQUIREMENTS,
                AsyncCommandName.LOAD_PROJECT_USERS,
                AsyncCommandName.SEEK_EMPLOYEES,
                AsyncCommandName.LOAD_TASKS_INFORMATION,
                AsyncCommandName.SEARCH_EMPLOYEES));
        managerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.MANAGER, managerCommands);
    }

    private void initDeveloperCommands(Set<String> commonCommands) {
        Set<String> developerCommands = makeStringSet(Arrays.asList(
                AsyncCommandName.UPDATE_TASK_HOURS,
                AsyncCommandName.UPDATE_TASK_STATUS));
        developerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.DEVELOPER, developerCommands);
    }

    private void initCustomerCommands(Set<String> commonCommands) {
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
                CommandName.UPDATE_REQUIREMENT,
                AsyncCommandName.ADD_REQUIREMENT,
                AsyncCommandName.EMPLOYEE_REQUIREMENTS,
                AsyncCommandName.LOAD_TASKS_INFORMATION,
                AsyncCommandName.DELETE_REQUIREMENT));
        customerCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.CUSTOMER, customerCommands);
    }

    private void initAdminCommands(Set<String> commonCommands) {
        Set<String> adminCommands = new HashSet<>();
        adminCommands.addAll(commonCommands);
        accessMap.put(User.UserRole.ADMIN, adminCommands);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        initGuestCommands();
        Set<String> commonCommands = initCommonCommands();
        initManagerCommands(commonCommands);
        initDeveloperCommands(commonCommands);
        initCustomerCommands(commonCommands);
        initAdminCommands(commonCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User.UserRole role = (User.UserRole) request.getSession(true)
                .getAttribute(CommandAttribute.ROLE);
        if (role == null) {
            role = User.UserRole.GUEST;
        }
        String command = request.getParameter(RequestParameter.COMMAND);

        if (!accessMap.get(role).contains(command)) {
            if (containsCommand(command)) {
                request.getRequestDispatcher(CommandPath.ERROR_403_JSP).forward(request, response);
            } else {
                request.getRequestDispatcher(CommandPath.ERROR_404_JSP).forward(request, response);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean containsCommand(String command) {
        return accessMap.entrySet().stream()
                .anyMatch(entry -> entry.getValue().contains(command));
    }

    private Set<String> makeStringSet(List<?> list) {
        return list.stream()
                .map(value -> StringFormatter.transformEnumString(String.valueOf(value)))
                .collect(Collectors.toSet());
    }

}
