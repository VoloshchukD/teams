package by.voloshchuk.entity;

public class EmployeeRequirement extends AbstractIdentifiedEntity {

    private Integer experience;

    private Integer salary;

    private String qualification;

    private String comment;

    private TechnicalTask technicalTask;

    private String primarySkill;

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

    public TechnicalTask getTechnicalTask() {
        return technicalTask;
    }

    public void setTechnicalTask(TechnicalTask technicalTask) {
        this.technicalTask = technicalTask;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    @Override
    public String toString() {
        return "EmployeeRequirement{" +
                "experience=" + experience +
                ", salary=" + salary +
                ", qualification='" + qualification + '\'' +
                ", comment='" + comment + '\'' +
                ", technicalTask=" + technicalTask +
                ", primarySkill='" + primarySkill + '\'' +
                '}';
    }
}
