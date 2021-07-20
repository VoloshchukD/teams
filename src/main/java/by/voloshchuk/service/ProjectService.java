package by.voloshchuk.service;

import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface ProjectService {

    public List<Project> findProjectsByUserIdAndState(Long useId, String state) throws ServiceException;

}
