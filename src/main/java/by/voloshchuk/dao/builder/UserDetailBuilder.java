package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.ConstantColumnName;
import by.voloshchuk.entity.UserDetail;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailBuilder implements Builder<UserDetail> {

    @Override
    public UserDetail buildEntity(ResultSet resultSet) throws SQLException {
        UserDetail userDetail = new UserDetail();
        userDetail.setId(resultSet.getLong(ConstantColumnName.USER_DETAIL_ID));
        userDetail.setImagePath(resultSet.getString(ConstantColumnName.USER_DETAIL_IMAGE));
        userDetail.setFirstName(resultSet.getString(ConstantColumnName.USER_DETAIL_FIRST_NAME));
        userDetail.setLastName(resultSet.getString(ConstantColumnName.USER_DETAIL_LAST_NAME));
        userDetail.setCompany(resultSet.getString(ConstantColumnName.USER_DETAIL_COMPANY));
        userDetail.setPosition(resultSet.getString(ConstantColumnName.USER_DETAIL_POSITION));
        userDetail.setExperience(resultSet.getInt(ConstantColumnName.USER_DETAIL_EXPERIENCE));
        userDetail.setSalary(resultSet.getInt(ConstantColumnName.USER_DETAIL_SALARY));
        userDetail.setPrimarySkill(resultSet.getString(
                ConstantColumnName.USER_DETAIL_PRIMARY_SKILL));
        userDetail.setSkillsDescription(resultSet.getString(
                ConstantColumnName.USER_DETAIL_SKILLS_DESCRIPTION));
        userDetail.setStatus(UserDetail.Status.valueOf(resultSet.getString(
                ConstantColumnName.USER_DETAIL_STATUS)));
        return userDetail;
    }

}
