package by.voloshchuk.service.validator;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.service.validator.impl.BillValidator;
import by.voloshchuk.service.validator.impl.ProjectValidator;
import by.voloshchuk.service.validator.impl.TechnicalTaskValidator;
import by.voloshchuk.service.validator.impl.UserValidator;

public class ValidatorProvider {

    private final Validator<UserDto> userValidator = new UserValidator();

    private final Validator<Project> projectValidator = new ProjectValidator();

    private final Validator<TechnicalTaskDto> technicalTaskValidator = new TechnicalTaskValidator();

    private final Validator<BillDto> billValidator = new BillValidator();

    private ValidatorProvider() {
    }

    private static class ValidatorHolder {
        private static final ValidatorProvider INSTANCE = new ValidatorProvider();
    }

    public static ValidatorProvider getInstance() {
        return ValidatorHolder.INSTANCE;
    }

    public Validator<UserDto> getUserValidator() {
        return userValidator;
    }

    public Validator<Project> getProjectValidator() {
        return projectValidator;
    }

    public Validator<TechnicalTaskDto> getTechnicalTaskValidator() {
        return technicalTaskValidator;
    }

    public Validator<BillDto> getBillValidator() {
        return billValidator;
    }

}
