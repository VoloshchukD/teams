package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private static final String ADD_PROJECT_QUERY = "INSERT INTO projects (project_name, description, start_date, " +
            "state, technical_task_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String FIND_PROJECT_BY_ID_QUERY = "SELECT * FROM projects WHERE project_id = ?";

    private static final String FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY = "SELECT * FROM teams.projects INNER JOIN teams.user_project_maps " +
            "ON teams.projects.project_id=teams.user_project_maps.project_id WHERE teams.user_project_maps.user_id = ? AND teams.projects.state = ?";

    private static final String UPDATE_PROJECT_QUERY = "UPDATE projects SET project_name = ?, description = ?, start_date = ?, state = ?," +
            " technical_task_id = ? WHERE project_id = ?";

    private static final String DELETE_PROJECT_QUERY = "DELETE FROM projects WHERE project_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addProject(Project project) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PROJECT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState());
            statement.setLong(5, project.getTechnicalTask().getId());
            isAdded = (statement.executeUpdate() == 1);
            if (isAdded) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                long projectId = resultSet.getLong(1);
                System.out.println(projectId);
                project.setId(projectId);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public Project findProjectById(Long id) throws DaoException {
        Project project = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PROJECT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            project = new Project();
            if (resultSet.next()) {
                project.setId(resultSet.getLong(ConstantColumnName.PROJECT_ID));
                project.setName(resultSet.getString(ConstantColumnName.PROJECT_NAME));
                project.setDescription(resultSet.getString(ConstantColumnName.PROJECT_DESCRIPTION));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.PROJECT_START_DATE);
                Date date = new Date(timestamp.getTime());
                project.setStartDate(date);
                project.setState(resultSet.getString(ConstantColumnName.PROJECT_STATE));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return project;
    }

    public List<Project> findProjectsByUserIdAndState(Long userId, String state) throws DaoException {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY)) {
            statement.setLong(1, userId);
            statement.setString(2, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getLong(ConstantColumnName.PROJECT_ID));
                project.setName(resultSet.getString(ConstantColumnName.PROJECT_NAME));
                project.setDescription(resultSet.getString(ConstantColumnName.PROJECT_DESCRIPTION));
                Timestamp timestamp = resultSet.getTimestamp(ConstantColumnName.PROJECT_START_DATE);
                Date date = new Date(timestamp.getTime());
                project.setStartDate(date);
                project.setState(resultSet.getString(ConstantColumnName.PROJECT_STATE));
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return projects;
    }

    public Project updateProject(Project project) throws DaoException {
        Project resultProject = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT_QUERY)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState());
            statement.setLong(5, project.getTechnicalTask().getId());
            statement.setLong(6, project.getId());
            int result = statement.executeUpdate();
            if (result == 1) {
                resultProject = project;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultProject;
    }

    public boolean removeProject(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROJECT_QUERY)) {
            statement.setLong(1, id);
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
