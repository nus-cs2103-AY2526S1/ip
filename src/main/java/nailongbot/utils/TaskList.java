package nailongbot.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nailongbot.exception.EmptyDescriptionException;
import nailongbot.exception.InvalidFormatException;
import nailongbot.exception.InvalidTaskNumberException;
import nailongbot.task.Deadline;
import nailongbot.task.Event;
import nailongbot.task.Task;
import nailongbot.task.Todo;


/**
 * Manages the collection of tasks and provides operations on them.
 * Handles adding, removing, marking, and searching tasks.
 */
public class TaskList {
    private ArrayList<Task> memory;

    public TaskList() {
        this.memory = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> memory) {
        this.memory = memory;
    }

    public void addTask(Task task) {
        memory.add(task);
    }

    public ArrayList<Task> getMemory() {
        return this.memory;
    }

    /**
     * Marks the task as complete.
     *
     * @throws InvalidTaskNumberException if task number given does not exist.
     */
    public void markTask(int index) throws InvalidTaskNumberException {
        validateIndex(index);
        memory.get(index).doTask();
    }

    /**
     * Marks the task as not completed.
     *
     * @throws InvalidTaskNumberException if task number given does not exist.
     */
    public void unmarkTask(int index) throws InvalidTaskNumberException {
        validateIndex(index);
        memory.get(index).undoTask();
    }

    /**
     * Removes the task from memory.
     *
     * @throws InvalidTaskNumberException if task number given does not exist.
     */
    public Task deleteTask(int index) throws InvalidTaskNumberException {
        validateIndex(index);
        return memory.remove(index);
    }

    public int size() {
        return memory.size();
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return memory;
    }
    /**
     * Finds the task with that specific index.
     *
     * @return Task with specific index.
     * @throws InvalidTaskNumberException if task number given does not exist.
     */
    public Task getTask(int index) throws InvalidTaskNumberException {
        validateIndex(index);
        return memory.get(index);
    }

    /**
     * Checks with index given is within the range of ArrayList memory.
     *
     * @param index the index of task in array.
     * @throws InvalidTaskNumberException if task number given does not exist.
     */
    private void validateIndex(int index) throws InvalidTaskNumberException {
        if (index < 0 || index >= memory.size()) {
            throw new InvalidTaskNumberException("Error: Invalid task number! Use 'list' to see available tasks");
        }
    }

    /**
     * Creates a todo Task.
     * Adds todo Task to ArrayList memory.
     *
     * @param description the users input for todo.
     * @throws EmptyDescriptionException if there is no description.
     */
    public void addTodo(String description) throws EmptyDescriptionException {
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Error: Todo description cannot be empty");
        }
        memory.add(new Todo(description));
    }

    /**
     * Creates a deadline Task.
     * Splits input into description and date time.
     * Checks for appropriate deadline format.
     * Adds deadline Task to ArrayList memory.
     *
     * @param input the users input for deadline.
     * @throws InvalidFormatException if the input is not in appropriate deadline format.
     * @throws EmptyDescriptionException if there is no description.
     */
    public void addDeadline(String input) throws InvalidFormatException, EmptyDescriptionException {
        String[] dateParts = input.split("/by", 2);
        if (dateParts.length != 2) {
            throw new InvalidFormatException("Error: Use format - deadline [description] /by [time]");
        }

        String description = dateParts[0].trim();
        String byDate = dateParts[1].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Error: Deadline description cannot be empty");
        }

        memory.add(new Deadline(description, byDate));
    }

    /**
     * Creates a event Task.
     * Splits input into description, from date, time and to date, time.
     * Checks for appropriate deadline format.
     * Adds deadline Task to ArrayList memory.
     *
     * @param input the users input for event.
     * @throws InvalidFormatException if the input is not in appropriate event format.
     * @throws EmptyDescriptionException if there is no description.
     */
    public void addEvent(String input) throws InvalidFormatException, EmptyDescriptionException {
        String eventParts[] = input.split(" /from | /to ");
        if (eventParts.length != 3) {
            throw new InvalidFormatException("Error: Use format - event [description] /from [start] /to [end]");
        }

        String description = eventParts[0].trim();
        String startTime = eventParts[1].trim();
        String endTime = eventParts[2].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Error: Event description cannot be empty");
        }

        memory.add(new Event(description, startTime, endTime));
    }

    /**
     * Finds all Task due on the input date.
     *
     * @param dateString date to query for.
     * @return all tasks on that date.
     * @throws InvalidFormatException if the input is not in appropriate date format.
     */
    public String getTasksOnDate(String dateString) throws InvalidFormatException {
        LocalDate queryDate;
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            queryDate = LocalDate.parse(dateString.trim(), inputFormatter);
        } catch (DateTimeParseException e) {
            System.out.print(Ui.LINE);
            throw new InvalidFormatException("Invalid format! Please use - show d/M/yyyy, e.g., 28/8/2025\n" + Ui.LINE);
        }

        StringBuilder result = new StringBuilder();
        boolean isFound = false;

        for (Task task : memory) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getByDate().toLocalDate().equals(queryDate)) {
                    result.append(d.toString()).append("\n");
                    isFound = true;
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                LocalDate start = e.getStartTime().toLocalDate();
                LocalDate end = e.getEndTime().toLocalDate();
                if (!queryDate.isBefore(start) && !queryDate.isAfter(end)) {
                    result.append(e.toString()).append("\n");
                    isFound = true;
                }
            }
        }

        if (!isFound) {
            System.out.print(Ui.LINE);
            result.append("No tasks/events isfound on ").append(queryDate.format(inputFormatter) + "\n" + Ui.LINE);
        }
        return result.toString();
    }
}
