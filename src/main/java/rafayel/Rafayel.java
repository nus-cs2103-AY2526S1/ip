
package rafayel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import rafayel.command.Parser;
import rafayel.storage.Storage;
import rafayel.task.Deadline;
import rafayel.task.Event;
import rafayel.task.Task;
import rafayel.task.TaskList;
import rafayel.task.Todo;
import rafayel.ui.Ui;

/**
 * Chatbot named Rafayel that manages a task list.
 * Functions include to add, delete, mark, unmark, find and list tasks.
 * Supports different task types: Todo, Deadline, and Event.
 * Saves task data to local file storage.
 */
public class Rafayel {

    /* Storage object that saves the task to local file storage. */
    private final Storage storage;
    /* TaskList stores the list of tasks */
    private TaskList tasks;
    /* Manages the ui of Rafayel */
    private final Ui ui;

    /**
     * Constructs a new Rafayel chatbot instance with the specified file path for data storage.
     *
     * @param filePath path to the file where task data will be stored.
     * @throws RafayelException if there is an error initialising the storage.
     */
    public Rafayel(String filePath) throws RafayelException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RafayelException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles marking or unmarking a task as done/not done.
     *
     * @param input user input containing the mark/unmark command.
     * @param tasks TaskList with all the tasks.
     * @param markTask true to mark the task as done, false otherwise.
     * @throws RafayelException if the input format or task number is invalid.
     */
    private static String handleMarkCommand(String input, TaskList tasks, boolean markTask) throws RafayelException {
        int minLen = markTask ? 5 : 7;
        if (input.length() <= minLen) {
            throw new RafayelException("Please state what task to be marked/unmarked.");
        }
        String[] temp = input.split(" ");
        int taskNumber = Integer.parseInt(temp[1]);

        if (taskNumber <= 0 || taskNumber > tasks.getSize()) {
            throw new RafayelException("Invalid task number.");
        }
        taskNumber--;
        if (markTask) {
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString());
            return "Nice! I've marked this task as done:\n  " + tasks.get(taskNumber).toString();
        } else {
            tasks.get(taskNumber).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber).toString());
            return "OK, I've marked this task as not done yet:\n  " + tasks.get(taskNumber).toString();
        }

    }

    /**
     * Prints the confirmation message when a new task is added to the list
     *
     * @param newTask the task that was added.
     * @param counter the current number of tasks in the ArrayList.
     */
    private static String printNewTaskString(Task newTask, int counter) {
        return String.format(
                "Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                newTask.toString(), counter);
    }

    /**
     * Handles the creation and addition of a new Todo task.
     *
     * @param input user input with the todo command.
     * @param tasks TaskList to add the new task to.
     * @throws RafayelException if the input format is invalid or description is missing.
     */
    private static String handleTodoCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 5) {
            throw new RafayelException("Please add in the description of the Todo task.");
        }

        Todo newTask = new Todo(input.substring(5).trim());
        tasks.add(newTask);

        return printNewTaskString(newTask, tasks.getSize());
    }

    /**
     * Handles the creation and addition of a new Deadline task.
     *
     * @param input user input with the deadline command.
     * @param tasks TaskList to add the new task to.
     * @throws RafayelException if the input format is invalid, description is missing or date format is wrong.
     */
    private static String handleDeadlineCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 10) {
            throw new RafayelException("Please add in the description of the Deadline task.");
        }
        if (!input.contains("/by")) {
            throw new RafayelException("Deadline format is wrong. Example: deadline [desc] /by [time]");
        }

        String[] taskInfo = input.substring(9).split("/by ");
        LocalDateTime dateTime = handleReadDate(taskInfo[1].trim());
        Deadline newTask = new Deadline(taskInfo[0].trim(), dateTime);
        tasks.add(newTask);

        return printNewTaskString(newTask, tasks.getSize());
    }

    /**
     * Parses a date string into a LocalDateTime object with three supported formats.
     *
     * @param input input of the date string to parse.
     * @return the parsed LocalDateTime object, null if no format matches.
     */
    public static LocalDateTime handleReadDate(String input) {
        // check if valid format
        DateTimeFormatter[] differentTimeFormatters = new DateTimeFormatter[] {
                DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm") };

        for (DateTimeFormatter formatter : differentTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception ignore) {
                // ignore
            }
        }

        System.out.println("Please use one of: MMM d yyyy HH:mm | yyyy/MM/dd HH:mm | dd-MM-yyyy HH:mm");

        return null;
    }

    /**
     * Handles the creation and addition of a new Event task.
     *
     * @param input user input with the event command
     * @param tasks TaskList to add the new task to
     * @throws RafayelException if the input format is invalid, description is missing, or date formats are incorrect.
     */
    private static String handleEventCommand(String input, TaskList tasks) throws RafayelException {
        if (input.length() <= 6) {
            throw new RafayelException("Please add in the description of the Event task.");
        }
        if (!input.contains("/from")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }
        if (!input.contains("/to")) {
            throw new RafayelException("Event format is wrong. Example: event [desc] /from [time] /to [time]");
        }

        String[] taskInfo = input.substring(6).split("/");
        LocalDateTime dateTimeFrom = handleReadDate(taskInfo[1].substring(5).trim());
        LocalDateTime dateTimeTo = handleReadDate(taskInfo[2].substring(3).trim());

        Event newTask = new Event(taskInfo[0].trim(), dateTimeFrom, dateTimeTo);

        tasks.add(newTask);

        return printNewTaskString(newTask, tasks.getSize());
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @throws RafayelException if there's any error while executing user commands
     */
    public String getResponse(String input) throws RafayelException {
        try {
            Parser.CommandType commandType = Parser.parse(input);
            switch (commandType) {
            case BYE:
                return ui.showExit();

            case LIST:
                return tasks.getTaskList();

            case MARK:
                // Mark task
                return handleMarkCommand(input, tasks, true);

            case UNMARK:
                // Unmark task
                return handleMarkCommand(input, tasks, false);

            case TODO:
                // Create Todo Task
                return handleTodoCommand(input, tasks);

            case DEADLINE:
                // Create Deadline Task
                return handleDeadlineCommand(input, tasks);

            case EVENT:
                // Create Event Task
                return handleEventCommand(input, tasks);

            case DELETE:
                // Delete tasks
                if (input.length() <= 7) {
                    throw new RafayelException("Please indicate which task to delete (i.e. delete 1)");
                }
                String[] temp = input.split(" ");
                int taskNumber = Integer.parseInt(temp[1]);

                taskNumber--;
                Task deletedTask = tasks.remove(taskNumber);

                System.out.println("Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have "
                        + tasks.getSize() + " tasks in the list.");
                return "Noted. I've removed this task:\n  " + deletedTask.toString() + "\nNow you have "
                        + tasks.getSize() + " tasks in the list.";

            case FIND:
                // Find substring
                if (input.length() <= 5) {
                    throw new RafayelException("Please indicate what to find in the task (i.e. find book)");
                }
                String substring = input.substring(5).trim();
                ArrayList<Task> matchedTasks = tasks.matchTasks(substring);

                String res = "Here are the matching tasks in your list:\n";
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < matchedTasks.size(); i++) {
                    System.out.println(i + 1 + "." + matchedTasks.get(i).toString());
                    res += i + 1 + "." + matchedTasks.get(i).toString() + "\n";
                }
                return res;

            default:
                throw new RafayelException("Please enter a valid prompt! (i.e. todo/deadline/event)");
            }
        } catch (RafayelException e) {
            // ui.showError(e.getMessage());
            return e.getMessage();
        } finally {
            storage.save(tasks.getAll());
        }
    }
}
