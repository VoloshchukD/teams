package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.util.RegexProperty;

public class BillValidator implements Validator<BillDto> {

    @Override
    public boolean validateCreateData(BillDto bill) {
        return validateString(bill.getProjectId(), RegexProperty.PROPERTY_NUMBER_REGEX)
                && validateUpdateData(bill);
    }

    @Override
    public boolean validateUpdateData(BillDto bill) {
        return validateString(bill.getAmountDue(), RegexProperty.PROPERTY_PROJECT_NAME_REGEX)
                && validateString(bill.getInformation(), RegexProperty.PROPERTY_PROJECT_DESCRIPTION_REGEX);
    }

}
