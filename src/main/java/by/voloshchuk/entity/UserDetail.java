package by.voloshchuk.entity;

import java.util.Objects;

public class UserDetail extends AbstractIdentifiedEntity {

    private String firstName;

    private String lastName;

    private String company;

    private String position;

    private Integer experience;

    private Integer salary;

    private String primarySkill;

    private String skillsDescription;

    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(company, that.company) && Objects.equals(position, that.position)
                && Objects.equals(experience, that.experience) && Objects.equals(salary, that.salary)
                && Objects.equals(primarySkill, that.primarySkill)
                && Objects.equals(skillsDescription, that.skillsDescription)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, company, position,
                experience, salary, primarySkill, skillsDescription, status);
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "@firstName='" + firstName +
                ", lastName='" + lastName +
                ", company='" + company +
                ", position='" + position +
                ", experience=" + experience +
                ", salary=" + salary +
                ", primarySkill='" + primarySkill +
                ", skillsDescription='" + skillsDescription +
                ", status='" + status;
    }

}
