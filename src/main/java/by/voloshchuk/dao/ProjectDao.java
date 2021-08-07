package by.voloshchuk.dao;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface ProjectDao {

    boolean addProject(ProjectDto projectDto) throws DaoException;

    Project findProjectById(Long id) throws DaoException;

    List<Project> findProjectsByUserIdAndState(Long userId, String state) throws DaoException;

    Project updateProject(Project project) throws DaoException;

    boolean removeProject(Long id) throws DaoException;

}
