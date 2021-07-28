package by.voloshchuk.service;

import by.voloshchuk.service.impl.ProjectServiceImpl;
import by.voloshchuk.service.impl.TechnicalTaskServiceImpl;
import by.voloshchuk.service.impl.UserDetailServiceImpl;
import by.voloshchuk.service.impl.UserServiceImpl;

public class ServiceProvider {

    private final ProjectService projectService = new ProjectServiceImpl();

    private final TechnicalTaskService technicalTaskService = new TechnicalTaskServiceImpl();

    private final UserDetailService userDetailService = new UserDetailServiceImpl();

    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {
    }

    private static class ServiceProviderHolder {
        private static final ServiceProvider INSTANCE = new ServiceProvider();
    }

    public static ServiceProvider getInstance() {
        return ServiceProvider.ServiceProviderHolder.INSTANCE;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public TechnicalTaskService getTechnicalTaskService() {
        return technicalTaskService;
    }

    public UserDetailService getUserDetailService() {
        return userDetailService;
    }

    public UserService getUserService() {
        return userService;
    }

}
