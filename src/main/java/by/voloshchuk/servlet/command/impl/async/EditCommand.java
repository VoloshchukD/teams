package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.service.UserService;
import by.voloshchuk.service.impl.UserDetailServiceImpl;
import by.voloshchuk.service.impl.UserServiceImpl;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.AsyncCommandParameter;
import by.voloshchuk.servlet.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = (Long) request.getSession().getAttribute(AsyncCommandParameter.USER_ID);
        UserDetail userDetail = null;
        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetail = userDetailService.findUserDetailByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_FIRST_NAME,  userDetail.getFirstName());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_LAST_NAME,  userDetail.getLastName());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_COMPANY,  userDetail.getCompany());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_POSITION,  userDetail.getPosition());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_EXPERIENCE,  userDetail.getExperience());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_SALARY,  userDetail.getSalary());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_PRIMARY_SKILL,  userDetail.getPrimarySkill());
        jsonObject.put(AsyncCommandParameter.USER_DETAIL_SKILLS_DESCRIPTION,  userDetail.getSkillsDescription());
        response.getWriter().write(jsonObject.toString());
    }

}
