package seedu.mani.parser;
import seedu.mani.task.Deadline;
import seedu.mani.task.TaskList;
import seedu.mani.task.Todo;
import seedu.mani.task.Event;
import seedu.mani.ui.Ui;

import java.io.IOException;


/**
 * The Parser class is responsible for interpreting user commands.
 * It takes the raw string command, breaks it down and facilitates
 * the necessary output.
 */
public class Parser {

    private static final String EMPTY_TASK = "Error: There is no task provided, description is empty.";
    private static final String FILE_PATH = "./data/tasks.txt";

    /**
     * Parses through user's command and executes the operation as per input
     *
     * @param userCommand
     * @param ui
     * @param memory
     * @return
     */
    public static boolean parse(String userCommand, Ui ui, TaskList memory) throws IOException {
        if (userCommand == null || userCommand.trim().isEmpty()) {
            ui.errorMessage("No command entered.");
            return false;
        }

        String[] words = userCommand.trim().split(" ", 2);
        String firstWord = words[0];
        String remainingMessage = words.length > 1 ? words[1] : "";

        switch (firstWord) {
            case "bye":
                return true;

            case "list":
                ui.systemMessage(memory.toString());
                return false;

            case "mark":
                try {
                    String[] markParts = remainingMessage.split(" ");
                    int markInt = Integer.parseInt(markParts[0]);
                    ui.systemMessage(memory.markTask(markInt));
                } catch (NumberFormatException e) {
                    ui.errorMessage("Error: Please provide a valid task number.");
                }
                return false;

            case "unmark":
                try {
                    String[] unmarkParts = remainingMessage.split(" ");
                    int unmarkInt = Integer.parseInt(unmarkParts[0]);
                    ui.systemMessage(memory.unmarkTask(unmarkInt));
                } catch (NumberFormatException e) {
                    ui.errorMessage("Error: Please provide a valid task number.");
                }
                return false;

            case "todo":
                return handleTodo(remainingMessage, ui, memory);

            case "deadline":
                return handleDeadline(remainingMessage, ui, memory);

            case "event":
                return handleEvent(remainingMessage, ui, memory);

            case "delete":
                try {
                    String[] parts = remainingMessage.split(" ");
                    int lastInt = Integer.parseInt(parts[0]);
                    ui.systemMessage(memory.deleteTask(lastInt - 1, FILE_PATH));
                } catch (NumberFormatException e) {
                    ui.errorMessage("Error: Please provide a valid task number.");
                }
                return false;

            case "find":
                String keyword = remainingMessage.trim();
                if (keyword.isEmpty()) {
                    ui.systemMessage("Error: No keyword provided.");
                } else {
                    ui.systemMessage(memory.findTask(keyword));
                }
                return false;

            default:
                ui.errorMessage("There is no valid command provided.");
                return false;
        }
    }

    /**
     * Create a new Todo Task, and checks for errors in user input.
     *
     * @param remainingMessage
     * @param ui
     * @param memory
     * @return
     * @throws IOException
     */
    private static boolean handleTodo(String remainingMessage, Ui ui, TaskList memory) throws IOException {
        if (remainingMessage.trim().isEmpty()) {
            ui.systemMessage(EMPTY_TASK);
            return false;
        }
        remainingMessage = remainingMessage.trim();
        ui.systemMessage(memory.addTask(new Todo(remainingMessage), FILE_PATH));
        return false;
    }

    /**
     * Create a new Deadline Task, and checks for errors in user input.
     *
     * @param remainingMessage
     * @param ui
     * @param memory
     * @return
     * @throws IOException
     */
    private static boolean handleDeadline(String remainingMessage, Ui ui, TaskList memory) throws IOException {
        if (remainingMessage.trim().isEmpty()) {
            ui.systemMessage(EMPTY_TASK);
            return false;
        }

        int byIndex = remainingMessage.indexOf("/by");
        if (byIndex == -1) {
            ui.systemMessage("Error: Use /by to specify deadline");
            return false;
        }

        String taskDescription = remainingMessage.substring(0, byIndex).trim();
        String date = remainingMessage.substring(byIndex + 3).trim();

        if (taskDescription.isEmpty()) {
            ui.systemMessage(EMPTY_TASK);
            return false;
        }
        if (date.isEmpty()) {
            ui.systemMessage("Error: No deadline provided.");
            return false;
        }

        ui.systemMessage(memory.addTask(new Deadline(taskDescription, date), FILE_PATH));
        return false;
    }

    /**
     * Create a new Event Task, and checks for errors in user input.
     *
     * @param remainingMessage
     * @param ui
     * @param memory
     * @return
     * @throws IOException
     */
    private static boolean handleEvent(String remainingMessage, Ui ui, TaskList memory) throws IOException {
        if (remainingMessage.trim().isEmpty()) {
            ui.systemMessage(EMPTY_TASK);
            return false;
        }

        int fromIndex = remainingMessage.indexOf("/from");
        int toIndex = remainingMessage.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            ui.systemMessage("Error: Use /from and /to to specify event time");
            return false;
        }

        String taskDescription = remainingMessage.substring(0, fromIndex).trim();
        String from = remainingMessage.substring(fromIndex + 5, toIndex).trim();
        String to = remainingMessage.substring(toIndex + 3).trim();

        if (taskDescription.isEmpty()) {
            ui.systemMessage(EMPTY_TASK);
            return false;
        }
        if (from.isEmpty() || to.isEmpty()) {
            ui.systemMessage("Error: From or To is not provided.");
            return false;
        }

        ui.systemMessage(memory.addTask(new Event(taskDescription, from, to), FILE_PATH));
        return false;
    }
}
