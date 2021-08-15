package by.voloshchuk.service;

import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TaskServiceTest {

    private static TaskService taskService;

    private static TaskDto task;

    private static final Long DATABASE_TASK_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        taskService = ServiceProvider.getInstance().getTaskService();
        task = new TaskDto();
        task.setTaskId(DATABASE_TASK_ID);
        task.setName("Bug fix");
        task.setDetails("Fix bug at UserService");
        task.setPlannedTime("2");
        task.setStatus(Task.TaskStatus.TO_DO.toString());
        task.setProjectId(DATABASE_PROJECT_ID.toString());
        task.setUserId(DATABASE_USER_ID.toString());
    }

    @Test
    public void testAddTask() throws ServiceException {
        boolean added = taskService.addTask(task);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindTasksByProjectIdAndUserId() throws ServiceException {
        List<Task> founded = taskService.findTasksByProjectIdAndUserId(DATABASE_PROJECT_ID,
                DATABASE_USER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindTaskByProjectIdAndStatus() throws ServiceException {
        List<Task> founded = taskService.findTaskByProjectIdAndStatus(
                DATABASE_PROJECT_ID, Task.TaskStatus.DONE.toString());
        Assert.assertNotNull(founded);
    }

    @Test
    public void testUpdateTask() throws ServiceException {
        String updateData = "Logics patching";
        task.setName(updateData);
        Task updated = taskService.updateTask(task);
        Assert.assertEquals(updateData, updated.getName());
    }

}
