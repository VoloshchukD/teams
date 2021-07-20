package by.voloshchuk.servlet.listener;

import by.voloshchuk.dao.pool.CustomConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
