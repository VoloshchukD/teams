package by.voloshchuk.dao;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Project;
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
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        taskDao = DaoProvider.getInstance().getTaskDao();
        task = new Task();
        task.setId(DATABASE_TASK_ID);
        task.setName("Bug fix");
        task.setDetails("Fix bug at UserService");
        task.setPlannedTime(2);
        task.setStatus(Task.TaskStatus.TO_DO);
        Project project = new Project();
        project.setId(DATABASE_PROJECT_ID);
        task.setProject(project);
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
    public void testUpdateTask() throws DaoException {
        String updateData = "Logics patching";
        task.setName(updateData);
        Task updated = taskDao.updateTask(task);
        Assert.assertEquals(updateData, updated.getName());
    }

}
