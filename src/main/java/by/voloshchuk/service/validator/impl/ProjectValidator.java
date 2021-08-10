package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class ProjectValidator implements Validator<Project> {

    @Override
    public boolean validateCreateData(Project project) {
        return validateUpdateData(project);
    }

    @Override
    public boolean validateUpdateData(Project project) {
        return validateString(project.getName(), RegexProperty.PROPERTY_PROJECT_NAME_REGEX)
                && validateString(project.getDescription(), RegexProperty.PROPERTY_PROJECT_DESCRIPTION_REGEX);
    }

}
