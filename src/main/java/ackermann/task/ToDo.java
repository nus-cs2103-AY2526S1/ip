package ackermann.task;

import java.util.List;

import ackermann.exceptions.task.EmptyNameException;

/**
 * Class to create toDo object
 */
public class ToDo extends Task {
    /**
     * Constructor for toDo task
     * @param name Name of task
     */
    public ToDo(String name) throws EmptyNameException {
        super(name);
    }

    @Override
    public String toString() {
        List<String> tags = super.getTags();
        return String.valueOf(new StringBuilder()
                .append("[T]")
                .append(super.toString())
                .append(" ")
                .append(tags.isEmpty() ? "[ ]" : tags)
        );
    }
}
