package by.voloshchuk.dao;

import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.AbstractEntity;
import by.voloshchuk.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            fillStatement(statement, parameters);
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
            fillStatement(statement, parameters);
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
            fillStatement(statement, parameters);
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                entities.add(entityBuilder.buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

    public boolean executeUpdateTransactionMultiple(String[] queries, Object[][] parameters)
            throws DaoException {
        boolean result = false;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            for (int i = 0; i < queries.length; i++) {
                try (PreparedStatement statement = connection.prepareStatement(queries[i])) {
                    fillStatement(statement, parameters[i]);
                    statement.executeUpdate();
                }
            }
            connection.commit();
            result = true;
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
        return result;
    }

    public static void fillStatement(PreparedStatement statement, Object[] parameters)
            throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }

}
