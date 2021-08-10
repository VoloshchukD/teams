package by.voloshchuk.util;

import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.entity.dto.UserDto;

public class DtoBuilder {

    public static User buildUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(User.UserRole.valueOf(userDto.getRole()));
        user.setUserDetail(buildUserDetail(userDto));
        return user;
    }

    public static UserDetail buildUserDetail(UserDto userDto) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(userDto.getFirstName());
        userDetail.setLastName(userDto.getLastName());
        userDetail.setCompany(userDto.getCompany());
        userDetail.setPosition(userDto.getPosition());
        userDetail.setExperience(Integer.parseInt(userDto.getExperience()));
        userDetail.setSalary(Integer.parseInt(userDto.getSalary()));
        userDetail.setPrimarySkill(userDto.getPrimarySkill());
        userDetail.setSkillsDescription(userDto.getSkillsDescription());
        userDetail.setStatus(UserDetail.Status.NOT_BUSY);
        return userDetail;
    }

}
