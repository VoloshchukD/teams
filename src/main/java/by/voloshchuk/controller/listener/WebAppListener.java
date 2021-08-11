package by.voloshchuk.controller.listener;

import by.voloshchuk.dao.pool.CustomConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener for initializing application data on context creation.
 *
 * @author Daniil Voloshchuk
 */
public class WebAppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CustomConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        CustomConnectionPool.getInstance().destroyPool();
    }

}
