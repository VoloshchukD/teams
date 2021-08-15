package by.voloshchuk.controller.command;

import by.voloshchuk.controller.command.impl.*;
import by.voloshchuk.controller.command.impl.transition.*;
import by.voloshchuk.util.StringFormatter;

import java.util.HashMap;
import java.util.Map;

/**
 * Provider for sync commands.
 *
 * @author Daniil Voloshchuk
 */
public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();

    private Map<CommandName, Command> commands = new HashMap<>();

    private CommandProvider() {
        commands.put(CommandName.ERROR, new ToMainPageCommand());
        commands.put(CommandName.MAIN, new ToMainPageCommand());
        commands.put(CommandName.ABOUT, new ToAboutCommand());
        commands.put(CommandName.PROJECTS, new ToUserProjectsCommand());
        commands.put(CommandName.TO_CREATE_PROJECT, new ToCreateProjectCommand());
        commands.put(CommandName.CREATE_PROJECT, new CreateProjectCommand());
        commands.put(CommandName.CREATE_TECHNICAL_TASK, new CreateTechnicalTaskCommand());
        commands.put(CommandName.CREATE_BILL, new CreateBillCommand());
        commands.put(CommandName.TO_CREATE_REQUIREMENT, new ToCreateRequirementCommand());
        commands.put(CommandName.TO_SEEK_EMPLOYEES, new ToSeekEmployeesCommand());
        commands.put(CommandName.TECHNICAL_TASKS, new ToUserTechnicalTasksCommand());
        commands.put(CommandName.TO_CREATE_TECHNICAL_TASK, new ToCreateTechnicalTask());
        commands.put(CommandName.TO_TASKS, new ToTasksCommand());
        commands.put(CommandName.TO_PROJECT_BILLS, new ToProjectBillsCommand());
        commands.put(CommandName.TO_BILL_CREATION, new ToBillCreationCommand());
        commands.put(CommandName.UPDATE_TECHNICAL_TASK, new UpdateTechnicalTaskCommand());
        commands.put(CommandName.DELETE_TECHNICAL_TASK, new DeleteTechnicalTaskCommand());
        commands.put(CommandName.UPDATE_PROJECT, new UpdateProjectCommand());
        commands.put(CommandName.DELETE_PROJECT, new DeleteProjectCommand());
        commands.put(CommandName.TO_PAYMENT_FORM, new ToPaymentFormCommand());
        commands.put(CommandName.FIND_TECHNICAL_TASKS, new SeekTechnicalTasksCommand());
        commands.put(CommandName.BILLS, new ToUserBillsCommand());
        commands.put(CommandName.MAKE_PAYMENT, new MakePaymentCommand());
        commands.put(CommandName.LOCALE, new ChangeLocaleCommand());
        commands.put(CommandName.REGISTRATION, new ToRegistrationCommand());
        commands.put(CommandName.REGISTRATE, new RegistrationCommand());
        commands.put(CommandName.AUTHORIZATION, new ToAuthorizationCommand());
        commands.put(CommandName.AUTHORIZE, new AuthorizationCommand());
        commands.put(CommandName.PROFILE, new ToProfileCommand());
        commands.put(CommandName.LOGOUT, new LogOutCommand());
        commands.put(CommandName.DELETE_ACCOUNT, new DeleteAccountCommand());
        commands.put(CommandName.UPDATE_BILL, new UpdateBillCommand());
        commands.put(CommandName.DELETE_BILL, new DeleteBillCommand());
        commands.put(CommandName.UPDATE_TASK, new UpdateTaskCommand());
        commands.put(CommandName.DELETE_TASK, new DeleteTaskCommand());
        commands.put(CommandName.UPDATE_REQUIREMENT, new UpdateRequirementCommand());
        commands.put(CommandName.FINISH_PROJECT, new FinishProjectCommand());
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
        String formattedName = StringFormatter.parseCommand(commandName);
        CommandName resultName = CommandName.valueOf(formattedName);
        return resultName;
    }

}
