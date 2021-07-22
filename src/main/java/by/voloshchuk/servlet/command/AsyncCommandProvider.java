package by.voloshchuk.servlet.command;

import by.voloshchuk.servlet.command.impl.async.AvatarCommand;

import java.util.HashMap;
import java.util.Map;

public class AsyncCommandProvider {

    private static final AsyncCommandProvider instance = new AsyncCommandProvider();

    private Map<AsyncCommandName, AsyncCommand> asyncCommands = new HashMap<>();

    private AsyncCommandProvider() {
        asyncCommands.put(AsyncCommandName.AVATAR, new AvatarCommand());
    }

    public static AsyncCommandProvider getInstance() {
        return instance;
    }

    public AsyncCommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        AsyncCommand asyncCommand;
        if (name != null) {
            asyncCommand = asyncCommands.get(name);
        } else {
            asyncCommand = asyncCommands.get(AsyncCommandName.ERROR);
        }
        return asyncCommand;
    }

}
