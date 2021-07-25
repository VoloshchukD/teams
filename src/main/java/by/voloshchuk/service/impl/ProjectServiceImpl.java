package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public List<Project> findProjectsByUserIdAndState(Long useId, String state) throws ServiceException {
        List<Project> projects = null;
        ProjectDao projectDao = daoProvider.getProjectDao();
        try {
            projects = projectDao.findProjectsByUserIdAndState(useId, state);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return projects;
    }

}
