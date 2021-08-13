package by.voloshchuk.dao.builder;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Builder<T> {

    T buildEntity(ResultSet resultSet) throws SQLException;

}
