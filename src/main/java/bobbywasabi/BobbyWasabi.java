package bobbywasabi;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        this.storage = new Storage("./data/BobbyWasabiTasks.txt", "./data");
        try {
            this.storage.createDataStorage();
            this.taskList = new TaskList(storage.load());
        } catch (BobbyWasabiException e) {
            ui.generateErrorMsg(e.getMessage());
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Starts the BobbyWasabi main command loop.
     * Continuously reads and executes user commands until the BYE command is received.
     */
    public void run() {
        this.ui.greetUser();

        while (true) {
            // Get user input and command
            String userInput = ui.getNextInput();
            Command command = Parser.parseCommand(userInput);

            switch (command) {
            case BYE:

                ui.farewellUser();
                return;

            case LIST:

                ui.listMessage(this.taskList);
                continue;

            case MARK:
                try {

                    int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                    Task targetTask = this.taskList.get(indx - 1);
                    targetTask.setIsMarked(true);

                    ui.markTaskMessage(indx, targetTask);
                    storage.updateDataFileFromTasks(this.taskList);
                    continue;

                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }
            case UNMARK:
                try {

                    int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                    Task targetTask = this.taskList.get(indx - 1);
                    targetTask.setIsMarked(false);

                    ui.unmarkTaskMessage(indx, targetTask);
                    storage.updateDataFileFromTasks(this.taskList);
                    continue;

                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }
            case TODO:
                try {

                    String description = Parser.parseTodo(userInput);

                    Task todo = new ToDo(description, false);
                    this.taskList.add(todo);

                    ui.addTaskMessage(todo, this.taskList.size());
                    storage.fileWrite(todo.getData());
                    continue;

                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }
            case DEADLINE:
                try {
                    String[] details = Parser.parseDeadline(userInput);
                    String description = details[0];
                    String deadline = details[1];

                    LocalDateTime dateTime = Parser.parseDateString(deadline);

                    Task deadlineTask = new Deadline(description, false, dateTime);
                    this.taskList.add(deadlineTask);

                    ui.addTaskMessage(deadlineTask, this.taskList.size());
                    storage.fileWrite(deadlineTask.getData());
                    continue;

                } catch (BobbyWasabiException | DateTimeParseException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }

            case EVENT:
                try {
                    String[] details = Parser.parseEvent(userInput);
                    String description = details[0];
                    String start = details[1];
                    String end = details[2];

                    Task eventTask = new Event(description, false, start, end);
                    this.taskList.add(eventTask);

                    ui.addTaskMessage(eventTask, this.taskList.size());
                    storage.fileWrite(eventTask.getData());
                    continue;

                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }
            case DELETE:
                try {
                    int indx = Parser.parseCommandIndex(userInput, this.taskList.size());
                    Task targetTask = this.taskList.get(indx - 1);
                    this.taskList.remove(indx - 1);

                    ui.deleteMessage(targetTask, this.taskList.size());
                    storage.updateDataFileFromTasks(this.taskList);
                    continue;

                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                    continue;
                }
            case FIND:
                try {
                    String keyword = Parser.parseFindCommand(userInput);
                    String matchingTasks = this.taskList.findTasksThatMatchKeyword(keyword);
                    ui.findMessage(matchingTasks); //
                } catch (BobbyWasabiException e) {
                    ui.generateErrorMsg(e.getMessage());
                }
                continue;
            case OTHERS:
                ui.invalidMessage();
                continue;
            default:
                ui.invalidMessage();
            }


        }
    }


    /**
     * Main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new BobbyWasabi().run();
    }
}
