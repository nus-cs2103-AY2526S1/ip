package paul.task;

import paul.exception.PaulException;
import paul.storage.Storage;
import paul.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

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
        this.tasks.add(task);
    }

    /**
     * Gets the ith task from the TaskList.
     *
     * @param i The ith task to get (1-indexed).
     */
    public Task get(int i) {
        // Get the ith Task in the list
        return this.tasks.get(i - 1);
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
     * Adds a task to the TaskList based on the user's command and saves it in the storage.
     * @param parsedCommand The String array after parsing the command.
     * @param storage Storage to save the new task.
     * @param ui Ui to handle user's input.
     * @throws PaulException if there is an error in the command.
     */
    public void addTask(String[] parsedCommand, Storage storage, Ui ui) throws PaulException {
        String commandType = parsedCommand[0];
        if (parsedCommand.length < 2) {
            throw new PaulException("The description of a " + commandType + " cannot be empty!");
        }
        String description = parsedCommand[1];
        Task newTask;

        switch (commandType) {
        case "todo":
            newTask = new ToDo(description);
            break;
        case "deadline":
            String[] deadlineStr = description.split(" /by ");

            if (deadlineStr.length < 2 || deadlineStr[0].isBlank() || deadlineStr[1].isBlank()) {
                throw new PaulException("A deadline must have a description and a /by date!");
            }

            try {
                LocalDate date = LocalDate.parse(deadlineStr[1]);
                newTask = new Deadline(deadlineStr[0].trim(), date);
            } catch (DateTimeParseException e) {
                throw new PaulException("/by must be in yyyy-mm-dd format! (e.g., 2019-10-15)");
            }
            break;
        case "event":
            String[] eventStr = description.split(" /from | /to ");

            if (eventStr.length < 3 || eventStr[0].isBlank() || eventStr[1].isBlank() || eventStr[2].isBlank()) {
                throw new PaulException("An event must have a description, /from, and /to!");
            }

            try {
                LocalDate fromDate = LocalDate.parse(eventStr[1]);
                LocalDate toDate = LocalDate.parse(eventStr[2]);
                newTask = new Event(eventStr[0].trim(), fromDate, toDate);
            } catch (DateTimeParseException e) {
                throw new PaulException("/from and /to must be in yyyy-mm-dd format! (e.g., 2019-10-15)");
            }
            break;
        default:
            newTask = null;
        }
        tasks.add(newTask);
        storage.saveTasks(this);
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Deletes a task from TaskList based on the user's command and save the changes in the storage.
     * @param parsedCommand The String array after parsing the command.
     * @param storage Storage to record the changes after deleting the task.
     * @param ui Ui to handle user's input.
     * @throws PaulException if there is an error in the command.
     */
    public void deleteTask(String[] parsedCommand, Storage storage, Ui ui) throws PaulException {
        if (parsedCommand.length < 2) {
            throw new PaulException("The description of a delete cannot be empty!");
        }

        try {
            int index = Integer.parseInt(parsedCommand[1]);
            Task task = this.get(index);
            tasks.remove(index - 1);
            storage.saveTasks(this);
            ui.showTaskDeleted(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new PaulException("Oops! Invalid task number for delete command.");
        } catch (NumberFormatException e) {
            throw new PaulException("Please input a valid task number!");
        }
    }

    /**
     * Marks or unmarks a task from TaskList based on the user's command and save the changes in the storage.
     * @param parsedCommand The String array after parsing the command.
     * @param storage Storage to record the changes after marking/unmarking the task.
     * @param ui Ui to handle user's input.
     * @throws PaulException if there is an error in the command.
     */
    public void markTask(String[] parsedCommand, Storage storage, Ui ui) throws PaulException {
        String commandType = parsedCommand[0];
        if (parsedCommand.length < 2) {
            throw new PaulException("The description of a " + commandType + " cannot be empty!");
        }
        String input = parsedCommand[1];

        switch (commandType) {
        case "mark":
            try {
                int index = Integer.parseInt(input);
                Task task = this.get(index);
                task.markTask();
                ui.showTaskMarked(task);
            } catch (IndexOutOfBoundsException e) {
                throw new PaulException("Oops! Invalid task number for mark command.");
            } catch (NumberFormatException e) {
                throw new PaulException("Please input a valid task number!");
            }
            break;
        case "unmark":
            try {
                int index = Integer.parseInt(input);
                Task task = this.get(index);
                task.unmarkTask();
                ui.showTaskUnmarked(task);
            } catch (IndexOutOfBoundsException e) {
                throw new PaulException("Oops! Invalid task number for unmark command.");
            } catch (NumberFormatException e) {
                throw new PaulException("Please input a valid task number!");
            }
            break;
        }
        storage.saveTasks(this);
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
