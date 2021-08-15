package by.voloshchuk.controller.command;

import by.voloshchuk.controller.command.impl.async.*;
import by.voloshchuk.util.StringFormatter;

import java.util.HashMap;
import java.util.Map;

/**
 * Provider for async commands.
 *
 * @author Daniil Voloshchuk
 */
public class AsyncCommandProvider {

    private static final AsyncCommandProvider instance = new AsyncCommandProvider();

    private Map<AsyncCommandName, AsyncCommand> asyncCommands = new HashMap<>();

    private AsyncCommandProvider() {
        asyncCommands.put(AsyncCommandName.LOAD_AVATAR, new LoadAvatarCommand());
        asyncCommands.put(AsyncCommandName.UPDATE_USER_DETAIL, new UpdateUserDetailCommand());
        asyncCommands.put(AsyncCommandName.EMPLOYEE_REQUIREMENTS, new LoadRequirementsCommand());
        asyncCommands.put(AsyncCommandName.SEEK_EMPLOYEES, new SeekEmployeesByRequirementCommand());
        asyncCommands.put(AsyncCommandName.ADD_EMPLOYEE, new AddEmployeeCommand());
        asyncCommands.put(AsyncCommandName.DELETE_EMPLOYEE, new DeleteEmployeeCommand());
        asyncCommands.put(AsyncCommandName.ADD_REQUIREMENT, new AddRequirementCommand());
        asyncCommands.put(AsyncCommandName.LOAD_PROJECT_USERS, new LoadProjectUsersCommand());
        asyncCommands.put(AsyncCommandName.ADD_TASK, new AddTaskCommand());
        asyncCommands.put(AsyncCommandName.UPDATE_TASK_HOURS, new UpdateTaskHoursCommand());
        asyncCommands.put(AsyncCommandName.UPDATE_TASK_STATUS, new UpdateTaskStatusCommand());
        asyncCommands.put(AsyncCommandName.LOAD_TASKS_INFORMATION, new LoadTasksInformationCommand());
        asyncCommands.put(AsyncCommandName.LOAD_PROJECT_REQUIREMENTS, new LoadProjectRequirementsCommand());
        asyncCommands.put(AsyncCommandName.ACCEPT_PAYMENT, new AcceptPaymentCommand());
        asyncCommands.put(AsyncCommandName.UPDATE_USER_STATUS, new UpdateUserStatusCommand());
        asyncCommands.put(AsyncCommandName.DELETE_REQUIREMENT, new DeleteRequirementCommand());
        asyncCommands.put(AsyncCommandName.SEARCH_EMPLOYEES, new SeekEmployeesBySkillCommand());
    }

    public static AsyncCommandProvider getInstance() {
        return instance;
    }

    public AsyncCommand getCommand(String commandName) {
        AsyncCommandName name = parseCommand(commandName);
        AsyncCommand asyncCommand;
        if (name != null) {
            asyncCommand = asyncCommands.get(name);
        } else {
            asyncCommand = asyncCommands.get(AsyncCommandName.ERROR);
        }
        return asyncCommand;
    }

    public AsyncCommandName parseCommand(String commandName) {
        String formattedName = StringFormatter.parseCommand(commandName);
        AsyncCommandName asyncCommandName = AsyncCommandName.valueOf(formattedName);
        return asyncCommandName;
    }

}
