package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.CommandAttribute;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoadAvatarCommand implements AsyncCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommandAttribute.USER_IMAGE,  request.getSession().getAttribute(CommandAttribute.USER_IMAGE));
        response.getWriter().write(jsonObject.toString());
    }

}
