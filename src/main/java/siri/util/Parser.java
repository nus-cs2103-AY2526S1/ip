package siri.util;
import siri.Command;
import siri.exception.SiriException;

/**
 * Parser to parse the input
 */
public class Parser {
    private Command command;
    private String description;
    private String argument;
    private String keyword;
    private String deadline;
    private String from;
    private String to;
    private int spaceIndex;

    /**
     * Constructor of parser, determine the keyword and argument from the input
     * @param input the input contents
     */
    public Parser(String input) {
        this.spaceIndex = input.indexOf(' ');
        this.keyword = (spaceIndex == -1) ? input : input.substring(0, spaceIndex);
        this.argument = (spaceIndex == -1) ? "" : input.substring(spaceIndex + 1);
    }

    /**
     * get command from the keyword
     * @return command
     */
    public Command getCommand() {
        return Command.fromKeyword(keyword);
    }

    /**
     * Getter of keyword
     * @return keyword
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Getter of argument
     * @return argument
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Parsing ToDoTask
     * @return the description of the ToDoTask if the argument is valid
     * @throws SiriException if the argument is empty, throw an exception
     */
    public String parseTodo() throws SiriException {
        assert !argument.isEmpty() : "Argument is empty";
        if (argument.isEmpty()) {
            throw new SiriException("Todo task description is empty", "todo <description>", argument);
        } else {
            return argument;
        }
    }

    /**
     * Parsing mark action
     * @return the index of the task being marked
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseMark() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "mark <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
            if (n <= 0) {
                throw new SiriException("index should be greater than 0");
            }
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "siri.task.task number must be an integer",
                    "mark <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing unmark action
     * @return the index of the task being unmarked
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseUnMark() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "unmark <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
            if (n <= 0) {
                throw new SiriException("index should be greater than 0");
            }
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "siri.task.task number must be an integer",
                    "unmark <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing delete action
     * @return the index of the task being deleted
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseDelete() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "delete <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
            if (n <= 0) {
                throw new SiriException("index should be greater than 0");
            }
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "siri.task.task number must be an integer",
                    "delete <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing deadline task
     * @return a String array where the first element is the description adn the second is the deadline
     * @throws SiriException if the argument is invalid, throw an exception
     */
    public String[] parseDeadline() throws SiriException {
        final String marker = "/by";
        if (argument == null) {
            argument = "";
        }
        String arg = argument.trim();
        int byIdx = arg.toLowerCase().indexOf(marker);
        if (byIdx < 0) {
            throw new SiriException(
                    "Missing '/by' marker for deadline task",
                    "deadline <description> /by <time>",
                    argument
            );
        }
        description = arg.substring(0, byIdx).trim();
        deadline = arg.substring(byIdx + marker.length()).trim();

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new SiriException(
                    "Deadline description or time is empty",
                    "deadline <description> /by <time>",
                    argument
            );
        }
        return new String[]{description, deadline};
    }

    /**
     * Parsing EventTask
     * @return a String array where the first element is the description,
     *          the second is the starting time, the third is the ending time
     * @throws SiriException if the argument is invalid, throw an exception
     */
    public String[] parseEvent() throws SiriException {
        final String fromMarker = "/from";
        final String toMarker = "/to";
        if (argument == null) {
            argument = "";
        }
        int fromIndex = argument.indexOf(fromMarker);
        int toIndex = argument.indexOf(toMarker);
        if (fromIndex < 0) {
            throw new SiriException("Missing '/from' separator for event task");
        }
        if (toIndex < 0) {
            throw new SiriException("Missing '/to' separator for event task");
        }
        description = argument.substring(0, fromIndex).trim();
        from = argument.substring(fromIndex + fromMarker.length(), toIndex).trim();
        to = argument.substring(toIndex + toMarker.length()).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new SiriException("Event description, start, or end is empty",
                    "event <description> /from  <start> /to <end>", argument);
        }
        return new String[] {description, from, to};
    }

    /**
     * Parse the find command
     * @return the argument find
     * @throws SiriException throws an exception when the keyword is empty
     */
    public String parseFind() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException("Missing keyword");
        } else {
            return argument;
        }
    }

    /**
     * Parse the UndoTask
     * @return the index of the task
     * @throws SiriException throws exception when the argument is invalid
     */
    public int parseUndo() throws SiriException {
        if (argument.isEmpty()) {
            return 0;
        } else if (isDigits(argument)) {
            return Integer.parseInt(argument);
        } else {
            throw new SiriException("Invalid argument, argument should be a number");
        }
    }

    /**
     * Check whether the input String is a digit
     * @param s input String
     * @return whether the input String is a digit
     */
    public static boolean isDigits(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
