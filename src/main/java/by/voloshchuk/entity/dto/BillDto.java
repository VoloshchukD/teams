package by.voloshchuk.entity.dto;

import java.util.Objects;

public class BillDto {

    private String status;

    private String information;

    private String amountDue;

    private String projectId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDto billDto = (BillDto) o;
        return Objects.equals(status, billDto.status) && Objects.equals(information, billDto.information) && Objects.equals(amountDue, billDto.amountDue) && Objects.equals(projectId, billDto.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, information, amountDue, projectId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("status=");
        builder.append(status);
        builder.append(", information=");
        builder.append(information);
        builder.append(", amountDue=");
        builder.append(amountDue);
        builder.append(", projectId=");
        builder.append(projectId);
        return builder.toString();
    }

}
