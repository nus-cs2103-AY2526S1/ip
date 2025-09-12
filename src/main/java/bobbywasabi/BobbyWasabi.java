package bobbywasabi;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bobbywasabi.client.ClientList;
import bobbywasabi.client.Client;
import bobbywasabi.exceptions.BobbyWasabiException;
import bobbywasabi.parser.Parser;
import bobbywasabi.storage.Storage;
import bobbywasabi.tasks.Deadline;
import bobbywasabi.tasks.Event;
import bobbywasabi.tasks.Task;
import bobbywasabi.tasks.TaskList;
import bobbywasabi.tasks.ToDo;
import bobbywasabi.ui.UI;

/**
 * Main class for the BobbyWasabi task manager application.
 * This class handles command parsing, task list management,
 * and interaction between the user interface, storage, and tasks.
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
         * Converts a user input string to a corresponding Command.
         *
         * @param input User command input.
         * @return Corresponding Command enum value.
         */
        public static Command toCommand(String input) {
            try {
                return Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Command.OTHERS;
            }
        }
    }

    private ClientList clientList;
    private TaskList taskList;
    private Storage storage;
    private UI ui;

    /**
     * Constructs a new BobbyWasabi instance.
     * Initializes the UI, Storage, and TaskList. If loading from storage fails,
     * an empty task list is initialized and an error is displayed.
     */
    public BobbyWasabi() {
        this.ui = new UI();
        this.storage = new Storage("./data", "./data/BobbyWasabiTasks.txt",
                "./data/BobbyWasabiClients.txt");

        try {
            this.storage.createDataStorage();
            this.taskList = storage.loadTaskList();
            this.clientList = storage.loadClientList();
            String s = "hi";
        } catch (BobbyWasabiException e) {
            ui.generateErrorMsg(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public String processMarkCommand(String userInput) {
        try {

            int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

            assert indx > 0 && indx <= this.taskList.size()
                    : "Index in MARK command is out of bounds!";

            Task targetTask = this.taskList.get(indx - 1);
            targetTask.setIsMarked(true);

            storage.updateDataFileFromTasks(this.taskList);

            return ui.markTaskMessage(indx, targetTask);

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processUnmarkCommand(String userInput) {
        try {

            int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

            assert indx > 0 && indx <= this.taskList.size()
                    : "Index in MARK command is out of bounds!";

            Task targetTask = this.taskList.get(indx - 1);
            targetTask.setIsMarked(false);

            storage.updateDataFileFromTasks(this.taskList);

            return ui.unmarkTaskMessage(indx, targetTask);

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processTodoCommand(String userInput) {
        try {
            String description = Parser.parseTodo(userInput);
            Task todo = new ToDo(description, false);
            this.taskList.add(todo);

            storage.fileWrite(todo.getData(), Storage.StorageType.TASKLIST);

            return ui.addTaskMessage(todo, this.taskList.size());

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processDeadlineCommand(String userInput) {
        try {
            String[] details = Parser.parseDeadline(userInput);

            assert details.length == 2
                    : "Details in DEADLINE command is insufficient!";

            String description = details[0];
            String deadline = details[1];
            LocalDateTime dateTime = Parser.parseDateString(deadline);

            Task deadlineTask = new Deadline(description, false, dateTime);
            this.taskList.add(deadlineTask);

            storage.fileWrite(deadlineTask.getData(), Storage.StorageType.TASKLIST);

            return ui.addTaskMessage(deadlineTask, this.taskList.size());

        } catch (BobbyWasabiException | DateTimeParseException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processEventCommand(String userInput) {
        try {
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
            return ui.addTaskMessage(eventTask, this.taskList.size());

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processDeleteCommand(String userInput) {
        try {

            int indx = Parser.parseCommandIndex(userInput, this.taskList.size());

            assert indx > 0 && indx < this.taskList.size()
                    : "Index in DELETE command is out of bounds!";

            Task targetTask = this.taskList.get(indx - 1);
            this.taskList.remove(indx - 1);

            storage.updateDataFileFromTasks(this.taskList);

            return ui.deleteMessage(targetTask, this.taskList.size());

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processFindCommand(String userInput) {
        try {

            String keyword = Parser.parseFindCommand(userInput);
            String matchingTasks = this.taskList.findTasksThatMatchKeyword(keyword);

            return ui.findMessage(matchingTasks);

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }


    //edit clients -> 1 name/contact/age/occupation/currentpolicies what you want to replace
    public String processEditClient(String userInput) {
        try {
            String[] details = Parser.parseEditClient(userInput, this.clientList.size());

            int index = Parser.getIntegerFromString(details[0]) - 1;
            String field = details[1];
            String newFieldContent = details[2];
            Client targetClient = this.clientList.get(index);

            this.clientList.updateClientField(index, field, newFieldContent);

            this.storage.updateDataFileFromClients(this.clientList);

            return ui.editClientMessage(targetClient);
        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }


    public String processDeleteClient(String userInput) {
        try {

            int indx = Parser.parseCommandIndex(userInput, this.clientList.size());

            assert indx > 0 && indx <= this.clientList.size()
                    : "Index in DELETE command is out of bounds!";

            Client targetClient = this.clientList.get(indx - 1);
            this.clientList.remove(indx - 1);

            storage.updateDataFileFromClients(this.clientList);

            return ui.deleteClientMessage(targetClient, this.clientList.size());

        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processAddClient(String userInput) {
        try {
            Client client = Parser.parseAddClient(userInput);
            this.clientList.add(client);
            storage.fileWrite(client.getData(), Storage.StorageType.CLIENTLIST);
            return ui.addClientMessage(client, this.clientList.size());
        } catch (BobbyWasabiException e) {
            return ui.generateErrorMsg(e.getMessage());
        }
    }

    public String processClientsCommand() {
        return ui.clientsMessage(this.clientList);
    }

    public String processByeCommand() {
        return this.ui.farewellUser();
    }

    public String processListCommand() {
        return ui.listMessage(this.taskList);
    }

    public String processDefaultCommand() {
        return ui.invalidMessage();
    }

    /**
     * Starts the BobbyWasabi main command loop.
     * Continuously reads and executes user commands until the BYE command is received.
     */
    public String getResponse(String userInput) {

        Command command = Parser.parseCommand(userInput);

        assert command != null
                : "Command cannot be null!";

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
            return  this.processEditClient(userInput);
        default:
            return this.processDefaultCommand();
        }
    }

}
