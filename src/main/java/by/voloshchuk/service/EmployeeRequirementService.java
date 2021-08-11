package by.voloshchuk.service;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface EmployeeRequirementService {

    /**
     * Requirement adding logics.
     *
     * @param requirement - entity with requirement data for adding
     * @return boolean result of adding
     */
    boolean addEmployeeRequirement(EmployeeRequirement requirement) throws ServiceException;

    /**
     * Technical task employee requirements finding logics.
     *
     * @param technicalTaskId - id of technical task with requirements
     * @return list of {@link EmployeeRequirement}
     */
    List<EmployeeRequirement> findAllByTechnicalTaskId(Long technicalTaskId) throws ServiceException;

    /**
     * Project employee requirements finding logics.
     *
     * @param projectId - id of project with requirements
     * @return list of {@link EmployeeRequirement}
     */
    List<EmployeeRequirement> findAllByProjectId(Long projectId) throws ServiceException;

    /**
     * Employee requirement updating logics.
     *
     * @param requirement - data for requirement updating
     * @return {@link EmployeeRequirement}
     */
    EmployeeRequirement updateEmployeeRequirement(EmployeeRequirement requirement) throws ServiceException;

    /**
     * Employee requirement removing logics.
     *
     * @param id - bill id to delete
     * @return boolean removing result
     */
    boolean removeEmployeeRequirement(Long id) throws ServiceException;

}
