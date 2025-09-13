package bobbywasabi;

import java.time.LocalDateTime;

import bobbywasabi.client.Client;
import bobbywasabi.client.ClientList;
import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.parser.Parser;
import bobbywasabi.response.Response;
import bobbywasabi.storage.Storage;
import bobbywasabi.tasks.Deadline;
import bobbywasabi.tasks.Event;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;
import bobbywasabi.tasks.ToDo;

/**
 * Main class for the BobbyWasabi task manager application.
 * <p>
 * Handles parsing of user commands, management of task and client lists,
 * interaction with persistent storage, and communication with the UI layer via {@link Response}.
 */
public class BobbyWasabi {

    /**
     * Enum representing supported user commands.
     * If input does not match a valid command, it defaults to OTHERS.
     */
    public enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND,
        CLIENTS,
        ADDCLIENT,
        EDITCLIENT,
        DELETECLIENT,
        OTHERS;

        /**
         * Converts a user input string into the corresponding {@link Command}.
         *
         * @param input User input string representing a command.
         * @return The matching {@link Command} enum value, or {@link #OTHERS} if input is unrecognized.
         */
        public static Command toCommand(String input) {
            try {
                return Command.valueOf(input.toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                return Command.OTHERS;
            }
        }
    }

    private ClientList clientList;
    private TaskList taskList;
    private Storage storage;
    private Response botResponse;
    private String commandType;

    /**
     * Constructs a new BobbyWasabi instance.
     * <p>
     * Initializes the {@link Response}, {@link Storage}, {@link TaskList}, and {@link ClientList}.
     * If loading from storage fails, empty task and client lists are created.
     */
    public BobbyWasabi() {
        this.botResponse = new Response();
        this.storage = new Storage("./data", "./data/BobbyWasabiTasks.txt",
                "./data/BobbyWasabiClients.txt");

        try {
            this.storage.createDataStorage();
            this.taskList = storage.loadTaskList();
            this.clientList = storage.loadClientList();
        } catch (BobbyWasabiException e) {
            this.taskList = new TaskList();
            this.clientList = new ClientList();
        }
    }

    /**
     * Processes a MARK command, marking the specified task as completed.
     *
     * @param userInput User command input containing the task index.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processMarkCommand(String userInput) throws BobbyWasabiException {

        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

        assert indx > 0 && indx <= this.taskList.size()
                : "Index in MARK command is out of bounds!";

        Task targetTask = this.taskList.get(indx - 1);
        targetTask.setIsMarked(true);

        storage.updateDataFileFromTasks(this.taskList);

        return botResponse.markTaskMessage(indx, targetTask);

    }

    /**
     * Processes an UNMARK command, marking the specified task as not completed.
     *
     * @param userInput User command input containing the task index.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processUnmarkCommand(String userInput) throws BobbyWasabiException {

        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

        assert indx > 0 && indx <= this.taskList.size()
                : "Index in MARK command is out of bounds!";

        Task targetTask = this.taskList.get(indx - 1);
        targetTask.setIsMarked(false);

        storage.updateDataFileFromTasks(this.taskList);

        return botResponse.unmarkTaskMessage(indx, targetTask);

    }

    /**
     * Processes a TODO command, adding a new {@link ToDo} task.
     *
     * @param userInput User command input containing the task description.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processTodoCommand(String userInput) throws BobbyWasabiException {

        String description = Parser.parseTodo(userInput);
        Task todo = new ToDo(description, false);
        this.taskList.add(todo);

        storage.fileWrite(todo.getData(), Storage.StorageType.TASKLIST);

        return botResponse.addTaskMessage(todo, this.taskList.size());

    }

    /**
     * Processes a DEADLINE command, adding a new {@link Deadline} task with a specified due date.
     *
     * @param userInput User command input containing the task description and deadline.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processDeadlineCommand(String userInput) throws BobbyWasabiException {

        String[] details = Parser.parseDeadline(userInput);

        assert details.length == 2
                : "Details in DEADLINE command is insufficient!";

        String description = details[0];
        String deadline = details[1];
        LocalDateTime dateTime = Parser.parseDateString(deadline);

        Task deadlineTask = new Deadline(description, false, dateTime);
        this.taskList.add(deadlineTask);

        storage.fileWrite(deadlineTask.getData(), Storage.StorageType.TASKLIST);

        return botResponse.addTaskMessage(deadlineTask, this.taskList.size());

    }

    /**
     * Processes an EVENT command, adding a new {@link Event} task with a start and end time.
     *
     * @param userInput User command input containing the event description and timings.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processEventCommand(String userInput) throws BobbyWasabiException {

        String[] details = Parser.parseEvent(userInput);

        assert details.length >= 2
                : "Details in DEADLINE command is insufficient!";

        String description = details[0];
        LocalDateTime[] timings = Parser.parseEventDateString(details[1], details[2]);
        LocalDateTime start = timings[0];
        LocalDateTime end = timings[1];

        Task eventTask = new Event(description, false, start, end);
        this.taskList.add(eventTask);

        storage.fileWrite(eventTask.getData(), Storage.StorageType.TASKLIST);
        return botResponse.addTaskMessage(eventTask, this.taskList.size());

    }

    /**
     * Processes a DELETE command, removing a task from the task list.
     *
     * @param userInput User command input containing the task index.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processDeleteCommand(String userInput) throws BobbyWasabiException {

        int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

        assert indx > 0 && indx <= this.taskList.size()
                : "Index in DELETE command is out of bounds!";

        Task targetTask = this.taskList.get(indx - 1);
        this.taskList.remove(indx - 1);

        storage.updateDataFileFromTasks(this.taskList);

        return botResponse.deleteMessage(targetTask, this.taskList.size());

    }

    /**
     * Processes a FIND command, searching for tasks matching a keyword.
     *
     * @param userInput User command input containing the search keyword.
     * @return Search result message or error message via {@link Response}.
     */
    public String processFindCommand(String userInput) throws BobbyWasabiException {

        String keyword = Parser.parseFindCommand(userInput);
        String matchingTasks = this.taskList.findTasksThatMatchKeyword(keyword);

        return botResponse.findMessage(matchingTasks);

    }

    /**
     * Processes an EDITCLIENT command, updating a specified field of a client.
     *
     * @param userInput User command input containing the client index, field name, and new value.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processEditClient(String userInput) throws BobbyWasabiException {

        String[] details = Parser.parseEditClient(userInput, this.clientList.size());

        int index = Parser.getIntegerFromString(details[0]) - 1;
        String field = details[1];
        String newFieldContent = details[2];
        Client targetClient = this.clientList.get(index);

        this.clientList.updateClientField(index, field, newFieldContent);

        this.storage.updateDataFileFromClients(this.clientList);

        return botResponse.editClientMessage(targetClient);

    }

    /**
     * Processes a DELETECLIENT command, removing a client from the client list.
     *
     * @param userInput User command input containing the client index.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processDeleteClient(String userInput) throws BobbyWasabiException {

        int indx = Parser.parseCommandIndex(userInput, this.clientList.size());

        assert indx > 0 && indx <= this.clientList.size()
                : "Index in DELETE command is out of bounds!";

        Client targetClient = this.clientList.get(indx - 1);
        this.clientList.remove(indx - 1);

        storage.updateDataFileFromClients(this.clientList);

        return botResponse.deleteClientMessage(targetClient, this.clientList.size());

    }

    /**
     * Processes an ADDCLIENT command, adding a new {@link Client} to the client list.
     *
     * @param userInput User command input containing client details.
     * @return Confirmation message or error message via {@link Response}.
     */
    public String processAddClient(String userInput) throws BobbyWasabiException {

        Client client = Parser.parseAddClient(userInput);
        this.clientList.add(client);
        storage.fileWrite(client.getData(), Storage.StorageType.CLIENTLIST);
        return botResponse.addClientMessage(client, this.clientList.size());

    }

    /**
     * Processes the CLIENTS command, returning a formatted list of all clients.
     *
     * @return Client list message via {@link Response}.
     */
    public String processClientsCommand() {
        return botResponse.clientsMessage(this.clientList);
    }

    /**
     * Processes the BYE command, returning a farewell message.
     *
     * @return Farewell message via {@link Response}.
     */
    public String processByeCommand() {
        return this.botResponse.farewellUser();
    }

    /**
     * Processes the LIST command, returning a formatted list of all tasks.
     *
     * @return Task list message via {@link Response}.
     */
    public String processListCommand() {
        return botResponse.listMessage(this.taskList);
    }

    /**
     * Processes an unrecognized command, returning a generic invalid command message.
     *
     * @return Error message via {@link Response}.
     */
    public String processDefaultCommand() {
        return botResponse.invalidMessage();
    }

    /**
     * Parses and executes a user input command, routing it to the appropriate processor method.
     * <p>
     * Supports all commands defined in {@link Command}. If the input does not match a known command,
     * it falls back to {@link #processDefaultCommand()}.
     *
     * @param userInput Raw user input string.
     * @return Formatted response message corresponding to the executed command.
     */
    public String getResponse(String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);

            assert command != null
                    : "Command cannot be null!";

            this.commandType = command.name();

            switch (command) {
            case BYE:
                return this.processByeCommand();
            case LIST:
                return this.processListCommand();
            case MARK:
                return this.processMarkCommand(userInput);
            case UNMARK:
                return this.processUnmarkCommand(userInput);
            case TODO:
                return this.processTodoCommand(userInput);
            case DEADLINE:
                return this.processDeadlineCommand(userInput);
            case EVENT:
                return this.processEventCommand(userInput);
            case DELETE:
                return this.processDeleteCommand(userInput);
            case FIND:
                return this.processFindCommand(userInput);
            case CLIENTS:
                return this.processClientsCommand();
            case ADDCLIENT:
                return this.processAddClient(userInput);
            case DELETECLIENT:
                return this.processDeleteClient(userInput);
            case EDITCLIENT:
                return this.processEditClient(userInput);
            default:
                return this.processDefaultCommand();
            }
        } catch (BobbyWasabiException e) {
            this.commandType = "OTHERS";
            return botResponse.generateErrorMsg(e.getMessage());
        }
    }

    /**
     * Returns the type of the last processed command.
     *
     * @return Command type as a string.
     */
    public String getCommandType() {
        return this.commandType;
    }

}
