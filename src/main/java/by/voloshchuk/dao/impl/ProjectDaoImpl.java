package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.builder.ProjectBuilder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private static final String ADD_PROJECT_QUERY = "INSERT INTO projects " +
            "(project_name, description, start_date, state, technical_task_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_PROJECT_BY_ID_QUERY = "SELECT * FROM projects WHERE project_id = ?";

    private static final String FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY = "SELECT * FROM projects " +
            "INNER JOIN user_project_maps " +
            "ON projects.project_id = user_project_maps.project_id " +
            "WHERE user_project_maps.user_id = ? AND projects.state = ?";

    private static final String UPDATE_PROJECT_QUERY = "UPDATE projects " +
            "SET project_name = ?, description = ? " +
            "WHERE project_id = ?";

    private static final String UPDATE_PROJECT_STATUS_QUERY = "UPDATE projects SET state = ? " +
            "WHERE project_id = ?";

    private static final String DELETE_PROJECT_QUERY = "DELETE FROM projects WHERE project_id = ?";

    private static final String ADD_USER_TO_PROJECT_QUERY = "INSERT INTO user_project_maps " +
            "(project_id, user_id) VALUES (?, ?);";

    private static final String UPDATE_TECHNICAL_TASK_QUERY = "UPDATE technical_tasks " +
            "SET status = ? WHERE technical_task_id = ?";

    private final DaoExecutor<Project> executor;

    public ProjectDaoImpl() {
        Builder<Project> builder = new ProjectBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addProject(ProjectDto projectDto) throws DaoException {
        boolean added = false;
        CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_PROJECT_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            Project project = projectDto.getProject();
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState().toString());
            statement.setLong(5, project.getTechnicalTask().getId());
            added = (statement.executeUpdate() == 1);
            if (added) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long projectId = resultSet.getLong(1);
                project.setId(projectId);
                try (PreparedStatement userStatement = connection.prepareStatement(
                        ADD_USER_TO_PROJECT_QUERY)) {
                    userStatement.setLong(1, projectId);
                    userStatement.setLong(2, projectDto.getCustomerId());
                    userStatement.executeUpdate();
                    userStatement.setLong(1, projectId);
                    userStatement.setLong(2, projectDto.getManagerId());
                    userStatement.executeUpdate();
                }
                try (PreparedStatement userStatement = connection.prepareStatement(
                        UPDATE_TECHNICAL_TASK_QUERY)) {
                    userStatement.setString(1,
                            TechnicalTask.TechnicalTaskStatus.ON_PROJECT.toString());
                    userStatement.setLong(2, project.getTechnicalTask().getId());
                    userStatement.executeUpdate();
                }
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException exception) {
                throw new DaoException(exception);
            }
        }
        return added;
    }

    public List<Project> findProjectsByUserIdAndState(Long userId, String state)
            throws DaoException {
        Object[] parameters = {userId, state};
        List<Project> projects = executor.executeQueryMultipleResult(
                FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY, parameters);
        return projects;
    }

    public Project updateProject(Project project) throws DaoException {
        Project resultProject = null;
        Object[] parameters = {project.getName(), project.getDescription(), project.getId()};
        boolean result = executor.executeUpdate(UPDATE_PROJECT_QUERY, parameters);
        if (result) {
            resultProject = project;
        }
        return resultProject;
    }

    public String updateProjectStatus(Long projectId, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, projectId};
        boolean result = executor.executeUpdate(UPDATE_PROJECT_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return resultStatus;
    }

    public boolean removeProject(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(DELETE_PROJECT_QUERY, parameters);
        return removed;
    }

}
