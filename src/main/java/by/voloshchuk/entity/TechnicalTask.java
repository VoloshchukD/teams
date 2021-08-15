package by.voloshchuk.entity;

import java.util.Date;
import java.util.Objects;

public class TechnicalTask extends AbstractEntity {

    private String name;

    private String overview;

    private Date deadline;

    private TechnicalTaskStatus status;

    public enum TechnicalTaskStatus {
        ON_PROJECT,
        WAIT_PROJECT,
        COMPLETED
    }

    private Long customerId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TechnicalTaskStatus getStatus() {
        return status;
    }

    public void setStatus(TechnicalTaskStatus status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalTask that = (TechnicalTask) o;
        return Objects.equals(name, that.name)
                && Objects.equals(overview, that.overview)
                && Objects.equals(deadline, that.deadline)
                && status == that.status
                && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, overview, deadline,
                status, customerId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                getClass().getName());
        builder.append("id=");
        builder.append(getId());
        builder.append("name=");
        builder.append(name);
        builder.append(", overview=");
        builder.append(overview);
        builder.append(", deadline=");
        builder.append(deadline);
        builder.append(", status=");
        builder.append(status);
        builder.append(", customerId=");
        builder.append(customerId);
        return builder.toString();
    }

}
