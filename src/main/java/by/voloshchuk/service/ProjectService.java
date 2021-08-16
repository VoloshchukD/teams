package by.voloshchuk.service;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface ProjectService {

    /**
     * Project adding logics.
     *
     * @param projectDto - entity with required data for adding
     * @return boolean result of adding
     */
    boolean addProject(ProjectDto projectDto) throws ServiceException;

    /**
     * Project finding logics.
     *
     * @param userId - id of user with projects
     * @param state  - state of required projects
     * @return list of {@link Project}
     */
    List<Project> findProjectsByUserIdAndState(Long userId, String state) throws ServiceException;

    /**
     * Project data updating logics.
     *
     * @param project - data for project updating
     * @return {@link Project}
     */
    Project updateProject(Project project) throws ServiceException;

    /**
     * Project status updating logics.
     *
     * @param projectId - project id for update
     * @param status    - status for update
     * @return updated status
     */
    String updateProjectStatus(Long projectId, String status) throws ServiceException;

    /**
     * Project removing logics.
     *
     * @param projectId       - project id to delete
     * @param technicalTaskId - related technical task id to delete
     * @return boolean result of removing
     */
    boolean removeProject(Long projectId, Long technicalTaskId) throws ServiceException;

}
