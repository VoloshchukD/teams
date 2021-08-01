package by.voloshchuk.service;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface ProjectService {

    boolean addProject(Project project) throws ServiceException;

    List<Project> findProjectsByUserIdAndState(Long useId, String state) throws ServiceException;

}
