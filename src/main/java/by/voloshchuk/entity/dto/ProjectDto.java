package by.voloshchuk.entity.dto;

import by.voloshchuk.entity.Project;

import java.util.Objects;

public class ProjectDto {

    private Project project;

    private Long managerId;

    private Long customerId;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
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
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(project, that.project)
                && Objects.equals(managerId, that.managerId)
                && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, managerId, customerId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("project=");
        builder.append(project);
        builder.append(", managerId=");
        builder.append(managerId);
        builder.append(", customerId=");
        builder.append(customerId);
        return builder.toString();
    }

}
