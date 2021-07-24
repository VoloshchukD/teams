package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.service.UserService;
import by.voloshchuk.service.impl.UserDetailServiceImpl;
import by.voloshchuk.service.impl.UserServiceImpl;
import by.voloshchuk.servlet.command.AsyncCommand;
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

    private UserDetailService userDetailService = new UserDetailServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = (Long) request.getSession().getAttribute(RequestParameter.USER_ID);
        UserDetail userDetail = null;
        try {
            userDetail = userDetailService.findUserDetailByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname",  userDetail.getFirstName());
        jsonObject.put("lastname",  userDetail.getLastName());
        jsonObject.put("company",  userDetail.getCompany());
        jsonObject.put("position",  userDetail.getPosition());
        jsonObject.put("experience",  userDetail.getExperience());
        jsonObject.put("salary",  userDetail.getSalary());
        jsonObject.put("primary",  userDetail.getPrimarySkill());
        jsonObject.put("skills",  userDetail.getSkillsDescription());
        response.getWriter().write(jsonObject.toString());
    }

}
