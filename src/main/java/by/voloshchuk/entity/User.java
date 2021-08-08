package by.voloshchuk.entity;

import java.util.Objects;

public class User extends AbstractIdentifiedEntity {

    private String email;

    private String password;

    private UserRole role;

    public enum UserRole {
        MANAGER,
        DEVELOPER,
        CUSTOMER,
        GUEST,
        ADMIN
    }

    private UserDetail userDetail;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && Objects.equals(userDetail, user.userDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, role, userDetail);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userDetail=" + userDetail +
                '}';
    }

}
