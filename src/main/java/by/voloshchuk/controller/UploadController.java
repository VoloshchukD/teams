package by.voloshchuk.controller;

import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
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

/**
 * Controller for user image uploading.
 *
 * @author Daniil Voloshchuk
 */
public class UploadController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private static final String FILE_NAME_REGEX = "(.+)([.].+)";

    private static final int FILE_EXTENSION_GROUP_NUMBER = 2;

    private static final String UPLOAD_DIRECTORY = "uploads";

    private static final String WEB_APPLICATION_ROOT = "";

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String applicationPath = req.getServletContext().getRealPath(WEB_APPLICATION_ROOT);
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
        try {
            String resultUserImage = userDetailService.updateUserDetailImage(userDetailId, fileName);
            req.getSession().setAttribute(CommandAttribute.USER_IMAGE, resultUserImage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    /**
     * Method that defines uploaded file extension.
     *
     * @param fileName - uploaded file name with extension
     * @return file extension
     */
    private String parseFileExtension(String fileName) {
        Pattern pattern = Pattern.compile(FILE_NAME_REGEX);
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();
        return matcher.group(FILE_EXTENSION_GROUP_NUMBER);
    }

}
