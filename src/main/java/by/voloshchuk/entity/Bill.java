package by.voloshchuk.entity;

public class Bill extends AbstractIdentifiedEntity {

    private BillStatus status;

    public enum BillStatus {
        PAID,
        NOT_PAID,
        ACCEPTED
    }

    private String information;

    private Integer amountDue;

    private Long projectId;

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Integer amountDue) {
        this.amountDue = amountDue;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "status='" + status + '\'' +
                ", information='" + information + '\'' +
                ", amountDue=" + amountDue +
                ", projectId=" + projectId +
                '}';
    }

}
