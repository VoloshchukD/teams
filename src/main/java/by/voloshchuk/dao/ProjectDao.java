package by.voloshchuk.dao;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface ProjectDao {

    /**
     * Save project data to storage.
     *
     * @param projectDto - entity with required data for saving
     * @return boolean result of saving
     */
    boolean addProject(ProjectDto projectDto) throws DaoException;

    /**
     * Find stored user projects with status.
     *
     * @param userId - id of user with projects
     * @param state  - state of required projects
     * @return list of {@link Project}
     */
    List<Project> findProjectsByUserIdAndState(Long userId, String state) throws DaoException;

    /**
     * Project data update.
     *
     * @param project - data for project updating
     * @return {@link Project}
     */
    Project updateProject(Project project) throws DaoException;

    /**
     * Project status reset.
     *
     * @param projectId - project id for update
     * @param status    - status for update
     * @return updated status
     */
    String updateProjectStatus(Long projectId, String status) throws DaoException;

    /**
     * Project data deletion.
     *
     * @param id - project id to delete
     * @return boolean result of removing
     */
    boolean removeProject(Long id) throws DaoException;

}
