package by.voloshchuk.dao;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ProjectDaoTest {

    private static ProjectDao projectDao;

    private static ProjectDto projectDto;

    private static Project project;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_TECHNICAL_TASK_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        projectDao = DaoProvider.getInstance().getProjectDao();
        projectDto = new ProjectDto();
        project = new Project();
        project.setId(DATABASE_PROJECT_ID);
        project.setName("Food delivery application");
        project.setDescription("Application for client executor interaction");
        java.util.Date currentDate = new java.util.Date(System.currentTimeMillis());
        java.sql.Date databaseValue = new java.sql.Date(currentDate.getTime());
        project.setStartDate(databaseValue);
        project.setTechnicalTaskId(DATABASE_TECHNICAL_TASK_ID);
        project.setState(Project.ProjectStatus.IN_PROGRESS);
        projectDto.setProject(project);
        projectDto.setManagerId(DATABASE_USER_ID);
        projectDto.setCustomerId(DATABASE_USER_ID);
    }

    @Test
    public void testAddProject() throws DaoException {
        boolean added = projectDao.addProject(projectDto);
        Assert.assertTrue(added);
    }

    @Test
    public void testUpdateTechnicalTask() throws DaoException {
        String updateData = "Delivery";
        project.setName(updateData);
        Project updated = projectDao.updateProject(project);
        Assert.assertEquals(updateData, updated.getName());
    }

    @Test
    public void testFindProjectsByUserIdAndState() throws DaoException {
        List<Project> founded = projectDao.findProjectsByUserIdAndState(
                DATABASE_USER_ID, Project.ProjectStatus.FINISHED.toString());
        Assert.assertNotNull(founded);
    }

}
