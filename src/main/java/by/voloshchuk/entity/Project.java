package by.voloshchuk.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Project extends AbstractEntity {

    private String name;

    private String description;

    private Date startDate;

    private ProjectStatus state;

    public enum ProjectStatus {
        STARTING,
        FINISHED,
        IN_PROGRESS
    }

    private TechnicalTask technicalTask;

    private List<User> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ProjectStatus getState() {
        return state;
    }

    public void setState(ProjectStatus state) {
        this.state = state;
    }

    public TechnicalTask getTechnicalTask() {
        return technicalTask;
    }

    public void setTechnicalTask(TechnicalTask technicalTask) {
        this.technicalTask = technicalTask;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name)
                && Objects.equals(description, project.description)
                && Objects.equals(startDate, project.startDate)
                && state == project.state
                && Objects.equals(technicalTask, project.technicalTask)
                && Objects.equals(employees, project.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startDate,
                state, technicalTask, employees);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("id=");
        builder.append(getId());
        builder.append("name=");
        builder.append(name);
        builder.append(", description=");
        builder.append(description);
        builder.append(", startDate=");
        builder.append(startDate);
        builder.append(", state=");
        builder.append(state);
        builder.append(", technicalTask=");
        builder.append(technicalTask);
        builder.append(", employees=");
        builder.append(employees);
        return builder.toString();
    }

}
