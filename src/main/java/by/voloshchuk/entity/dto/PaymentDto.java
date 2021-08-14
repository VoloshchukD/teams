package by.voloshchuk.entity.dto;

import java.util.Objects;

public class PaymentDto {

    private Long billId;

    private String cardHolder;

    private String month;

    private String year;

    private String number;

    private String cvc;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(billId, that.billId)
                && Objects.equals(cardHolder, that.cardHolder)
                && Objects.equals(month, that.month)
                && Objects.equals(year, that.year)
                && Objects.equals(number, that.number)
                && Objects.equals(cvc, that.cvc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billId, cardHolder,
                month, year, number, cvc);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                getClass().getName());
        builder.append("billId=");
        builder.append(billId);
        builder.append("cardHolder=");
        builder.append(cardHolder);
        builder.append(", month=");
        builder.append(month);
        builder.append(", year=");
        builder.append(year);
        builder.append(", number=");
        builder.append(number);
        builder.append(", cvc=");
        builder.append(cvc);
        return builder.toString();
    }

}
