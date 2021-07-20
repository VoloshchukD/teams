package by.voloshchuk.entity;

import java.util.Date;
import java.util.List;

public class TechnicalTask extends AbstractIdentifiedEntity {

    private String overview;

    private Date deadline;

    private Integer workersAmount;

    private String status;

    private User customer;

    private List<EmployeeRequirement> requirements;

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

    public Integer getWorkersAmount() {
        return workersAmount;
    }

    public void setWorkersAmount(Integer workersAmount) {
        this.workersAmount = workersAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<EmployeeRequirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<EmployeeRequirement> requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "TechnicalTask{" +
                "overview='" + overview + '\'' +
                ", deadline=" + deadline +
                ", workersAmount=" + workersAmount +
                ", customer=" + customer +
//                ", requirements=" + requirements +
                '}';
    }
}
