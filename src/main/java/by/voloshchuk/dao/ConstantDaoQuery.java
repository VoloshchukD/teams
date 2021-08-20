package by.voloshchuk.dao;

public final class ConstantDaoQuery {

    public static final String ADD_BILL_QUERY = "INSERT INTO bills " +
            "(status, information, amount_due, project_id) " +
            "VALUES (?, ?, ?, ?);";

    public static final String FIND_BILL_BY_ID_AND_USER_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN user_project_maps " +
            "ON bills.project_id = user_project_maps.project_id " +
            "WHERE bills.bill_id = ? AND user_project_maps.user_id = ?";

    public static final String FIND_BILLS_BY_PROJECT_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN projects " +
            "ON projects.project_id = bills.project_id " +
            "WHERE projects.project_id = ?";

    public static final String FIND_BILLS_BY_USER_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN projects " +
            "ON projects.project_id = bills.project_id INNER JOIN technical_tasks " +
            "ON projects.technical_task_id = technical_tasks.technical_task_id " +
            "WHERE technical_tasks.customer_id = ?";

    public static final String UPDATE_BILL_QUERY = "UPDATE bills " +
            "SET information = ?, amount_due = ? " +
            "WHERE bill_id = ?;";

    public static final String UPDATE_BILL_STATUS_QUERY = "UPDATE bills " +
            "SET status = ? WHERE bill_id = ?;";

    public static final String DELETE_BILL_QUERY = "DELETE FROM bills WHERE bill_id = ?;";

    public static final String ADD_PROJECT_QUERY = "INSERT INTO projects " +
            "(project_name, description, start_date, state, technical_task_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String FIND_PROJECT_BY_ID_QUERY = "SELECT * FROM projects " +
            "WHERE project_id = ?";

    public static final String FIND_PROJECTS_BY_USER_ID_AND_STATE_QUERY = "SELECT * " +
            "FROM projects INNER JOIN user_project_maps " +
            "ON projects.project_id = user_project_maps.project_id " +
            "WHERE user_project_maps.user_id = ? AND projects.state = ?";

    public static final String UPDATE_PROJECT_QUERY = "UPDATE projects " +
            "SET project_name = ?, description = ? " +
            "WHERE project_id = ?";

    public static final String UPDATE_PROJECT_STATE_QUERY = "UPDATE projects SET state = ? " +
            "WHERE project_id = ?";

    public static final String DELETE_PROJECT_BILLS_QUERY = "DELETE FROM bills " +
            "WHERE bills.project_id = ?";

    public static final String DELETE_PROJECT_TASKS_QUERY = "DELETE FROM tasks " +
            "WHERE tasks.project_id = ?";

    public static final String DELETE_PROJECT_TECHNICAL_TASKS_QUERY = "DELETE " +
            "FROM technical_tasks " +
            "WHERE technical_tasks.technical_task_id = ?";

    public static final String DELETE_PROJECT_QUERY = "DELETE FROM projects " +
            "WHERE project_id = ?";

    public static final String DELETE_USERS_FROM_PROJECT_QUERY = "DELETE FROM user_project_maps " +
            "WHERE project_id = ?";

    public static final String UPDATE_TECHNICAL_TASK_STATUS_QUERY = "UPDATE technical_tasks " +
            "SET status = ? WHERE technical_task_id = ?";

    public static final String ADD_TASK_QUERY = "INSERT INTO tasks (name, " +
            "details, planned_time, status, project_id, developer_id) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String FIND_TASK_BY_ID_QUERY = "SELECT * FROM tasks WHERE task_id = ?";

    public static final String FIND_TASKS_BY_PROJECT_ID_AND_USER_ID_QUERY = "SELECT * FROM tasks " +
            "INNER JOIN user_project_maps " +
            "ON tasks.project_id = user_project_maps.project_id " +
            "INNER JOIN users " +
            "ON users.user_id = tasks.developer_id " +
            "INNER JOIN user_details " +
            "ON user_details.user_detail_id = users.user_detail_id " +
            "WHERE tasks.project_id = ? " +
            "AND user_project_maps.user_id = ?";

    public static final String FIND_TASKS_BY_PROJECT_ID_QUERY_AND_STATUS = "SELECT * FROM tasks " +
            "INNER JOIN users ON tasks.developer_id = users.user_id " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id " +
            "WHERE tasks.project_id = ? AND tasks.status = ?";

    public static final String UPDATE_TASK_QUERY = "UPDATE tasks SET name = ?, details = ?," +
            " planned_time = ?, developer_id = ? WHERE task_id = ?";

    public static final String UPDATE_TASK_STATUS_QUERY = "UPDATE tasks SET status = ? " +
            "WHERE task_id = ?";

    public static final String UPDATE_TASK_HOURS_QUERY = "UPDATE tasks SET tracked_time = ? " +
            "WHERE task_id = ?";

    public static final String DELETE_TASK_QUERY = "DELETE FROM tasks WHERE task_id = ?";

    public static final String ADD_TECHNICAL_TASK_QUERY = "INSERT INTO technical_tasks " +
            "(name, overview, deadline, status, customer_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String FIND_TECHNICAL_TASK_BY_ID_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    public static final String FIND_TECHNICAL_TASKS_BY_CUSTOMER_ID_QUERY = "SELECT * " +
            "FROM technical_tasks " +
            "WHERE technical_tasks.customer_id = ?";

    public static final String FIND_TECHNICAL_TASKS_BY_STATUS_QUERY = "SELECT * FROM technical_tasks " +
            "WHERE technical_tasks.status = ?";

    public static final String UPDATE_TECHNICAL_TASK_QUERY = "UPDATE technical_tasks SET name = ?, " +
            "overview = ? " +
            "WHERE technical_task_id = ?";

    public static final String DELETE_TECHNICAL_TASK_QUERY = "DELETE FROM technical_tasks " +
            "WHERE technical_task_id = ?";

    public static final String DELETE_PROJECT_REQUIREMENTS_QUERY = "DELETE FROM employee_requirements " +
            "WHERE employee_requirements.technical_task_id = ?";

    public static final String ADD_USER_QUERY = "INSERT INTO users (email, " +
            "password, role, user_detail_id) VALUES (?, ?, ?, ?)";

    public static final String ADD_USER_DETAIL_QUERY = "INSERT INTO user_details (first_name, " +
            "last_name, company, position, experience, " +
            "salary, primary_skill, skills_description, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String ADD_USER_TO_PROJECT_QUERY = "INSERT INTO user_project_maps " +
            "(project_id, user_id) VALUES (?, ?);";

    public static final String DELETE_USER_FROM_PROJECT_QUERY = "DELETE FROM user_project_maps " +
            "WHERE project_id = ? AND user_id = ?;";

    public static final String FIND_BASIC_DATA_QUERY = "SELECT " +
            "TIMESTAMPDIFF(year, MIN(projects.start_date), CURDATE()) " +
            "AS years, ROUND(AVG(TIMESTAMPDIFF(month, projects.start_date, technical_tasks.deadline))) " +
            "AS productivity, COUNT(DISTINCT(users.user_id)) AS customers, COUNT(projects.project_id) " +
            "AS projects FROM users INNER JOIN technical_tasks " +
            "ON users.user_id = technical_tasks.customer_id INNER JOIN projects " +
            "ON technical_tasks.technical_task_id = projects.technical_task_id " +
            "WHERE users.role = 'CUSTOMER' AND projects.state = 'FINISHED'";

    public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE user_id = ?";

    public static final String FIND_USERS_BY_PROJECT_ID = "SELECT * FROM users " +
            "INNER JOIN user_project_maps " +
            "ON users.user_id = user_project_maps.user_id " +
            "INNER JOIN user_details ON users.user_id = user_details.user_detail_id " +
            "WHERE user_project_maps.project_id = ? AND users.role = IF (? = 'ALL', role, ?) " +
            "AND user_details.status != 'DELETED' ";

    public static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM users " +
            "INNER JOIN user_details ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE email = ?";

    public static final String FIND_USER_BY_REQUIREMENT_QUERY = "SELECT * FROM users " +
            "LEFT JOIN user_project_maps " +
            "ON users.user_id = user_project_maps.user_id " +
            "INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE (user_project_maps.project_id IS NULL OR user_project_maps.project_id != ?) " +
            "AND users.role = 'DEVELOPER' " +
            "AND user_details.experience >= ? AND user_details.salary <= ? " +
            "AND user_details.primary_skill = ? " +
            "AND user_details.status = 'NOT_BUSY'";

    public static final String FIND_USER_BY_PRIMARY_SKILL_QUERY = "SELECT * FROM users " +
            "LEFT JOIN user_project_maps " +
            "ON users.user_id = user_project_maps.user_id " +
            "INNER JOIN user_details " +
            "ON users.user_detail_id = user_details.user_detail_id " +
            "WHERE (user_project_maps.project_id IS NULL OR user_project_maps.project_id != ?) " +
            "AND user_details.primary_skill LIKE ? " +
            "AND user_details.status = 'NOT_BUSY' " +
            "GROUP BY users.user_id";

    public static final String PERCENT = "%";

    public static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, " +
            "password = ? WHERE user_id = ?";

    public static final String DELETE_USER_QUERY = "DELETE FROM users WHERE user_id = ?";

    public static final String DELETE_USER_DETAIL_QUERY = "DELETE FROM user_details " +
            "WHERE user_detail_id = ?";

    public static final String FIND_USER_DETAIL_BY_USER_ID_QUERY = "SELECT * FROM user_details " +
            "INNER JOIN users ON user_details.user_detail_id = users.user_detail_id " +
            "WHERE users.user_id = ?";

    public static final String UPDATE_USER_DETAIL_QUERY = "UPDATE user_details SET first_name = ?, " +
            "last_name = ?, company = ?, position = ?, experience = ?, salary = ?, primary_skill = ?, " +
            "skills_description = ?, status = ? WHERE user_detail_id = ?";

    public static final String UPDATE_USER_DETAIL_STATUS_QUERY = "UPDATE user_details " +
            "SET status = ? WHERE user_detail_id = ?";

    public static final String UPDATE_USER_DETAIL_IMAGE_QUERY = "UPDATE user_details " +
            "SET user_image_path = ? " +
            "WHERE user_detail_id = ?";

    public static final String ADD_EMPLOYEE_REQUIREMENT_QUERY = "INSERT INTO employee_requirements " +
            "(experience, salary, qualification, primary_skill, " +
            "comment, technical_task_id) VALUES (?, ?, ?, ?, ?, ?)";

    public static final String FIND_ALL_BY_TECHNICAL_TASK_ID_QUERY = "SELECT * FROM employee_requirements " +
            "WHERE employee_requirements.technical_task_id = ?";

    public static final String FIND_ALL_BY_PROJECT_ID_QUERY = "SELECT * FROM employee_requirements " +
            "INNER JOIN technical_tasks " +
            "ON employee_requirements.technical_task_id = technical_tasks.technical_task_id " +
            "INNER JOIN projects " +
            "ON projects.technical_task_id = technical_tasks.technical_task_id " +
            "WHERE projects.project_id = ?";

    public static final String UPDATE_EMPLOYEE_REQUIREMENT_QUERY = "UPDATE employee_requirements " +
            "SET experience = ?, salary = ?, qualification = ?, primary_skill = ?, comment = ? " +
            "WHERE employee_requirement_id = ?";

    public static final String DELETE_EMPLOYEE_REQUIREMENT_QUERY = "DELETE FROM employee_requirements " +
            "WHERE employee_requirement_id = ?";

    private ConstantDaoQuery() {
    }

}
