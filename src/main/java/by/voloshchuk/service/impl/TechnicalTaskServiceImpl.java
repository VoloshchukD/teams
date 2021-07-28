package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TechnicalTaskService;

import java.util.List;

public class TechnicalTaskServiceImpl implements TechnicalTaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByCustomerId(useId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return technicalTasks;
    }
}
