package by.voloshchuk.dao;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TechnicalTaskDaoTest {

    private static TechnicalTaskDao technicalTaskDao;

    private static TechnicalTask technicalTask;

    private static final Long DATABASE_TECHNICAL_TASK_ID = 1L;

    private static final Long DATABASE_CUSTOMER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        technicalTaskDao = DaoProvider.getInstance().getTechnicalTaskDao();
        technicalTask = new TechnicalTask();
        technicalTask.setId(DATABASE_TECHNICAL_TASK_ID);
        User user = new User();
        user.setId(DATABASE_CUSTOMER_ID);
        technicalTask.setCustomer(user);
        java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
        java.sql.Date databaseValue = new java.sql.Date(currentDate.getTime());
        technicalTask.setName("Test");
        technicalTask.setDeadline(databaseValue);
        technicalTask.setOverview("Test data");
        technicalTask.setStatus(TechnicalTask.TechnicalTaskStatus.ON_PROJECT);
    }

    @Test
    public void testAddTechnicalTask() throws DaoException {
        boolean added = technicalTaskDao.addTechnicalTask(technicalTask);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindTechnicalTaskById() throws DaoException {
        TechnicalTask resultTechnicalTask = technicalTaskDao.findTechnicalTaskById(
                DATABASE_TECHNICAL_TASK_ID);
        Assert.assertNotNull(resultTechnicalTask);
    }

    @Test
    public void testUpdateTechnicalTask() throws DaoException {
        String updateData = "Updated data";
        technicalTask.setName(updateData);
        TechnicalTask updatedTechnicalTask = technicalTaskDao.updateTechnicalTask(technicalTask);
        Assert.assertEquals(updateData, updatedTechnicalTask.getName());
    }

    @Test
    public void testFindTechnicalTasksByCustomerId() throws DaoException {
        List<TechnicalTask> founded = technicalTaskDao.findTechnicalTasksByUserId(
                DATABASE_CUSTOMER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindTechnicalTasksByStatus() throws DaoException {
        List<TechnicalTask> founded = technicalTaskDao.findTechnicalTasksByStatus(
                TechnicalTask.TechnicalTaskStatus.COMPLETED.toString());
        Assert.assertNotNull(founded);
    }

}
