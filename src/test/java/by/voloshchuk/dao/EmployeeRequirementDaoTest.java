package by.voloshchuk.dao;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class EmployeeRequirementDaoTest {

    private static EmployeeRequirementDao employeeRequirementDao;

    private static EmployeeRequirement employeeRequirement;

    private static final Long DATABASE_EMPLOYEE_REQUIREMENT_ID = 1L;

    private static final Long DATABASE_TECHNICAL_TASK_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        employeeRequirementDao = DaoProvider.getInstance().getEmployeeRequirementDao();
        employeeRequirement = new EmployeeRequirement();
        employeeRequirement.setId(DATABASE_EMPLOYEE_REQUIREMENT_ID);
        employeeRequirement.setSalary(15);
        employeeRequirement.setComment("-");
        employeeRequirement.setExperience(1);
        employeeRequirement.setPrimarySkill("C++");
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(DATABASE_TECHNICAL_TASK_ID);
        employeeRequirement.setTechnicalTask(technicalTask);
        employeeRequirement.setQualification("Junior developer");
    }

    @Test
    public void testAddEmployeeRequirement() throws DaoException {
        boolean added = employeeRequirementDao.addEmployeeRequirement(employeeRequirement);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindEmployeeRequirementByTechnicalTaskId() throws DaoException {
        List<EmployeeRequirement> founded = employeeRequirementDao.findAllByTechnicalTaskId(
                DATABASE_TECHNICAL_TASK_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindEmployeeRequirementByProjectId() throws DaoException {
        List<EmployeeRequirement> founded = employeeRequirementDao.findAllByProjectId(
                DATABASE_PROJECT_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testEmployeeRequirement() throws DaoException {
        String updateData = "Java";
        employeeRequirement.setPrimarySkill(updateData);
        EmployeeRequirement updated = employeeRequirementDao.updateEmployeeRequirement(
                employeeRequirement);
        Assert.assertEquals(updateData, updated.getPrimarySkill());
    }

}
