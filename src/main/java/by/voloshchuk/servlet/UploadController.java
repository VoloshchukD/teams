package by.voloshchuk.servlet;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.servlet.command.CommandAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UploadController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final String FILE_NAME_REGEX = "(.+)([.].+)";

    private static final int FILE_EXTENSION_GROUP_NUMBER = 2;

    private static final String UPLOAD_DIRECTORY = "uploads";

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        Long userDetailId = (Long) req.getSession().getAttribute(CommandAttribute.USER_DETAIL_ID);
        String fileName = null;
        for (Part part : req.getParts()) {
            if (part.getSubmittedFileName() != null) {
                String fileExtension = parseFileExtension(part.getSubmittedFileName());
                fileName = userDetailId + fileExtension;
                part.write(uploadFilePath + File.separator + fileName);
            }
        }
        fileName = File.separator + UPLOAD_DIRECTORY + File.separator + fileName;

        UserDetailService userDetailService = serviceProvider.getUserDetailService();

        String resultUserImage = null;
        try {
            resultUserImage = userDetailService.updateUserDetailImage(userDetailId, fileName);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        req.getSession().setAttribute(CommandAttribute.USER_IMAGE, resultUserImage);
    }

    private String parseFileExtension(String fileName) {
        Pattern pattern = Pattern.compile(FILE_NAME_REGEX);
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        return matcher.group(FILE_EXTENSION_GROUP_NUMBER);
    }

}
