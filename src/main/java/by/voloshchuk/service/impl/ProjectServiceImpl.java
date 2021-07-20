package by.voloshchuk.service.impl;

import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.dao.impl.ProjectDaoImpl;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao = new ProjectDaoImpl();

    @Override
    public List<Project> findProjectsByUserIdAndState(Long useId, String state) throws ServiceException {
        List<Project> projects = null;
        try {
            projects = projectDao.findProjectsByUserIdAndState(useId, state);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return projects;
    }

}
