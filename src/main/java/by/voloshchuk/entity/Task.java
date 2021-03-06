package by.voloshchuk.entity;

import java.util.Objects;

public class Task extends AbstractEntity {

    private String name;

    private String details;

    private Integer plannedTime;

    private Integer trackedTime;

    private TaskStatus status;

    public enum TaskStatus {
        TO_DO,
        IN_PROGRESS,
        DONE
    }

    private Long projectId;

    private User developer;

    private static final long serialVersionUID = 2089395688822660901L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(Integer plannedTime) {
        this.plannedTime = plannedTime;
    }

    public Integer getTrackedTime() {
        return trackedTime;
    }

    public void setTrackedTime(Integer trackedTime) {
        this.trackedTime = trackedTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name)
                && Objects.equals(details, task.details)
                && Objects.equals(plannedTime, task.plannedTime)
                && Objects.equals(trackedTime, task.trackedTime)
                && status == task.status && Objects.equals(projectId, task.projectId)
                && Objects.equals(developer, task.developer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, details, plannedTime,
                trackedTime, status, projectId, developer);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("id=");
        builder.append(getId());
        builder.append("name=");
        builder.append(name);
        builder.append(", details=");
        builder.append(details);
        builder.append(", plannedTime=");
        builder.append(plannedTime);
        builder.append(", trackedTime=");
        builder.append(trackedTime);
        builder.append(", status=");
        builder.append(status);
        builder.append(", projectId=");
        builder.append(projectId);
        builder.append(", developer=");
        builder.append(developer);
        return builder.toString();
    }

}
