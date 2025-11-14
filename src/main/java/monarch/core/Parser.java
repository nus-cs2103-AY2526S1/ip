package monarch.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import monarch.exceptions.MonException;
import monarch.tasks.Deadline;
import monarch.tasks.Event;
import monarch.tasks.Task;
import monarch.tasks.ToDo;

/**
 * Represents how commands are interpreted & executed by Monarch.
 */
public class Parser {
    private boolean isEnd = false;
    private String result = "";

    /**
     * Constructor for Parser.
     *
     * @param userInput The commands.
     * @throws MonException The exception when an invalid command is inputted.
     */
    public Parser(String userInput) throws MonException {
        String[] sliced = userInput.split(" ");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        // Interpret user input
        switch (sliced[0]) {
        case "bye":
            /* Terminates the program */
            this.bye(ui, tasks);
            break;

        case "list":
            /* Return all inputs */
            result = this.list(ui, tasks);
            break;

        case "mark":
            /* Mark task as done */
            result = this.mark(ui, tasks, sliced);
            break;

        case "unmark":
            /* Mark task as undone */
            result = this.unmark(ui, tasks, sliced);
            break;

        case "todo":
            /* Create a ToDo task */
            result = this.todo(ui, tasks, userInput);
            break;

        case "deadline":
            /* Create a deadline task */
            result = this.deadline(ui, tasks, userInput);
            break;

        case "event":
            /* Create an event task */
            result = this.event(ui, tasks, userInput);
            break;

        case "delete":
            /* Delete a task */
            result = this.delete(ui, tasks, sliced);
            break;

        case "clear":
            /* Clear all tasks */
            result = ui.clearList();
            break;

        case "find":
            /* Find a task by keywords */
            result = this.find(ui, tasks, userInput);
            break;

        case "sort":
            /* Sort the task list */
            result = this.sort(ui, tasks);
            break;

        default:
            /* Unknown case */
            throw new MonException("That's not something I can do unfortunately ¯\\_(ツ)_/¯");
        }
    }

    /**
     * Checks if Parser has received a terminate command.
     *
     * @return A boolean.
     */
    public boolean isEnd() {
        return this.isEnd;
    }

    /**
     * Returns the output of the command.
     *
     * @return String message.
     */
    public String getResult() {
        return this.result;
    }

    /**
     * Prepares the program to end by saving the task list.
     */
    private void bye(Ui ui, TaskList tasks) {
        this.isEnd = true;
        Storage storage = new Storage();
        try {
            storage.save(tasks.getAll());
        } catch (IOException e) {
            throw new MonException("UH-OH: Tasks didn't get saved properly.");
        }
        result = ui.end();
    }

    /**
     * Returns the task list as a String.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @return A string of the task list.
     */
    private String list(Ui ui, TaskList tasks) {
        return ui.listTasks(tasks.getAll());
    }

    /**
     * Marks a task as done.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param args The task index to mark.
     * @return The marked message on success.
     * @throws MonException When the args doesn't contain a number string.
     */
    private String mark(Ui ui, TaskList tasks, String[] args) throws MonException {
        try {
            if (args.length < 2) {
                throw new MonException("UH-OH: You forgot to indicate a task to mark (e.g. 1).");
            } else if (tasks.size() < Integer.parseInt(args[1]) - 1 || Integer.valueOf(args[1]) - 1 < 0) {
                throw new MonException("UH-OH: The task isn't in the range of the task list.");
            }
        } catch (NumberFormatException e) {
            throw new MonException("UH-OH: You need to give a number for the task to delete.");
        }
        tasks.get(Integer.parseInt(args[1]) - 1).markAsDone();
        return ui.mark(tasks.get(Integer.parseInt(args[1]) - 1));
    }

    /**
     * Unmarks a task as done.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param args The task index to unmark.
     * @return The unmarked message on success.
     * @throws MonException When the args doesn't contain a number string.
     */
    private String unmark(Ui ui, TaskList tasks, String[] args) throws MonException {
        try {
            if (args.length < 2) {
                throw new MonException("UH-OH: You forgot to indicate a task to unmark (e.g. 1).");
            } else if (tasks.size() < Integer.parseInt(args[1]) - 1 || Integer.valueOf(args[1]) - 1 < 0) {
                throw new MonException("UH-OH: The task isn't in the range of the task list.");
            }
        } catch (NumberFormatException e) {
            throw new MonException("UH-OH: You need to give a number for the task to delete.");
        }
        tasks.get(Integer.parseInt(args[1]) - 1).unmark();
        return ui.unmark(tasks.get(Integer.parseInt(args[1]) - 1));
    }

    /**
     * Adds a todo Task.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param userInput The information for building the todo task.
     * @return The todo message on success.
     */
    private String todo(Ui ui, TaskList tasks, String userInput) {
        if (userInput.split(" ").length == 1) {
            throw new MonException("UH-OH: You need a description for a todo.");
        }
        ToDo toDoTask = new ToDo(userInput.substring(4 + 1));
        tasks.add(toDoTask);
        return ui.addTask(toDoTask);
    }

    /**
     * Adds a deadline Task.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param userInput The information for building the deadline task.
     * @return The deadline message on success.
     * @throws MonException When the args doesn't contain enough information.
     */
    private String deadline(Ui ui, TaskList tasks, String userInput) throws MonException {
        try {
            String[] args = userInput.substring(8 + 1).split(" /by ", 2);
            if (args.length == 1) {
                throw new MonException("UH-OH: You need an end time for a deadline.");
            }
            Deadline deadlineTask = new Deadline(args[0], args[1]);
            tasks.add(deadlineTask);
            return ui.addTask(deadlineTask);
        } catch (RuntimeException error) {
            throw new MonException("UH-OH: You need an end time for a deadline.");
        }
    }

    /**
     * Adds an event Task.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param userInput The information for building the event task.
     * @return The event message on success.
     * @throws MonException When the args doesn't contain enough information.
     */
    private String event(Ui ui, TaskList tasks, String userInput) throws MonException {
        try {
            String[] split = userInput.substring(5 + 1).split(" /from ", 2);
            String[] temp = split[1].split(" /to ", 2);
            String[] eventArgs = {split[0], temp[0], temp[1]};
            Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
            tasks.add(eventTask);
            return ui.addTask(eventTask);
        } catch (RuntimeException error) {
            throw new MonException("UH-OH: You need a start time & end time for an event.");
        }
    }

    /**
     * Deletes a task.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param args The task index to delete.
     * @return The delete message on success.
     * @throws MonException When the args doesn't contain a number string.
     */
    private String delete(Ui ui, TaskList tasks, String[] args) throws MonException {
        try {
            if (args.length < 2) {
                throw new MonException("UH-OH: You forgot to indicate a task to unmark (e.g. 1).");
            } else if (tasks.size() < Integer.valueOf(args[1]) - 1 || Integer.valueOf(args[1]) - 1 < 0) {
                throw new MonException("UH-OH: The task isn't in the range of the task list.");
            }
        } catch (NumberFormatException e) {
            throw new MonException("UH-OH: You need to give a number for the task to delete.");
        }
        Task temp = tasks.get(Integer.valueOf(args[1]) - 1);
        tasks.remove(temp);
        return ui.deleteTask(temp);
    }

    /**
     * Finds a task from the provided task list.
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @param userInput The task to find.
     * @return The find message on success.
     * @throws MonException When the args doesn't contain valid keywords.
     */
    private String find(Ui ui, TaskList tasks, String userInput) throws MonException {
        try {
            String keyphrase = userInput.split(" ", 2)[1];
            ArrayList<Task> taskList = new ArrayList<>();
            Pattern pattern = Pattern.compile(keyphrase, Pattern.CASE_INSENSITIVE);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                Matcher matcher = pattern.matcher(task.getDescription());
                if (matcher.find()) {
                    taskList.add(task);
                }
            }

            return ui.findTask(taskList);
        } catch (RuntimeException error) {
            throw new MonException("UH-OH: That's not a valid keyword for finding tasks.");
        }
    }

    /**
     * Returns a sorted list of tasks, based on the type of task and date (if applicable).
     *
     * @param ui The instance of Ui.
     * @param tasks The task list to display.
     * @return A sorted list of tasks.
     */
    private String sort(Ui ui, TaskList tasks) {
        return ui.sort(tasks);
    }
}
