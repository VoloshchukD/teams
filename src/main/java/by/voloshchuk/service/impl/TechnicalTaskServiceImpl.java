package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;
import by.voloshchuk.util.DtoEntityConverter;

import java.text.ParseException;
import java.util.List;

public class TechnicalTaskServiceImpl implements TechnicalTaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addTechnicalTask(TechnicalTaskDto technicalTaskDto) throws ServiceException {
        boolean result = false;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        Validator<TechnicalTaskDto> taskValidator =
                ValidatorProvider.getInstance().getTechnicalTaskValidator();
        if (taskValidator.validateCreateData(technicalTaskDto)) {
            try {
                TechnicalTask technicalTask = DtoEntityConverter.buildTechnicalTask(technicalTaskDto);
                result = technicalTaskDao.addTechnicalTask(technicalTask);
            } catch (DaoException | ParseException e) {
                throw new ServiceException("Exception while add technical task ", e);
            }
        }
        return result;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByUserId(useId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find technical task ", e);
        }
        return technicalTasks;
    }

    @Override
    public TechnicalTask findTechnicalTaskById(Long technicalTaskId) throws ServiceException {
        TechnicalTask technicalTask = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTask = technicalTaskDao.findTechnicalTaskById(technicalTaskId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find technical task ", e);
        }
        return technicalTask;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByStatus(String status) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find technical task ", e);
        }
        return technicalTasks;
    }

    @Override
    public TechnicalTask updateTechnicalTask(TechnicalTask technicalTask) throws ServiceException {
        TechnicalTask updatedTechnicalTask = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            updatedTechnicalTask = technicalTaskDao.updateTechnicalTask(technicalTask);
        } catch (DaoException e) {
            throw new ServiceException("Exception while update technical task ", e);
        }
        return updatedTechnicalTask;
    }

    @Override
    public boolean removeTechnicalTask(Long id) throws ServiceException {
        boolean deleted = false;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            deleted = technicalTaskDao.removeTechnicalTask(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception while remove technical task ", e);
        }
        return deleted;
    }

}
