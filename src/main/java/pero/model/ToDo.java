package pero.model;

import pero.exception.PeroException;

import java.time.LocalDate;

/** Represents ToDo task with description.*/
public class ToDo extends Task {

    /**
     * Constructs new ToDo task with description and completion status
     *
     * @param description Text description of the task.
     * @param isDone True if task marked done, false otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Creates ToDo task from raw user input String.
     *
     * @param input User input line.
     * @return New ToDo task according to input.
     * @throws PeroException If input is in wrong format.
     */
    public static ToDo fromInput(String input) throws PeroException {
        if (input.equals("todo")) {
            throw new PeroException("Oops! pero.model.ToDo requires 'todo [task]' format, try again!");
        }
        String taskToDo = input.substring(5); //starts at index 5, remove "todo "

        if (taskToDo.isBlank()) {
            throw new PeroException("Oops! pero.model.ToDo requires 'todo [task]' format, try again!");
        }
        return new ToDo(taskToDo, false);
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone? "1" : "0") + " | " + this.description;
    }

    @Override
    public LocalDate getDueDate() {
        return null; // TODO doesnt have duedate
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
