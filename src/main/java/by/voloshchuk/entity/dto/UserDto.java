package by.voloshchuk.entity.dto;

import java.util.Objects;

public class UserDto {

    private Long userId;

    private Long userDetailId;

    private String email;

    private String password;

    private String role;

    private String imagePath;

    private String firstName;

    private String lastName;

    private String company;

    private String position;

    private String experience;

    private String salary;

    private String primarySkill;

    private String skillsDescription;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
        UserDto that = (UserDto) o;
        return Objects.equals(email, that.email)
                && Objects.equals(password, that.password)
                && Objects.equals(role, that.role)
                && Objects.equals(imagePath, that.imagePath)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(company, that.company)
                && Objects.equals(position, that.position)
                && Objects.equals(experience, that.experience)
                && Objects.equals(salary, that.salary)
                && Objects.equals(primarySkill, that.primarySkill)
                && Objects.equals(skillsDescription, that.skillsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role, imagePath, firstName,
                lastName, company, position, experience, salary,
                primarySkill, skillsDescription);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getClass().getName());
        builder.append("email=");
        builder.append(email);
        builder.append(", password=");
        builder.append(password);
        builder.append(", role=");
        builder.append(role);
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
        return builder.toString();
    }

}
