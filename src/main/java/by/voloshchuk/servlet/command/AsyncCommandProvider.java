package by.voloshchuk.servlet.command;

import by.voloshchuk.servlet.command.impl.async.*;

import java.util.HashMap;
import java.util.Map;

public class AsyncCommandProvider {

    private static final AsyncCommandProvider instance = new AsyncCommandProvider();

    private Map<AsyncCommandName, AsyncCommand> asyncCommands = new HashMap<>();

    private static final String COMMAND_SEPARATOR = "-";

    private static final String ENUM_COMMAND_SEPARATOR = "_";

    private AsyncCommandProvider() {
        asyncCommands.put(AsyncCommandName.LOAD_AVATAR, new LoadAvatarCommand());
        asyncCommands.put(AsyncCommandName.EDIT, new EditCommand());
        asyncCommands.put(AsyncCommandName.UPDATE_USER_DETAIL, new UpdateUserDetailCommand());
        asyncCommands.put(AsyncCommandName.EMPLOYEE_REQUIREMENTS, new EmployeeRequirementsCommand());
        asyncCommands.put(AsyncCommandName.SEEK_EMPLOYEES, new SeekEmployeesCommand());
        asyncCommands.put(AsyncCommandName.ADD_EMPLOYEE, new AddEmployeeCommand());
        asyncCommands.put(AsyncCommandName.ADD_REQUIREMENT, new AddRequirementCommand());
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
        String formattedName = commandName.toUpperCase().replaceAll(COMMAND_SEPARATOR, ENUM_COMMAND_SEPARATOR);
        AsyncCommandName asyncCommandName = AsyncCommandName.valueOf(formattedName);
        return asyncCommandName;
    }

}
