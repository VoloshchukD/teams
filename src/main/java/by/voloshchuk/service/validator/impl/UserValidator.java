package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class UserValidator implements Validator<UserDto> {

    @Override
    public boolean validateCreateData(UserDto userDto) {
        return validateString(userDto.getEmail(), RegexProperty.PROPERTY_EMAIL_REGEX)
                && validateString(userDto.getPassword(), RegexProperty.PROPERTY_PASSWORD_REGEX)
                && validateUpdateData(userDto)
                && contains(userDto.getRole());
    }

    @Override
    public boolean validateUpdateData(UserDto userDto) {
        return validateString(userDto.getFirstName(), RegexProperty.PROPERTY_FIRST_REGEX)
                && validateString(userDto.getLastName(), RegexProperty.PROPERTY_LAST_REGEX)
                && validateString(userDto.getSkillsDescription(), RegexProperty.PROPERTY_SKILLS_REGEX)
                && validateString(userDto.getSalary(), RegexProperty.PROPERTY_SALARY_REGEX)
                && validateString(userDto.getExperience(), RegexProperty.PROPERTY_EXPERIENCE_REGEX)
                && validateString(userDto.getPrimarySkill(), RegexProperty.PROPERTY_PRIMARY_REGEX)
                && validateString(userDto.getPosition(), RegexProperty.PROPERTY_POSITION_REGEX)
                && validateString(userDto.getCompany(), RegexProperty.PROPERTY_COMPANY_REGEX);
    }

    private boolean contains(String value) {
        for (User.UserRole role : User.UserRole.values()) {
            if (role.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
