package by.voloshchuk.controller.command;

import by.voloshchuk.controller.command.impl.CreateProjectCommand;
import by.voloshchuk.controller.command.impl.CreateTechnicalTaskCommand;
import by.voloshchuk.controller.command.impl.CreateBillCommand;
import by.voloshchuk.controller.command.impl.UpdateTechnicalTaskCommand;
import by.voloshchuk.controller.command.impl.DeleteTechnicalTaskCommand;
import by.voloshchuk.controller.command.impl.UpdateProjectCommand;
import by.voloshchuk.controller.command.impl.DeleteProjectCommand;
import by.voloshchuk.controller.command.impl.SeekTechnicalTasksCommand;
import by.voloshchuk.controller.command.impl.MakePaymentCommand;
import by.voloshchuk.controller.command.impl.ChangeLocaleCommand;
import by.voloshchuk.controller.command.impl.RegistrationCommand;
import by.voloshchuk.controller.command.impl.AuthorizationCommand;
import by.voloshchuk.controller.command.impl.LogOutCommand;
import by.voloshchuk.controller.command.impl.DeleteAccountCommand;
import by.voloshchuk.controller.command.impl.UpdateBillCommand;
import by.voloshchuk.controller.command.impl.DeleteBillCommand;
import by.voloshchuk.controller.command.impl.UpdateTaskCommand;
import by.voloshchuk.controller.command.impl.UpdateRequirementCommand;
import by.voloshchuk.controller.command.impl.FinishProjectCommand;
import by.voloshchuk.controller.command.impl.transition.ToMainPageCommand;
import by.voloshchuk.controller.command.impl.transition.ToAboutCommand;
import by.voloshchuk.controller.command.impl.transition.ToUserProjectsCommand;
import by.voloshchuk.controller.command.impl.transition.ToCreateProjectCommand;
import by.voloshchuk.controller.command.impl.transition.ToCreateRequirementCommand;
import by.voloshchuk.controller.command.impl.transition.ToSeekEmployeesCommand;
import by.voloshchuk.controller.command.impl.transition.ToUserTechnicalTasksCommand;
import by.voloshchuk.controller.command.impl.transition.ToCreateTechnicalTask;
import by.voloshchuk.controller.command.impl.transition.ToTasksCommand;
import by.voloshchuk.controller.command.impl.transition.ToProjectBillsCommand;
import by.voloshchuk.controller.command.impl.transition.ToBillCreationCommand;
import by.voloshchuk.controller.command.impl.transition.ToPaymentFormCommand;
import by.voloshchuk.controller.command.impl.transition.ToUserBillsCommand;
import by.voloshchuk.controller.command.impl.transition.ToRegistrationCommand;
import by.voloshchuk.controller.command.impl.transition.ToAuthorizationCommand;
import by.voloshchuk.controller.command.impl.transition.ToProfileCommand;
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
        String formattedName = StringFormatter.parseEnumStringValue(commandName);
        CommandName resultName = CommandName.valueOf(formattedName);
        return resultName;
    }

}
