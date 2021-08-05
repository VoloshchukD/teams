package by.voloshchuk.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TechnicalTask extends AbstractIdentifiedEntity {

    private String name;

    private String overview;

    private Date deadline;

    private TechnicalTaskStatus status;

    public enum TechnicalTaskStatus {
        EDITING,
        ON_PROJECT,
        WAIT_PROJECT,
        COMPLETED
    }

    private User customer;

    private List<EmployeeRequirement> requirements;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnicalTask that = (TechnicalTask) o;
        return Objects.equals(name, that.name) && Objects.equals(overview, that.overview) && Objects.equals(deadline, that.deadline) && status == that.status && Objects.equals(customer, that.customer) && Objects.equals(requirements, that.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, overview, deadline, status, customer, requirements);
    }

    @Override
    public String toString() {
        return "TechnicalTask{" +
                "name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", customer=" + customer +
                ", requirements=" + requirements +
                '}';
    }
}
