package merlie.parser;

import merlie.command.Command;
import merlie.command.DeadlineCommand;
import merlie.command.DeleteCommand;
import merlie.command.EventCommand;
import merlie.command.ExitCommand;
import merlie.command.FindCommand;
import merlie.command.ListCommand;
import merlie.command.MarkCommand;
import merlie.command.TodoCommand;
import merlie.command.UnmarkCommand;
import merlie.exception.MerlieException;

/**
 * Parses user input into commands for execution by Merlie.
 */
public class Parser {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String FIND_COMMAND = "find";
    private static final String EMPTY_COMMAND = "";
    private static final String EMPTY_COMMAND_RESPONSE = "silence is good too...";
    private static final String INVALID_TASK_NUMBER_ERROR = "please provide a valid task number!";
    private static final String EMPTY_DESCRIPTION_ERROR = "description must be non-empty!";
    private static final String INVALID_DEADLINE_ERROR = "provide a deadline in the correct syntax "
            + "(deadline <task> /by <deadline>)";
    private static final String EMPTY_INFORMATION_ERROR = "both description and date(s) must be non-empty!";
    private static final String INVALID_EVENT_ERROR = "provide an event in the correct syntax "
            + "(event <task> /from <start> /to <end>)";

    /**
     * Parses a string input into a Command object.
     *
     * @param input User input string.
     * @return Corresponding Command object.􀀔􀀓􀀔􀀘􀀗􀀔􀀓􀀚􀀚􀀔􀀗􀀓􀀖􀀙􀀓􀀚􀀘􀀘􀀗􀀚􀀜􀀘􀀔􀀓􀀔􀀘􀀗􀀔􀀓􀀚􀀚􀀔􀀗􀀓􀀖􀀙􀀓􀀚􀀘􀀘􀀗􀀚􀀜􀀘
     * @throws MerlieException If the input cannot be parsed into a valid command.
     */
    public static Command parse(String input) throws MerlieException {
        String[] parts = input.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case EXIT_COMMAND:
            return new ExitCommand();

        case LIST_COMMAND:
            return new ListCommand();

        case MARK_COMMAND:
            return parseMarkCommand(arguments);

        case UNMARK_COMMAND:
            return parseUnmarkCommand(arguments);

        case DELETE_COMMAND:
            return parseDeleteCommand(arguments);

        case TODO_COMMAND:
            return parseTodoCommand(arguments);

        case DEADLINE_COMMAND:
            return parseDeadlineCommand(arguments);

        case EVENT_COMMAND:
            return parseEventCommand(arguments);

        case FIND_COMMAND:
            return parseFindCommand(arguments);

        case EMPTY_COMMAND:
            throw new MerlieException(EMPTY_COMMAND_RESPONSE);

        default:
            throw new MerlieException("");
        }
    }

    /**
     * Parses arguments for a MarkCommand.
     *
     * @param arguments The task number to mark.
     * @return A MarkCommand with the specified index.
     * @throws MerlieException If the task number is not valid.
     */
    private static MarkCommand parseMarkCommand(String arguments) throws MerlieException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new MerlieException(INVALID_TASK_NUMBER_ERROR);
        }
    }

    /**
     * Parses arguments for an UnmarkCommand.
     *
     * @param arguments The task number to unmark.
     * @return An UnmarkCommand with the specified index.
     * @throws MerlieException If the task number is not valid.
     */
    private static UnmarkCommand parseUnmarkCommand(String arguments) throws MerlieException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new MerlieException(INVALID_TASK_NUMBER_ERROR);
        }
    }

    /**
     * Parses arguments for a DeleteCommand.
     *
     * @param arguments The task number to delete.
     * @return A DeleteCommand with the specified index.
     * @throws MerlieException If the task number is not valid.
     */
    private static DeleteCommand parseDeleteCommand(String arguments) throws MerlieException {
        try {
            int index = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new MerlieException(INVALID_TASK_NUMBER_ERROR);
        }
    }

    /**
     * Parses arguments for a TodoCommand.
     *
     * @param arguments The description of the todo task.
     * @return A TodoCommand with the specified description.
     * @throws MerlieException If the description is empty.
     */
    private static TodoCommand parseTodoCommand(String arguments) throws MerlieException {
        if (arguments.isEmpty()) {
            throw new MerlieException(EMPTY_DESCRIPTION_ERROR);
        }
        return new TodoCommand(arguments);
    }

    /**
     * Parses arguments for a DeadlineCommand.
     *
     * @param arguments The task description and deadline, formatted as
     *     "description /by deadline".
     * @return A DeadlineCommand with the parsed description and deadline.
     * @throws MerlieException If the syntax is invalid or any field is empty.
     */
    private static DeadlineCommand parseDeadlineCommand(String arguments) throws MerlieException {
        if (!arguments.contains(" /by ")) {
            throw new MerlieException(INVALID_DEADLINE_ERROR);
        }
        String[] taskInfo = arguments.split(" /by ", 2);
        String description = taskInfo[0].trim();
        String byString = taskInfo[1].trim();

        if (description.isEmpty() || byString.isEmpty()) {
            throw new MerlieException(EMPTY_INFORMATION_ERROR);
        }

        ParsedDate parsed = ParsedDate.parseDate(byString);
        return new DeadlineCommand(description, parsed.getDate(), parsed.getHasTime());
    }

    /**
     * Parses arguments for an EventCommand.
     *
     * @param arguments The task description and deadline, formatted as
     *     "description /from start /to end".
     * @return An EventCommand with the parsed description and times.
     * @throws MerlieException If the syntax is invalid or any field is empty.
     */
    private static EventCommand parseEventCommand(String arguments) throws MerlieException {
        if (!arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new MerlieException(INVALID_EVENT_ERROR);
        }

        String description = "";
        String fromString = "";
        String toString = "";

        try {
            String[] taskInfo1 = arguments.split(" /from ", 2);
            description = taskInfo1[0].trim();
            String[] taskInfo2 = taskInfo1[1].split(" /to ", 2);
            fromString = taskInfo2[0].trim();
            toString = taskInfo2[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MerlieException(INVALID_EVENT_ERROR);
        }

        if (description.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
            throw new MerlieException(EMPTY_INFORMATION_ERROR);
        }

        ParsedDate from = ParsedDate.parseDate(fromString);
        ParsedDate to = ParsedDate.parseDate(toString);

        return new EventCommand(description, from.getDate(), from.getHasTime(),
                to.getDate(), to.getHasTime());
    }

    /**
     * Parses arguments for a FindCommand.
     *
     * @param arguments The keyword to search for in task descriptions.
     * @return A FindCommand with the specified keyword.
     * @throws MerlieException If the keyword is empty.
     */
    private static FindCommand parseFindCommand(String arguments) throws MerlieException {
        if (arguments.isEmpty()) {
            throw new MerlieException(EMPTY_DESCRIPTION_ERROR);
        }
        return new FindCommand(arguments);
    }
}
