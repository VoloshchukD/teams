package by.voloshchuk.entity.dto;

import by.voloshchuk.entity.Project;

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

}
