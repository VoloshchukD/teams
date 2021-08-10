package by.voloshchuk.entity.dto;

import java.util.Objects;

public class TechnicalTaskDto {

    private String name;

    private String overview;

    private String deadline;

    private String status;

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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        TechnicalTaskDto that = (TechnicalTaskDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(overview, that.overview)
                && Objects.equals(deadline, that.deadline)
                && Objects.equals(status, that.status)
                && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, overview, deadline, status, customerId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
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
