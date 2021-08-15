package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private UserDetailBuilder userDetailBuilder;

    public UserBuilder() {
        userDetailBuilder = new UserDetailBuilder();
    }

    @Override
    public User buildEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ConstantColumnName.USER_ID));
        user.setEmail(resultSet.getString(ConstantColumnName.USER_EMAIL));
        user.setPassword(resultSet.getString(ConstantColumnName.USER_PASSWORD));
        user.setRole(User.UserRole.valueOf(resultSet.getString(ConstantColumnName.USER_ROLE)));
        UserDetail userDetail = userDetailBuilder.buildEntity(resultSet);
        user.setUserDetail(userDetail);
        return user;
    }

}
