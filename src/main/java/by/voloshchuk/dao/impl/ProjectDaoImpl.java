package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private static final String SQL_ADD_PROJECT = "INSERT INTO projects (project_name, description, start_date, " +
            "state, technical_task_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_FIND_PROJECT_BY_ID = "SELECT * FROM projects WHERE project_id = ?";

    private static final String SQL_FIND_PROJECTS_BY_USER_ID_AND_STATE = "SELECT * FROM teams.projects INNER JOIN teams.user_project_maps " +
            "ON teams.projects.project_id=teams.user_project_maps.project_id WHERE teams.user_project_maps.user_id = ? AND teams.projects.state = ?";

    private static final String SQL_UPDATE_PROJECT = "UPDATE projects SET project_name = ?, description = ?, start_date = ?, state = ?," +
            " technical_task_id = ? WHERE project_id = ?";

    private static final String SQL_DELETE_PROJECT = "DELETE FROM projects WHERE project_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addProject(Project project) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_PROJECT)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState());
            statement.setString(5, String.valueOf(project.getTechnicalTask().getId()));
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public Project findProjectById(Long id) throws DaoException {
        Project project = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECT_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            project = new Project();
            if (resultSet.next()) {
                project.setId(Long.valueOf(resultSet.getString(ConstantColumnName.PROJECT_ID)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECTS_BY_USER_ID_AND_STATE)) {
            statement.setLong(1, userId);
            statement.setString(2, state);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(Long.valueOf(resultSet.getString(ConstantColumnName.PROJECT_ID)));
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
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PROJECT)) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setTimestamp(3, new Timestamp(project.getStartDate().getTime()));
            statement.setString(4, project.getState());
            statement.setString(5, String.valueOf(project.getTechnicalTask().getId()));
            statement.setString(6, String.valueOf(project.getId()));
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
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PROJECT)) {
            statement.setString(1, String.valueOf(id));
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
