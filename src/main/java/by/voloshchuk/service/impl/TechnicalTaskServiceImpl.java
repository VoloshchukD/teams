package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.TechnicalTaskDao;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TechnicalTaskServiceImpl implements TechnicalTaskService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addTechnicalTask(TechnicalTaskDto technicalTaskDto) throws ServiceException {
        boolean result = false;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        Validator<TechnicalTaskDto> taskValidator = ValidatorProvider.getInstance().getTechnicalTaskValidator();
        if (taskValidator.validateCreateData(technicalTaskDto)) {
            try {
                TechnicalTask technicalTask = createTechnicalTask(technicalTaskDto);
                result = technicalTaskDao.addTechnicalTask(technicalTask);
            } catch (DaoException | ParseException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    private TechnicalTask createTechnicalTask(TechnicalTaskDto technicalTaskDto) throws ParseException {
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setName(technicalTaskDto.getName());
        technicalTask.setOverview(technicalTaskDto.getOverview());
        Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(technicalTaskDto.getDeadline());
        technicalTask.setDeadline(deadline);
        technicalTask.setStatus(TechnicalTask.TechnicalTaskStatus.WAIT_PROJECT);
        User user = new User();
        user.setId(technicalTaskDto.getCustomerId());
        technicalTask.setCustomer(user);
        return technicalTask;
    }

    @Override
    public List<TechnicalTask> findTechnicalTasksByUserId(Long useId) throws ServiceException {
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskDao technicalTaskDao = daoProvider.getTechnicalTaskDao();
        try {
            technicalTasks = technicalTaskDao.findTechnicalTasksByUserId(useId);
        } catch (DaoException e) {
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
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
            throw new ServiceException(e);
        }
        return deleted;
    }

}
