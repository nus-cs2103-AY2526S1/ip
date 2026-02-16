package chatbot.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import chatbot.client.ClientList;
import chatbot.command.AddClientCommand;
import chatbot.command.AddTaskCommand;
import chatbot.command.ByeCommand;
import chatbot.command.CommandExecutor;
import chatbot.command.DeleteClientCommand;
import chatbot.command.DeleteTaskCommand;
import chatbot.command.FindTasksCommand;
import chatbot.command.ListClientsCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkTaskCommand;
import chatbot.command.UnmarkTaskCommand;
import chatbot.command.UpdateClientCommand;
import chatbot.command.type.ClientCommand;
import chatbot.command.type.TaskCommand;
import chatbot.exception.BotException;
import chatbot.exception.InvalidCommandException;
import chatbot.storage.ClientStorage;
import chatbot.storage.TaskStorage;
import chatbot.task.TaskList;

/**
 * B33pbop is a GUI task management bot.
 * It supports commands to add tasks, delete tasks, list tasks, mark/unmark task completion and exit.
 */
public class B33pbop {
    private final Map<TaskCommand, CommandExecutor> taskCommandMap = new HashMap<>();
    private final Map<ClientCommand, CommandExecutor> clientCommandMap = new HashMap<>();
    private final UI ui;
    private final TaskList myTasks; // List of tasks managed by the bot
    private final ClientList myClients;

    /**
     * Constructor for B33pbop, initializes ui, task list and storage.
     */
    public B33pbop() {
        this.ui = new UI();
        this.myTasks = new TaskList();
        this.myClients = new ClientList();

        TaskStorage tempTaskStorage;
        ClientStorage tempClientStorage;
        try {
            tempTaskStorage = new TaskStorage();
            tempClientStorage = new ClientStorage();
            myTasks.loadTasks(tempTaskStorage.getStorageFile());
            myClients.loadClients(tempClientStorage.getStorageFile());
        } catch (IOException e) {
            tempTaskStorage = null;
            tempClientStorage = null;
        }
        TaskStorage taskStorage = tempTaskStorage;
        ClientStorage clientStorage = tempClientStorage;
        registerTaskCommands(taskStorage);
        registerClientCommands(clientStorage);
    }

    private void registerTaskCommands(TaskStorage taskStorage) {
        assert ui != null && myTasks != null : "UI and TaskList must be initialized";

        taskCommandMap.put(TaskCommand.BYE, new ByeCommand(ui));
        taskCommandMap.put(TaskCommand.LIST, new ListCommand(myTasks, ui));
        taskCommandMap.put(TaskCommand.TODO, new AddTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.DEADLINE, new AddTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.EVENT, new AddTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.DELETE, new DeleteTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.MARK, new MarkTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.UNMARK, new UnmarkTaskCommand(myTasks, ui, taskStorage));
        taskCommandMap.put(TaskCommand.FIND, new FindTasksCommand(myTasks, ui));
    }

    private void registerClientCommands(ClientStorage clientStorage) {
        assert ui != null && myClients != null : "UI and TaskList must be initialized";

        clientCommandMap.put(ClientCommand.ADD, new AddClientCommand(myClients, ui, clientStorage));
        clientCommandMap.put(ClientCommand.UPDATE, new UpdateClientCommand(myClients, ui, clientStorage));
        clientCommandMap.put(ClientCommand.DELETE, new DeleteClientCommand(myClients, ui, clientStorage));
        clientCommandMap.put(ClientCommand.LIST, new ListClientsCommand(myClients, ui));
    }

    /**
     * Returns a String of the bot greeting.
     *
     * @return String with a greet message.
     */
    public String getGreeting() {
        return ui.greetResponse();
    }

    public UI getUi() {
        return this.ui;
    }

    /**
     * Return a String representation of the bot response to user commands.
     *
     * @param input User input into the chatbot; must not be null or empty.
     * @return String response based on user input.
     */
    public String getResponse(String input) {
        assert input != null && !input.isEmpty() : "Input must not be null or empty";

        String[] inputParts = input.split(" ", 2);
        String cmdStr = inputParts[0].trim();
        String upperCmdStr = cmdStr.toUpperCase();

        if (upperCmdStr.equals("CLIENT")) {
            return getResponseFromClientCommand(input, inputParts);
        }

        try {
            TaskCommand command = parseTaskCommand(cmdStr);
            // Use the commandMap to get the executor
            CommandExecutor executor = taskCommandMap.get(command);
            if (executor == null) {
                return "What even is '" + input + "'?\n";
            }
            return executor.execute(input);

        } catch (BotException e) {
            return ui.runErrorMessage(e.getMessage());
        }
    }

    private String getResponseFromClientCommand(String input, String[] inputParts) {
        try {
            String userInput = inputParts[1].trim();
            String[] clientCommandParts = userInput.split(" ", 2);

            String clientCommand = clientCommandParts[0].strip();
            String clientCommandDetails = clientCommandParts.length > 1
                    ? clientCommandParts[1].strip()
                    : "";

            ClientCommand command = parseClientCommand(clientCommand);

            CommandExecutor executor = clientCommandMap.get(command);
            if (executor == null) {
                return "What even is '" + input + "'?\n";
            }
            return executor.execute(clientCommandDetails);

        } catch (BotException e) {
            return ui.runErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns a string command into the corresponding Command enum.
     *
     * @param cmdStr The string representation of the command; must not be null or empty.
     * @return The corresponding enum value of the cmdStr.
     * @throws InvalidCommandException If the string does not match any valid command.
     */
    private static TaskCommand parseTaskCommand(String cmdStr) throws InvalidCommandException {
        assert cmdStr != null && !cmdStr.isEmpty() : "Command string cannot be null or empty";

        try {
            return TaskCommand.valueOf(cmdStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("What even is '" + cmdStr + "'?\n");
        }
    }

    private static ClientCommand parseClientCommand(String cmdStr) throws InvalidCommandException {
        assert cmdStr != null && !cmdStr.isEmpty() : "Command string cannot be null or empty";

        try {
            return ClientCommand.valueOf(cmdStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("What even is '" + cmdStr + "'?\n");
        }
    }
}
