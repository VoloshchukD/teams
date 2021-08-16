package by.voloshchuk.service.validator;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.*;
import by.voloshchuk.service.validator.impl.*;

public class ValidatorProvider {

    private final Validator<UserDto> userValidator = new UserValidator();

    private final Validator<Project> projectValidator = new ProjectValidator();

    private final Validator<TechnicalTaskDto> technicalTaskValidator = new TechnicalTaskValidator();

    private final Validator<BillDto> billValidator = new BillValidator();

    private final PaymentValidator<PaymentDto> paymentValidator = new PaymentValidatorImpl();

    private final Validator<EmployeeRequirementDto> requirementValidator =
            new EmployeeRequirementValidator();

    private final Validator<TaskDto> taskValidator = new TaskValidator();

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

    public PaymentValidator<PaymentDto> getPaymentValidator() {
        return paymentValidator;
    }

    public Validator<EmployeeRequirementDto> getEmployeeRequirementValidator() {
        return requirementValidator;
    }

    public Validator<TaskDto> getTaskValidator() {
        return taskValidator;
    }

}
