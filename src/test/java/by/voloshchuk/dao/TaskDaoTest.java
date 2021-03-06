package by.voloshchuk.dao;

import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TaskDaoTest {

    private static TaskDao taskDao;

    private static Task task;

    private static final Long DATABASE_TASK_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        taskDao = DaoProvider.getInstance().getTaskDao();
        task = new Task();
        task.setId(DATABASE_TASK_ID);
        task.setName("Bug fix");
        task.setDetails("Fix bug at UserService");
        task.setPlannedTime(2);
        task.setStatus(Task.TaskStatus.TO_DO);
        task.setProjectId(DATABASE_PROJECT_ID);
        User developer = new User();
        developer.setId(DATABASE_USER_ID);
        task.setDeveloper(developer);
    }

    @Test
    public void testAddTask() throws DaoException {
        boolean added = taskDao.addTask(task);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindTasksByProjectIdAndUserId() throws DaoException {
        List<Task> founded = taskDao.findTasksByProjectIdAndUserId(DATABASE_PROJECT_ID, DATABASE_USER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindTaskByProjectIdAndStatus() throws DaoException {
        List<Task> founded = taskDao.findTaskByProjectIdAndStatus(
                DATABASE_PROJECT_ID, Task.TaskStatus.DONE.toString());
        Assert.assertNotNull(founded);
    }

    @Test
    public void testUpdateTaskStatus() throws DaoException {
        String updateData = Task.TaskStatus.DONE.toString();
        task.setName(updateData);
        String updated = taskDao.updateTaskStatus(DATABASE_TASK_ID, updateData);
        Assert.assertEquals(updated, updateData);
    }

    @Test
    public void testUpdateTaskHours() throws DaoException {
        Integer updateData = 4;
        Integer updated = taskDao.updateTaskHours(DATABASE_TASK_ID, updateData);
        Assert.assertEquals(updateData, updated);
    }

    @Test
    public void testUpdateTask() throws DaoException {
        String updateData = "Logics patching";
        task.setName(updateData);
        Task updated = taskDao.updateTask(task);
        Assert.assertEquals(updateData, updated.getName());
    }

}
