package by.voloshchuk.servlet.command;

import by.voloshchuk.servlet.command.impl.*;
import by.voloshchuk.servlet.command.impl.async.AcceptPaymentCommand;
import by.voloshchuk.servlet.command.impl.transition.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private static final String COMMAND_SEPARATOR = "-";

    private static final String ENUM_COMMAND_SEPARATOR = "_";

    private CommandProvider() {
        commands.put(CommandName.ERROR, new MainCommand());
        commands.put(CommandName.MAIN, new MainCommand());
        commands.put(CommandName.ABOUT, new AboutCommand());
        commands.put(CommandName.PROJECTS, new ProjectsCommand());
        commands.put(CommandName.TO_CREATE_PROJECT, new ToCreateProjectCommand());
        commands.put(CommandName.CREATE_PROJECT, new CreateProjectCommand());
        commands.put(CommandName.CREATE_TECHNICAL_TASK, new CreateTechnicalTaskCommand());
        commands.put(CommandName.CREATE_BILL, new CreateBillCommand());
        commands.put(CommandName.TO_CREATE_REQUIREMENT, new ToCreateRequirementCommand());
        commands.put(CommandName.TO_SEEK_EMPLOYEES, new ToSeekEmployeesCommand());
        commands.put(CommandName.TECHNICAL_TASKS, new TechnicalTasksCommand());
        commands.put(CommandName.TO_CREATE_TECHNICAL_TASK, new ToCreateTechnicalTask());
        commands.put(CommandName.TO_TASKS, new ToTasksCommand());
        commands.put(CommandName.TO_PROJECT_BILLS, new ToProjectBillsCommand());
        commands.put(CommandName.TO_BILL_CREATION, new ToBillCreationCommand());
        commands.put(CommandName.UPDATE_TECHNICAL_TASK, new UpdateTechnicalTaskCommand());
        commands.put(CommandName.DELETE_TECHNICAL_TASK, new DeleteTechnicalTaskCommand());
        commands.put(CommandName.UPDATE_PROJECT, new UpdateProjectCommand());
        commands.put(CommandName.DELETE_PROJECT, new DeleteProjectCommand());
        commands.put(CommandName.TO_PAYMENT_FORM, new ToPaymentFormCommand());
        commands.put(CommandName.FIND_TECHNICAL_TASKS, new FindTechnicalTaskCommand());
        commands.put(CommandName.BILLS, new BillsCommand());
        commands.put(CommandName.MAKE_PAYMENT, new MakePaymentCommand());
        commands.put(CommandName.LOCALE, new LocaleCommand());
        commands.put(CommandName.REGISTRATION, new ToRegistrationCommand());
        commands.put(CommandName.REGISTRATE, new RegistrationCommand());
        commands.put(CommandName.AUTHORIZATION, new ToAuthorizationCommand());
        commands.put(CommandName.AUTHORIZE, new AuthorizationCommand());
        commands.put(CommandName.PROFILE, new ProfileCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.DELETE_ACCOUNT, new DeleteAccountCommand());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = parseCommand(commandName);
        Command command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.ERROR);
        }
        return command;
    }

    public CommandName parseCommand(String commandName) {
        String formattedName = commandName.toUpperCase().replaceAll(COMMAND_SEPARATOR, ENUM_COMMAND_SEPARATOR);
        CommandName resultName = CommandName.valueOf(formattedName);
        return resultName;
    }

}
