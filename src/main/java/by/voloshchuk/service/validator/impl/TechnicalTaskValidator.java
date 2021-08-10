package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class TechnicalTaskValidator implements Validator<TechnicalTaskDto> {

    @Override
    public boolean validateCreateData(TechnicalTaskDto task) {
        return validateString(task.getDeadline(), RegexProperty.PROPERTY_TECHNICAL_TASK_DEADLINE_REGEX)
                && validateUpdateData(task);
    }

    @Override
    public boolean validateUpdateData(TechnicalTaskDto task) {
        return validateString(task.getName(), RegexProperty.PROPERTY_TECHNICAL_TASK_NAME_REGEX)
                && validateString(task.getOverview(), RegexProperty.PROPERTY_TECHNICAL_TASK_OVERVIEW_REGEX);
    }

}
