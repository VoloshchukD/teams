package by.voloshchuk.service.impl;

import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.dao.ProjectDao;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;

import java.util.Date;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    public boolean addProject(ProjectDto projectDto) throws ServiceException {
        boolean result = false;
        ProjectDao projectDao = daoProvider.getProjectDao();
        Date date = new Date(System.currentTimeMillis());
        Project project = projectDto.getProject();
        Validator<Project> projectValidator = ValidatorProvider.getInstance().getProjectValidator();
        if (projectValidator.validateCreateData(project)) {
            try {
                project.setStartDate(new java.sql.Date(date.getTime()));
                result = projectDao.addProject(projectDto);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
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
        Validator<Project> projectValidator = ValidatorProvider.getInstance().getProjectValidator();
        if (projectValidator.validateUpdateData(project)) {
            ProjectDao projectDao = daoProvider.getProjectDao();
            try {
                updatedProject = projectDao.updateProject(project);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return updatedProject;
    }

    @Override
    public String updateProjectStatus(Long projectId, String status) throws ServiceException {
        String resultStatus = null;
        ProjectDao projectDao = daoProvider.getProjectDao();
        try {
            resultStatus = projectDao.updateProjectStatus(projectId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultStatus;
    }

    @Override
    public boolean removeProject(Long projectId, Long technicalTaskId) throws ServiceException {
        boolean deleted = false;
        ProjectDao projectDao = daoProvider.getProjectDao();
        try {
            deleted = projectDao.removeProject(projectId, technicalTaskId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deleted;
    }

}
