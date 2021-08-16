package by.voloshchuk.entity;

import java.sql.Date;
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

    private Long technicalTaskId;

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

    public Long getTechnicalTaskId() {
        return technicalTaskId;
    }

    public void setTechnicalTaskId(Long technicalTaskId) {
        this.technicalTaskId = technicalTaskId;
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
                && Objects.equals(technicalTaskId, project.technicalTaskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startDate,
                state, technicalTaskId);
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
        builder.append(", technicalTaskId=");
        builder.append(technicalTaskId);
        return builder.toString();
    }

}
