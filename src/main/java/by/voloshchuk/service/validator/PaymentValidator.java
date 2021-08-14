package by.voloshchuk.service.validator;

public interface PaymentValidator<T> {

    boolean validatePayment(T paymentData);

}
