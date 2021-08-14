package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.dto.EmployeeRequirementDto;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class EmployeeRequirementValidator implements Validator<EmployeeRequirementDto> {

    @Override
    public boolean validateCreateData(EmployeeRequirementDto requirement) {
        return validateString(requirement.getTechnicalTaskId(), RegexProperty.PROPERTY_NUMBER_REGEX)
                && validateUpdateData(requirement);
    }

    @Override
    public boolean validateUpdateData(EmployeeRequirementDto requirement) {
        return validateString(requirement.getPrimarySkill(),
                RegexProperty.PROPERTY_REQUIREMENT_PRIMARY_REGEX)
                && validateString(requirement.getComment(),
                RegexProperty.PROPERTY_REQUIREMENT_COMMENT_REGEX)
                && validateString(requirement.getSalary(),
                RegexProperty.PROPERTY_REQUIREMENT_SALARY_REGEX)
                && validateString(requirement.getExperience(),
                RegexProperty.PROPERTY_REQUIREMENT_EXPERIENCE_REGEX)
                && validateString(requirement.getQualification(),
                RegexProperty.PROPERTY_REQUIREMENT_QUALIFICATION_REGEX);
    }

}
