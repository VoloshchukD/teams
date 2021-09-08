package by.voloshchuk.entity;

import java.util.Objects;

public class UserDetail extends AbstractEntity {

    private String imagePath;

    private String firstName;

    private String lastName;

    private String company;

    private String position;

    private Integer experience;

    private Integer salary;

    private String primarySkill;

    private String skillsDescription;

    private Status status;

    public enum Status {
        BUSY,
        NOT_BUSY,
        DELETED
    }

    private static final long serialVersionUID = 1106991494272214864L;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSkillsDescription() {
        return skillsDescription;
    }

    public void setSkillsDescription(String skillsDescription) {
        this.skillsDescription = skillsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetail that = (UserDetail) o;
        return Objects.equals(imagePath, that.imagePath)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(company, that.company)
                && Objects.equals(position, that.position)
                && Objects.equals(experience, that.experience)
                && Objects.equals(salary, that.salary)
                && Objects.equals(primarySkill, that.primarySkill)
                && Objects.equals(skillsDescription, that.skillsDescription)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imagePath, firstName, lastName, company,
                position, experience, salary, primarySkill, skillsDescription, status);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("id=");
        builder.append(getId());
        builder.append("imagePath=");
        builder.append(imagePath);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", company=");
        builder.append(company);
        builder.append(", position=");
        builder.append(position);
        builder.append(", experience=");
        builder.append(experience);
        builder.append(", salary=");
        builder.append(salary);
        builder.append(", primarySkill=");
        builder.append(primarySkill);
        builder.append(", skillsDescription=");
        builder.append(skillsDescription);
        builder.append(", status=");
        builder.append(status);
        return builder.toString();
    }

}
