package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.CommandAttribute;
import by.voloshchuk.servlet.command.RequestParameter;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String jsonString = request.getParameter("jsonString");
        JSONObject jsonObject = new JSONObject(jsonString);
        UserDetail userDetail = new UserDetail();
        Long userDetailId = (Long) request.getSession().getAttribute(CommandAttribute.USER_DETAIL_ID);
        userDetail.setId(userDetailId);
        userDetail.setFirstName(jsonObject.getString(RequestParameter.FIRST_NAME));
        userDetail.setLastName(jsonObject.getString(RequestParameter.LAST_NAME));
        userDetail.setCompany(jsonObject.getString(RequestParameter.COMPANY));
        userDetail.setPosition(jsonObject.getString(RequestParameter.POSITION));
        userDetail.setExperience(jsonObject.getInt(RequestParameter.EXPERIENCE));
        userDetail.setSalary(jsonObject.getInt(RequestParameter.SALARY));
        userDetail.setPrimarySkill(jsonObject.getString(RequestParameter.PRIMARY_SKILL));
        userDetail.setSkillsDescription(jsonObject.getString(RequestParameter.SKILLS_DESCRIPTION));
        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetailService.updateUserDetail(userDetail);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }

    }

}
