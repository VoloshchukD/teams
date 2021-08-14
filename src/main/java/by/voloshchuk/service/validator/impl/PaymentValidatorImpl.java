package by.voloshchuk.service.validator.impl;

import by.voloshchuk.entity.dto.PaymentDto;
import by.voloshchuk.service.validator.PaymentValidator;
import by.voloshchuk.util.RegexProperty;

public class PaymentValidatorImpl implements PaymentValidator<PaymentDto> {

    @Override
    public boolean validatePayment(PaymentDto paymentData) {
        return ((paymentData.getCvc() != null)
                && (paymentData.getCardHolder() != null)
                && (paymentData.getMonth() != null)
                && (paymentData.getYear() != null)
                && (paymentData.getNumber() != null))
                && ((paymentData.getCvc().matches(RegexProperty.PROPERTY_PAYMENT_CVC_REGEX))
                && (paymentData.getMonth().matches(RegexProperty.PROPERTY_PAYMENT_MM_REGEX))
                && (paymentData.getYear().matches(RegexProperty.PROPERTY_PAYMENT_YY_REGEX))
                && (paymentData.getNumber().matches(RegexProperty.PROPERTY_PAYMENT_NUMBER_REGEX))
                && (paymentData.getCardHolder().matches(RegexProperty.PROPERTY_PAYMENT_HOLDER_REGEX)));
    }

}
