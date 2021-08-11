package by.voloshchuk.service;

import by.voloshchuk.service.impl.*;

/**
 * Service provider for managing service implementations.
 *
 * @author Daniil Voloshchuk
 */
public class ServiceProvider {

    private final ProjectService projectService = new ProjectServiceImpl();

    private final TechnicalTaskService technicalTaskService = new TechnicalTaskServiceImpl();

    private final UserDetailService userDetailService = new UserDetailServiceImpl();

    private final UserService userService = new UserServiceImpl();

    private final BillService billService = new BillServiceImpl();

    private final EmployeeRequirementService employeeRequirementService = new EmployeeRequirementServiceImpl();

    private final TaskService taskService = new TaskServiceImpl();

    private ServiceProvider() {
    }

    private static class ServiceProviderHolder {
        private static final ServiceProvider INSTANCE = new ServiceProvider();
    }

    public static ServiceProvider getInstance() {
        return ServiceProvider.ServiceProviderHolder.INSTANCE;
    }

    /**
     * Get {@link ProjectService} implementation.
     *
     * @return {@link ProjectServiceImpl}
     */
    public ProjectService getProjectService() {
        return projectService;
    }

    /**
     * Get {@link TechnicalTaskService} implementation.
     *
     * @return {@link TechnicalTaskServiceImpl}
     */
    public TechnicalTaskService getTechnicalTaskService() {
        return technicalTaskService;
    }

    /**
     * Get {@link UserDetailService} implementation.
     *
     * @return {@link UserDetailServiceImpl}
     */
    public UserDetailService getUserDetailService() {
        return userDetailService;
    }

    /**
     * Get {@link UserService} implementation.
     *
     * @return {@link UserServiceImpl}
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Get {@link BillService} implementation.
     *
     * @return {@link BillServiceImpl}
     */
    public BillService getBillService() {
        return billService;
    }

    /**
     * Get {@link EmployeeRequirementService} implementation.
     *
     * @return {@link EmployeeRequirementServiceImpl}
     */
    public EmployeeRequirementService getEmployeeRequirementService() {
        return employeeRequirementService;
    }

    /**
     * Get {@link TaskService} implementation.
     *
     * @return {@link TaskServiceImpl}
     */
    public TaskService getTaskService() {
        return taskService;
    }

}
