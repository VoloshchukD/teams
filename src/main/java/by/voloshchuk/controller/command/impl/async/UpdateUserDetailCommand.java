package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for updating user data.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateUserDetailCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        UserDto userDto = buildUserDto(request);

        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetailService.updateUserDetail(userDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    private UserDto buildUserDto(HttpServletRequest request) {
        String jsonString = request.getParameter(AsyncCommandParameter.JSON_STRING);
        JSONObject jsonObject = new JSONObject(jsonString);
        UserDto userDto = new UserDto();
        Long userDetailId = (Long) request.getSession().getAttribute(CommandAttribute.USER_DETAIL_ID);
        userDto.setUserDetailId(userDetailId);
        userDto.setFirstName(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_FIRST_NAME));
        userDto.setLastName(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_LAST_NAME));
        userDto.setCompany(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_COMPANY));
        userDto.setPosition(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_POSITION));
        userDto.setExperience(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_EXPERIENCE));
        userDto.setSalary(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_SALARY));
        userDto.setPrimarySkill(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_PRIMARY_SKILL));
        userDto.setSkillsDescription(
                jsonObject.getString(AsyncCommandParameter.USER_DETAIL_SKILLS_DESCRIPTION));
        return userDto;
    }

}
