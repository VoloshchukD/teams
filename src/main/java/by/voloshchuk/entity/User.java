package by.voloshchuk.entity;

public class User extends AbstractIdentifiedEntity {

    private String email;

    private String password;

    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userDetail=" + userDetail +
                '}';
    }

}
