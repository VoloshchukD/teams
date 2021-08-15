package by.voloshchuk.entity.dto;

import java.util.Objects;

public class TaskDto {

    private Long taskId;

    private String name;

    private String userId;

    private String projectId;

    private String details;

    private String plannedTime;

    private String trackedTime;

    private String status;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(String plannedTime) {
        this.plannedTime = plannedTime;
    }

    public String getTrackedTime() {
        return trackedTime;
    }

    public void setTrackedTime(String trackedTime) {
        this.trackedTime = trackedTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return Objects.equals(name, taskDto.name)
                && Objects.equals(taskId, taskDto.taskId)
                && Objects.equals(userId, taskDto.userId)
                && Objects.equals(projectId, taskDto.projectId)
                && Objects.equals(details, taskDto.details)
                && Objects.equals(plannedTime, taskDto.plannedTime)
                && Objects.equals(trackedTime, taskDto.trackedTime)
                && Objects.equals(status, taskDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name, userId, projectId, details,
                plannedTime, trackedTime, status);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("taskId=");
        builder.append(taskId);
        builder.append(", name=");
        builder.append(name);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", projectId=");
        builder.append(projectId);
        builder.append(", details=");
        builder.append(details);
        builder.append(", plannedTime=");
        builder.append(plannedTime);
        builder.append(", trackedTime=");
        builder.append(trackedTime);
        builder.append(", status=");
        builder.append(status);
        return builder.toString();
    }

}
