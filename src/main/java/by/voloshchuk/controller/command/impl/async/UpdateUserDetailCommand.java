package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.controller.command.CommandAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserDetailCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String jsonString = request.getParameter(AsyncCommandParameter.JSON_STRING);
        JSONObject jsonObject = new JSONObject(jsonString);
        UserDetail userDetail = createUserDetail(request, jsonObject);

        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetailService.updateUserDetail(userDetail);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    private UserDetail createUserDetail(HttpServletRequest request, JSONObject jsonObject) {
        UserDetail userDetail = new UserDetail();
        Long userDetailId = (Long) request.getSession().getAttribute(CommandAttribute.USER_DETAIL_ID);
        userDetail.setId(userDetailId);
        userDetail.setFirstName(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_FIRST_NAME));
        userDetail.setLastName(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_LAST_NAME));
        userDetail.setCompany(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_COMPANY));
        userDetail.setPosition(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_POSITION));
        userDetail.setExperience(jsonObject.getInt(AsyncCommandParameter.USER_DETAIL_EXPERIENCE));
        userDetail.setSalary(jsonObject.getInt(AsyncCommandParameter.USER_DETAIL_SALARY));
        userDetail.setPrimarySkill(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_PRIMARY_SKILL));
        userDetail.setSkillsDescription(jsonObject.getString(AsyncCommandParameter.USER_DETAIL_SKILLS_DESCRIPTION));
        return userDetail;
    }

}
