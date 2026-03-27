package mochi;

import javafx.util.Pair;

/**
 * Parses user commands and extracts relevant information for processing.
 * Also executes method calls and handles errors related to command parsing.
 */
public class CommandParser {
    private static final String[] COMMANDS = new String[] {
        "bye", "list", "mark", "unmark", "tag", "untag", "todo", "deadline", "event", "delete", "find", "help"
    };

    private String command;
    private String parameters;

    /**
     * Returns a CommandParser object
     */
    public CommandParser() {}

    /**
     * Reads a line of input from the user and determines the command type and parameters.
     * If the command is 'bye', sets running as false to indicate program termination.
     */
    public void read(String s) {
        assert !s.isEmpty() : "Commands should not be empty.";
        this.parameters = s;
        for (String word : COMMANDS) {
            if (s.startsWith(word)) {
                this.command = word;
                return;
            }
        }
        this.command = "unknown";
    }

    /**
     * Checks if the parsed command matches the given string.
     *
     * @param s the command string to check against
     * @return true if the parsed command matches s, false otherwise
     */
    public boolean is(String s) {
        return s.equals(this.command);
    }

    /**
     * Parses and validates the parameters for the 'mark' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be marked as completed
     * @throws MarkingException if the parameters are invalid or out of range
     */
    public int markCommand(int listSize) throws MarkingException {
        String task = this.parameters.substring(4).trim();
        assert !task.isEmpty() : "Mark commands should contain valid task number.";
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MarkingException(task, listSize);
        }
        if (taskNo > listSize || taskNo < 1) {
            throw new MarkingException(task, listSize);
        }
        return taskNo;
    }

    /**
     * Parses and validates the parameters for the 'unmark' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be marked as uncompleted
     * @throws MarkingException if the parameters are invalid or out of range
     */
    public int unmarkCommand(int listSize) throws MarkingException {
        int commandLength = 6;
        String task = this.parameters.substring(commandLength).trim();
        assert !task.isEmpty() : "Unmark commands should contain valid task number.";
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MarkingException(task, listSize);
        }
        if (listSize == 0 || taskNo > listSize) {
            throw new MarkingException(task, listSize);
        }
        return taskNo;
    }

    /**
     * Parses and validates the parameters for the 'tag' and 'untag' command.
     *
     * @return the 1-indexed task number to be marked as uncompleted
     * @throws MochiException if the tag is empty
     */
    public Pair<Integer, String> tagCommand() throws MochiException {
        String[] text = this.parameters.split(" ");
        int taskNo;
        try {
            taskNo = Integer.parseInt(text[1]);
        } catch (Exception e) {
            throw new MochiException("Task number is required for tagging.");
        }
        if (text.length == 2) {
            throw new MochiException("Tag description is required for tagging.");
        }
        return new Pair<Integer, String>(taskNo, text[2]);
    }

    /**
     * Parses and validates the parameters for the 'todo' command.
     *
     * @return a ToDo task based on user input
     * @throws ToDoException if the description is empty
     */
    public ToDo todoCommand() throws ToDoException {
        int commandLength = 4;
        String task = this.parameters.substring(commandLength).trim();
        if (task.isEmpty()) {
            throw new ToDoException();
        }
        return new ToDo(task);
    }

    /**
     * Parses and validates the parameters for the 'deadline' command.
     *
     * @return a Deadline task based on user input
     * @throws DeadlineException if the description or by date is invalid
     */
    public Deadline deadlineCommand() throws DeadlineException {
        int commandLength = 8;
        try {
            String task = this.parameters.substring(commandLength).trim();
            String[] text = task.split("/by", 2);
            String desc = text[0].trim();
            String by = text[1].trim();

            if (desc.isEmpty() || by.isEmpty()) {
                throw new DeadlineException();
            }
            return new Deadline(desc, by);
        } catch (Exception e) {
            throw new DeadlineException();
        }
    }

    /**
     * Parses and validates the parameters for the 'event' command.
     *
     * @return a, Event task based on user input
     * @throws EventException if the description, to or from date is invalid
     */
    public Event eventCommand() throws EventException {
        int commandLength = 5;
        try {
            String task = this.parameters.substring(commandLength).trim();
            String[] text = task.split("/from", 2);
            String desc = text[0].trim();
            String[] duration = text[1].split("/to", 2);
            String from = duration[0].trim();
            String to = duration[1].trim();

            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EventException();
            }
            return new Event(desc, duration[0].trim(), duration[1].trim());
        } catch (Exception e) {
            throw new EventException();
        }
    }

    /**
     * Parses and validates the parameters for the 'delete' command.
     *
     * @param listSize the current size of the task list for validation
     * @return the 1-indexed task number to be deleted
     * @throws MochiException if the parameters are invalid or out of range
     */
    public int deleteCommand(int listSize) throws MochiException {
        int commandLength = 6;
        String task = this.parameters.substring(commandLength).trim();
        int taskNo;
        try {
            taskNo = Integer.parseInt(task);
        } catch (Exception e) {
            throw new MochiException("Please provide a task number for delete operation.");
        }
        if (listSize == 0) {
            throw new MochiException("There are no tasks to delete.");
        } else if (taskNo > listSize || taskNo < 1) {
            throw new MochiException(String.format("Invalid task number provided. Range is from 1 to %d.", listSize));
        }
        return taskNo;
    }

    /**
     * Extracts the keyword(s) from the user command
     *
     * @return the substring of the keyword(s) to be searched for
     */
    public String findCommand() {
        int commandLength = 4;
        return this.parameters.substring(commandLength).trim();
    }
}
