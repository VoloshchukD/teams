package by.voloshchuk.servlet.command;

import by.voloshchuk.servlet.command.impl.*;
import by.voloshchuk.servlet.command.impl.transition.AboutCommand;
import by.voloshchuk.servlet.command.impl.transition.ProfileCommand;
import by.voloshchuk.servlet.command.impl.transition.ToAuthorizationCommand;
import by.voloshchuk.servlet.command.impl.transition.ToRegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.ERROR, new MainCommand());
        commands.put(CommandName.MAIN, new MainCommand());
        commands.put(CommandName.ABOUT, new AboutCommand());
        commands.put(CommandName.PROJECTS, new ProjectsCommand());
        commands.put(CommandName.LOCALE, new LocaleCommand());
        commands.put(CommandName.REGISTRATION, new ToRegistrationCommand());
        commands.put(CommandName.REGISTRATE, new RegistrationCommand());
        commands.put(CommandName.AUTHORIZATION, new ToAuthorizationCommand());
        commands.put(CommandName.AUTHORIZE, new AuthorizationCommand());
        commands.put(CommandName.PROFILE, new ProfileCommand());
        commands.put(CommandName.AVATAR, new AvatarCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.ERROR);
        }
        return command;
    }

}
