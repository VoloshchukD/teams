package by.voloshchuk.dao.pool;

import by.voloshchuk.exception.WebAppRuntimeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Pool of connections based on two blocking queues with custom connections.
 *
 * @author Daniil Voloshchuk
 */
public class CustomConnectionPool {

    private static CustomConnectionPool instance;

    private BlockingQueue<ProxyConnection> availableConnections;

    private BlockingQueue<ProxyConnection> givenAwayConnections;

    private static Lock lock = new ReentrantLock();

    private static AtomicBoolean create = new AtomicBoolean(false);

    private static final Logger logger = LogManager.getLogger();

    private CustomConnectionPool() {
        registerDriver();
        init();
    }

    /**
     * Method contains driver registration logics for database connection.
     */
    private void registerDriver() {
        try {
            Class.forName(ConnectionProperty.DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            logger.log(Level.ERROR, "Error while db driver registration " + e.getMessage());
        }
    }

    /**
     * Method that create connection instances for release.
     */
    private void init() {
        availableConnections = new LinkedBlockingQueue<>(ConnectionProperty.DEFAULT_POOL_SIZE);
        givenAwayConnections = new LinkedBlockingQueue<>(ConnectionProperty.DEFAULT_POOL_SIZE);

        for (int i = 0; i < ConnectionProperty.DEFAULT_POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(
                        ConnectionProperty.DATABASE_URL, ConnectionProperty.DATABASE_USERNAME,
                        ConnectionProperty.DATABASE_PASSWORD);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.offer(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Connection error " + e.getMessage());
            }
        }
        if (availableConnections.size() == 0) {
            logger.log(Level.FATAL, "Connection pool is created without connections");
            throw new WebAppRuntimeException("Connection pool is not created");
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new CustomConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    /**
     * Method that is used to get available connection instance.
     *
     * @return - instance of free connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Waiting interrupted " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Method which is used to return taken connection back to pool.
     *
     * @param connection - previously taken instance of connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            givenAwayConnections.remove(proxyConnection);
            availableConnections.offer(proxyConnection);
        }
    }

    /**
     * Method for closing used resources.
     */
    public void destroyPool() {
        for (int i = 0; i < ConnectionProperty.DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().realClose();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Waiting interrupted " + e.getMessage());
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQLException while closing " +
                        "connections in method destroyPool " + e.getMessage());
            }
        }
        deregisterDrivers();
    }

    /**
     * Method for removing drivers to end working with database.
     */
    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver currentDriver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(currentDriver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Deregister driver error " + e.getMessage());
            }
        }
    }

}
