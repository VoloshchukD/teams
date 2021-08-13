package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.impl.ConstantColumnName;
import by.voloshchuk.entity.EmployeeRequirement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRequirementBuilder implements Builder<EmployeeRequirement> {

    @Override
    public EmployeeRequirement buildEntity(ResultSet resultSet) throws SQLException {
        EmployeeRequirement requirement = new EmployeeRequirement();
        requirement.setId(resultSet.getLong(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_ID));
        requirement.setExperience(resultSet.getInt(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_EXPERIENCE));
        requirement.setSalary(resultSet.getInt(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_SALARY));
        requirement.setQualification(resultSet.getString(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_QUALIFICATION));
        requirement.setPrimarySkill(resultSet.getString(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_PRIMARY_SKILL));
        requirement.setComment(resultSet.getString(
                ConstantColumnName.EMPLOYEE_REQUIREMENT_COMMENT));
        return requirement;
    }

}
