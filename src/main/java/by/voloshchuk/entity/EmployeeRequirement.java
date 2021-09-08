package by.voloshchuk.entity;

import java.util.Objects;

public class EmployeeRequirement extends AbstractEntity {

    private Integer experience;

    private Integer salary;

    private String qualification;

    private String comment;

    private Long technicalTaskId;

    private String primarySkill;

    private static final long serialVersionUID = 92144745125437748L;

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
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

    public Long getTechnicalTaskId() {
        return technicalTaskId;
    }

    public void setTechnicalTaskId(Long technicalTaskId) {
        this.technicalTaskId = technicalTaskId;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRequirement that = (EmployeeRequirement) o;
        return Objects.equals(experience, that.experience)
                && Objects.equals(salary, that.salary)
                && Objects.equals(qualification, that.qualification)
                && Objects.equals(comment, that.comment)
                && Objects.equals(technicalTaskId, that.technicalTaskId)
                && Objects.equals(primarySkill, that.primarySkill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience, salary, qualification,
                comment, technicalTaskId, primarySkill);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("id=");
        builder.append(getId());
        builder.append("experience=");
        builder.append(experience);
        builder.append(", salary=");
        builder.append(salary);
        builder.append(", qualification=");
        builder.append(qualification);
        builder.append(", comment=");
        builder.append(comment);
        builder.append(", technicalTaskId=");
        builder.append(technicalTaskId);
        builder.append(", primarySkill=");
        builder.append(primarySkill);
        return builder.toString();
    }

}

