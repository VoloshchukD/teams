package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.servlet.command.AsyncCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AvatarCommand implements AsyncCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("AVATAR");
        String result = "{" + '"' + "avatar" + '"' + ":" + '"' + request.getSession().getAttribute("userUploadedImg") + '"' + "}";

//        File avatar = new File("C:\\Users\\pagubnoe\\IdeaProjects\\teams\\src\\main\\webapp"+request.getSession().getAttribute("userImg"));
//        while (!avatar.exists()) {
//
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println("not exist");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        response.getWriter().write(result);
        System.out.println("f");

    }

}
