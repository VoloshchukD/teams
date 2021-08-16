package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface EmployeeRequirementDao {

    /**
     * Employee requirement data saving to data storage.
     *
     * @param requirement - entity with requirement data for save
     * @return boolean result of save
     */
    boolean addEmployeeRequirement(EmployeeRequirement requirement) throws DaoException;

    /**
     * Find all employee requirements represented on the technical task.
     *
     * @param technicalTaskId - id of technical task with requirements
     * @return list of {@link EmployeeRequirement}
     */
    List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws DaoException;

    /**
     * Find all employee requirements represented on the project
     *
     * @param projectId - id of project with requirements
     * @return list of {@link EmployeeRequirement}
     */
    List<EmployeeRequirement> findAllByProjectId(Long projectId) throws DaoException;

    /**
     * Data of requirement updating.
     *
     * @param requirement - data that needs to be updated
     * @return {@link EmployeeRequirement}
     */
    EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws DaoException;

    /**
     * Employee requirement data deletion.
     *
     * @param id - bill id to delete
     * @return boolean deletion result
     */
    boolean removeEmployeeRequirement(Long id) throws DaoException;

}
