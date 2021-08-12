package by.voloshchuk.service;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class EmployeeRequirementServiceTest {

    private static EmployeeRequirementService employeeRequirementService;

    private static EmployeeRequirement employeeRequirement;

    private static final Long DATABASE_EMPLOYEE_REQUIREMENT_ID = 1L;

    private static final Long DATABASE_TECHNICAL_TASK_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        employeeRequirementService = ServiceProvider.getInstance().getEmployeeRequirementService();
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
    public void testAddEmployeeRequirement() throws ServiceException {
        boolean added = employeeRequirementService.addEmployeeRequirement(employeeRequirement);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindEmployeeRequirementByTechnicalTaskId() throws ServiceException {
        List<EmployeeRequirement> founded = employeeRequirementService.findAllByTechnicalTaskId(
                DATABASE_TECHNICAL_TASK_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindEmployeeRequirementByProjectId() throws ServiceException {
        List<EmployeeRequirement> founded = employeeRequirementService.findAllByProjectId(
                DATABASE_PROJECT_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testEmployeeRequirement() throws ServiceException {
        String updateData = "Java";
        employeeRequirement.setPrimarySkill(updateData);
        EmployeeRequirement updated = employeeRequirementService.updateEmployeeRequirement(
                employeeRequirement);
        Assert.assertEquals(updateData, updated.getPrimarySkill());
    }

}
