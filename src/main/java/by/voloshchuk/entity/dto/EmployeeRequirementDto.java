package by.voloshchuk.entity.dto;

import java.util.Objects;

public class EmployeeRequirementDto {

    private Long requirementId;

    private String experience;

    private String salary;

    private String qualification;

    private String comment;

    private String primarySkill;

    private String technicalTaskId;

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getTechnicalTaskId() {
        return technicalTaskId;
    }

    public void setTechnicalTaskId(String technicalTaskId) {
        this.technicalTaskId = technicalTaskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRequirementDto that = (EmployeeRequirementDto) o;
        return Objects.equals(experience, that.experience)
                && Objects.equals(requirementId, that.requirementId)
                && Objects.equals(salary, that.salary)
                && Objects.equals(qualification, that.qualification)
                && Objects.equals(comment, that.comment)
                && Objects.equals(primarySkill, that.primarySkill)
                && Objects.equals(technicalTaskId, that.technicalTaskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementId, experience, salary, qualification,
                comment, primarySkill, technicalTaskId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(
                getClass().getName());
        builder.append("requirementId=");
        builder.append(requirementId);
        builder.append("experience=");
        builder.append(experience);
        builder.append("salary=");
        builder.append(salary);
        builder.append(", qualification=");
        builder.append(qualification);
        builder.append(", comment=");
        builder.append(comment);
        builder.append(", primarySkill=");
        builder.append(primarySkill);
        builder.append(", technicalTaskId=");
        builder.append(technicalTaskId);
        return builder.toString();
    }

}
