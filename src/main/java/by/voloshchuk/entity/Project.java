package by.voloshchuk.entity;

import java.sql.Date;
import java.util.List;

public class Project extends AbstractIdentifiedEntity {

    private String name;

    private String description;

    private Date startDate;

    private String state;

    private TechnicalTask technicalTask;

    private List<User> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TechnicalTask getTechnicalTask() {
        return technicalTask;
    }

    public void setTechnicalTask(TechnicalTask technicalTask) {
        this.technicalTask = technicalTask;
    }

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", state='" + state + '\'' +
                ", technicalTask=" + technicalTask +
                ", employees=" + employees +
                '}';
    }

}
