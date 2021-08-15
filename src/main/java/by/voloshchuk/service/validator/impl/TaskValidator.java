package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class TaskValidator implements Validator<TaskDto> {

    @Override
    public boolean validateCreateData(TaskDto task) {
        return validateUpdateData(task)
                && validateString(task.getProjectId(), RegexProperty.PROPERTY_NUMBER_REGEX);
    }

    @Override
    public boolean validateUpdateData(TaskDto task) {
        return validateString(task.getName(), RegexProperty.PROPERTY_TASK_NAME_REGEX)
                && validateString(task.getDetails(), RegexProperty.PROPERTY_TASK_DETAILS_REGEX)
                && validateString(task.getPlannedTime(), RegexProperty.PROPERTY_TASK_HOURS_REGEX);
    }

}
