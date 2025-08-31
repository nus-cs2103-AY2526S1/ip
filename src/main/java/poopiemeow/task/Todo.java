package poopiemeow.task;

import poopiemeow.exception.EmptyDescriptionException;

public class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toFileString() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }
}