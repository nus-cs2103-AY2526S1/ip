package paul.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import paul.exception.PaulException;
import paul.parser.Parser;

/**
 * A list that contains multiple tasks for Paul.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initialises the TaskList with an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the TaskList.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        assert task != null : "Cannot add null Task to TaskList";
        this.tasks.add(task);
    }

    /**
     * Gets the ith task from the TaskList.
     *
     * @param index The ith task to get (1-indexed).
     * @return The task at position i.
     */
    public Task get(int index) {
        // Get the ith Task in the list
        return this.tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the TaskList based on the user's command.
     *
     * @param parsedCommand The String array after parsing the command.
     * @return The task that was added.
     * @throws PaulException if there is an error in the command.
     */
    public Task addTask(String[] parsedCommand) throws PaulException {
        Parser.CommandType commandType = Parser.getCommandType(parsedCommand[0]);
        if (parsedCommand.length < 2) {
            throw new PaulException("Hey! You forgot the description. I can't add a "
                    + commandType + " with nothing in it.");
        }
        String description = parsedCommand[1].trim();

        //CHECKSTYLE.OFF: Indentation
        Task newTask = switch (commandType) {
            case TODO -> createToDo(description);
            case DEADLINE -> createDeadline(description);
            case EVENT -> createEvent(description);
            default -> null;
        };
        //CHECKSTYLE.ON: Indentation
        assert newTask != null : "Task creation failed in addTask()";
        if (isDuplicate(newTask)) {
            throw new PaulException("Uhh you already added this task. No duplicates allowed!");
        }

        return newTask;
    }

    private ToDo createToDo(String description) {
        return new ToDo(description);
    }

    private Task createDeadline(String description) throws PaulException {
        Task newTask;
        String[] deadlineStr = description.split(" /by ");

        if (deadlineStr.length < 2 || deadlineStr[0].isBlank() || deadlineStr[1].isBlank()) {
            throw new PaulException("Hey! A deadline requires a /by date for me to keep track!");
        }

        try {
            LocalDate date = LocalDate.parse(deadlineStr[1]);
            newTask = new Deadline(deadlineStr[0].trim(), date);
        } catch (DateTimeParseException e) {
            throw new PaulException("Hey! Your /by date looks wrong. Use yyyy-mm-dd, e.g. 2019-10-15.");
        }
        return newTask;
    }

    private Task createEvent(String description) throws PaulException {
        Task newTask;
        String[] eventStr = description.split(" /from | /to ");

        if (eventStr.length < 3 || eventStr[0].isBlank() || eventStr[1].isBlank() || eventStr[2].isBlank()) {
            throw new PaulException("Hey! An event requires a /from, and /to date! "
                    + "Otherwise I won't be able to understand :(");
        }

        try {
            LocalDate fromDate = LocalDate.parse(eventStr[1]);
            LocalDate toDate = LocalDate.parse(eventStr[2]);
            newTask = new Event(eventStr[0].trim(), fromDate, toDate);
        } catch (DateTimeParseException e) {
            throw new PaulException("Hey! /from and /to dates must follow yyyy-mm-dd format! (e.g., 2019-10-15) "
                    + "Otherwise, I get confused.");
        }
        return newTask;
    }

    /**
     * Deletes a task from TaskList based on the user's command and save the changes in the storage.
     *
     * @param parsedCommand The String array after parsing the command.
     * @return The task that was deleted.
     * @throws PaulException if there is an error in the command.
     */
    public Task deleteTask(String[] parsedCommand) throws PaulException {
        if (parsedCommand.length < 2) {
            throw new PaulException("Delete what? I need the number of the task to delete.");
        }
        Task task;
        try {
            int index = Integer.parseInt(parsedCommand[1].trim());
            task = this.get(index);
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("That task number doesn't exist. Double-check your list?");
        } catch (NumberFormatException e) {
            throw new PaulException("That doesn't look like a number, try again!");
        }
        assert task != null : "Deleted task should not be null";
        return task;
    }

    /**
     * Marks a task from TaskList and save the changes in the storage.
     *
     * @param parsedCommand The String array after parsing the command.
     * @return The marked task.
     * @throws PaulException if there is an error in the command.
     */
    public Task markTask(String[] parsedCommand) throws PaulException {
        return updateTaskStatus(parsedCommand, true);
    }

    /**
     * Unmarks a task from TaskList and save the changes in the storage.
     *
     * @param parsedCommand The String array after parsing the command.
     * @return The unmarked task.
     * @throws PaulException if there is an error in the command.
     */
    public Task unmarkTask(String[] parsedCommand) throws PaulException {
        return updateTaskStatus(parsedCommand, false);
    }

    private Task updateTaskStatus(String[] parsedCommand, boolean mark) throws PaulException {
        if (parsedCommand.length < 2) {
            throw new PaulException("Mark what? You didn't give me a task number.");
        }
        try {
            int index = Integer.parseInt(parsedCommand[1].trim());
            Task task = get(index);
            if (mark) {
                task.markTask();
            } else {
                task.unmarkTask();
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("That task number doesn't exist. Double-check your list?");
        } catch (NumberFormatException e) {
            throw new PaulException("That doesn't look like a number, try again!");
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description based on the user's command.
     *
     * @param parsedCommand The String array after parsing the command.
     * @return A list of found tasks.
     * @throws PaulException if there is an error in the command.
     */
    public TaskList findTasks(String[] parsedCommand) throws PaulException {
        if (parsedCommand.length < 2) {
            throw new PaulException("You didn't give me anything to search for. What keyword should I use?");
        }

        TaskList foundTasks = new TaskList();
        String keyword = parsedCommand[1].trim();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    /**
     * Checks if a new task is a duplicate of an existing task.
     *
     * @param newTask The task to check.
     * @return {@code true} if the task is a duplicate task, otherwise {@code false}.
     */
    public boolean isDuplicate(Task newTask) {
        return tasks.contains(newTask);
    }

    /**
     * Lists out all tasks in the TaskList.
     *
     * @return The string representation of all tasks in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString().trim();
    }
}
