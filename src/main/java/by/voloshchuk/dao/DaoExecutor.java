package by.voloshchuk.dao;

import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.AbstractEntity;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoExecutor<T extends AbstractEntity> {

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private Builder<T> entityBuilder;

    public DaoExecutor(Builder<T> entityBuilder) {
        this.entityBuilder = entityBuilder;
    }

    public boolean executeUpdate(String query, Object[] parameters) throws DaoException {
        boolean updated = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            updated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return updated;
    }

    public T executeQuery(String query, Object[] parameters) throws DaoException {
        T entity = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            if ((resultSet.next())) {
                entity = entityBuilder.buildEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    public List<T> executeQueryMultipleResult(String query, Object[] parameters) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                entities.add(entityBuilder.buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

}
