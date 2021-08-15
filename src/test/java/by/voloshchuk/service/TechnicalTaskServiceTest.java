package by.voloshchuk.service;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TechnicalTaskServiceTest {

    private static TechnicalTaskService technicalTaskService;

    private static TechnicalTaskDto technicalTaskDto;

    private static final Long DATABASE_TECHNICAL_TASK_ID = 1L;

    private static final Long DATABASE_CUSTOMER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        technicalTaskService = ServiceProvider.getInstance().getTechnicalTaskService();
        technicalTaskDto = new TechnicalTaskDto();
        technicalTaskDto.setCustomerId(DATABASE_CUSTOMER_ID);
        technicalTaskDto.setName("Test");
        technicalTaskDto.setOverview("Test data");
        technicalTaskDto.setStatus(TechnicalTask.TechnicalTaskStatus.ON_PROJECT.toString());
    }

    @Test
    public void testAddTechnicalTask() throws ServiceException {
        technicalTaskDto.setDeadline("2020-01-01 00:00:00");
        boolean added = technicalTaskService.addTechnicalTask(technicalTaskDto);
        Assert.assertTrue(added);
    }

    @Test
    public void testAddInvalidTechnicalTask() throws ServiceException {
        java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
        java.sql.Date databaseValue = new java.sql.Date(currentDate.getTime());
        technicalTaskDto.setDeadline(databaseValue.toString());
        boolean added = technicalTaskService.addTechnicalTask(technicalTaskDto);
        Assert.assertFalse(added);
    }

    @Test
    public void testFindTechnicalTaskById() throws ServiceException {
        TechnicalTask resultTechnicalTask = technicalTaskService.findTechnicalTaskById(
                DATABASE_TECHNICAL_TASK_ID);
        Assert.assertNotNull(resultTechnicalTask);
    }

    @Test
    public void testFindTechnicalTasksByCustomerId() throws ServiceException {
        List<TechnicalTask> founded = technicalTaskService.findTechnicalTasksByUserId(
                DATABASE_CUSTOMER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindTechnicalTasksByStatus() throws ServiceException {
        List<TechnicalTask> founded = technicalTaskService.findTechnicalTasksByStatus(
                TechnicalTask.TechnicalTaskStatus.COMPLETED.toString());
        Assert.assertNotNull(founded);
    }

}
