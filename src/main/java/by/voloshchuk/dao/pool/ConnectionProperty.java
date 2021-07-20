package by.voloshchuk.dao.pool;

import by.voloshchuk.exception.WebAppRuntimeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class ConnectionProperty {

    private static final Logger logger = LogManager.getLogger();

    private static final String PROPERTIES_FILE_NAME = "database.properties";

    private static final String DRIVER_CLASS_NAME_PROPERTY_NAME = "driver.class.name";

    private static final String DB_URL_PROPERTY_NAME = "db.url";

    private static final String DB_USERNAME_PROPERTY_NAME = "db.username";

    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    private static final String DEFAULT_POOL_SIZE_PROPERTY_NAME = "default.pool.size";

    static {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = ConnectionProperty.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            logger.log(Level.FATAL, "File with database properties is not found " + e.getMessage());
            throw new WebAppRuntimeException("Properties file is not found");
        } catch (IOException e) {
            logger.log(Level.FATAL, "Properties in file are not found " + e.getMessage());
            throw new WebAppRuntimeException("Database properties are not found");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.log(Level.ERROR, "Error while closing properties input stream " + e.getMessage());
                }
            }
        }
        DRIVER_CLASS_NAME = properties.getProperty(DRIVER_CLASS_NAME_PROPERTY_NAME);
        DATABASE_URL = properties.getProperty(DB_URL_PROPERTY_NAME);
        DATABASE_USERNAME = properties.getProperty(DB_USERNAME_PROPERTY_NAME);
        DATABASE_PASSWORD = properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
        DEFAULT_POOL_SIZE = Integer.parseInt(properties.getProperty(DEFAULT_POOL_SIZE_PROPERTY_NAME));
    }

    public static final String DRIVER_CLASS_NAME;

    public static final String DATABASE_URL;

    public static final String DATABASE_USERNAME;

    public static final String DATABASE_PASSWORD;

    public static final int DEFAULT_POOL_SIZE;

}
