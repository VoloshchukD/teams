package by.voloshchuk.service;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface ProjectService {

    boolean addProject(ProjectDto projectDto) throws ServiceException;

    List<Project> findProjectsByUserIdAndState(Long useId, String state) throws ServiceException;

    Project updateProject(Project project) throws ServiceException;

    boolean removeProject(Long id) throws ServiceException;

}
