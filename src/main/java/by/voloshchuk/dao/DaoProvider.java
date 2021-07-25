package by.voloshchuk.dao;

import by.voloshchuk.dao.impl.*;

public class DaoProvider {

    private final BillDao billDao = new BillDaoImpl();

    private final EmployeeRequirementDao employeeRequirementDao = new EmployeeRequirementDaoImpl();

    private final ProjectDao projectDao = new ProjectDaoImpl();

    private final TaskDao taskDao = new TaskDaoImpl();

    private final TechnicalTaskDao technicalTaskDao = new TechnicalTaskDaoImpl();

    private final UserDao userDao = new UserDaoImpl();

    private final UserDetailDao userDetailDao = new UserDetailDaoImpl();

    private DaoProvider() {
    }

    private static class DaoProviderHolder {
        private static final DaoProvider INSTANCE = new DaoProvider();
    }

    public static DaoProvider getInstance() {
        return DaoProviderHolder.INSTANCE;
    }

    public BillDao getBillDao() {
        return billDao;
    }

    public EmployeeRequirementDao getEmployeeRequirementDao() {
        return employeeRequirementDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public TechnicalTaskDao getTechnicalTaskDao() {
        return technicalTaskDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserDetailDao getUserDetailDao() {
        return userDetailDao;
    }

}
