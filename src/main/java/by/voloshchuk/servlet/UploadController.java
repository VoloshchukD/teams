package by.voloshchuk.servlet;

import by.voloshchuk.servlet.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig(location = "/", fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadController extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        System.out.println("2");
        File fileSaveDir = new File(uploadFilePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs();
//        }

        String urlPath = null;
        for (Part part : req.getParts()) {
            if (part.getSubmittedFileName() != null) {
                part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                System.out.println(UPLOAD_DIR + File.separator + part.getSubmittedFileName());
                urlPath = part.getSubmittedFileName();
//                resp.getWriter().print(part.getSubmittedFileName() + " upload successfull");
            }
        }
        System.out.println("upl");
        urlPath = "/uploads/" + urlPath;
        req.getSession().setAttribute("userUploadedImg", urlPath);
        System.out.println(req.getSession().getAttribute("userUploadedImg"));
        String referer = req.getHeader(RequestParameter.REFER_HEADER);
//        resp.sendRedirect(referer);
        System.out.println("urlPath");
    }

}
