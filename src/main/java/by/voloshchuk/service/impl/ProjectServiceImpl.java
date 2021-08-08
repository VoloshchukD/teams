package by.voloshchuk.service.impl;

import by.voloshchuk.dao.*;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Date;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    public boolean addProject(ProjectDto projectDto) throws ServiceException {
        boolean result = false;
        ProjectDao projectDao = daoProvider.getProjectDao();
        Date date = new Date(System.currentTimeMillis());
        Project project = projectDto.getProject();
        project.setStartDate(new java.sql.Date(date.getTime()));
        try {
            result = projectDao.addProject(projectDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

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

    @Override
    public Project updateProject(Project project) throws ServiceException {
        Project updatedProject = null;
        ProjectDao projectDao = daoProvider.getProjectDao();
        try {
            updatedProject = projectDao.updateProject(project);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return updatedProject;
    }

    @Override
    public boolean removeProject(Long id) throws ServiceException {
        boolean deleted = false;
        ProjectDao projectDao = daoProvider.getProjectDao();
        try {
            deleted = projectDao.removeProject(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deleted;
    }

}
