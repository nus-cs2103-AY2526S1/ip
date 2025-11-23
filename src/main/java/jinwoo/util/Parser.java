package jinwoo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import jinwoo.command.AddCommand;
import jinwoo.command.Command;
import jinwoo.command.DeleteCommand;
import jinwoo.command.ExitCommand;
import jinwoo.command.FindCommand;
import jinwoo.command.ListCommand;
import jinwoo.command.MarksCommand;
import jinwoo.command.UndoCommand;
import jinwoo.exception.DukeException;

/**
 * A utility class for Parsing.
 */
public class Parser {
    private static final String BYE = "bye";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String UNDO = "undo";

    /**
     * Parses a string into a LocalDate object.
     *
     * @throws DateTimeParseException if the input string is not in the correct format.
     */
    public static LocalDate parseToDate(String by) throws DukeException {
        try {
            String date = by.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            return (LocalDate.parse(date, formatter));
        } catch (DateTimeParseException e) {
            throw new DukeException("Warning ??? : Please enter date in the format yyyy-MM-dd.");
        }
    }

    /**
     * Parses a command string into a meaningful Command object
     *
     * @returns fully string array as Command object
     */
    public static Command parseCommand(String input) throws DukeException {
        if (input == null || input.trim().isEmpty()) {
            throw new DukeException("Warning ???: Empty Command");
        }
        // Allow multiple spaces between words and trim it
        String trimmed = input.trim().replaceAll("\\s+", " ");
        // Split only type and the rest
        String[] parts = trimmed.split(" ", 2);
        String type = parts[0];
        try {
            switch (type) {
            case BYE -> {
                return new ExitCommand();
            }
            case MARK -> {
                String taskNum = parts[1];
                return new MarksCommand(new String[]{MARK, taskNum});
            }
            case UNMARK -> {
                String taskNum = parts[1];
                return new MarksCommand(new String[]{UNMARK, taskNum});
            }
            case LIST -> {
                return new ListCommand();
            }
            case DELETE -> {
                String taskNum = parts[1];
                return new DeleteCommand(new String[]{taskNum});
            }
            case TODO -> {
                return new AddCommand(new String[]{TODO, parts[1]});
            }
            case DEADLINE -> {
                String description = parts[1].split(" /by ")[0];
                String by = parts[1].split(" /by ")[1];
                return new AddCommand(new String[]{DEADLINE, description, by});
            }
            case EVENT -> {
                String description = parts[1].split(" /from ")[0];
                String from = parts[1].split(" /from ")[1].split(" /to ")[0];
                String to = parts[1].split(" /to ")[1];
                return new AddCommand(new String[]{EVENT, description, from, to});
            }
            case FIND -> {
                String keyword = parts[1];
                return new FindCommand(new String[]{keyword});
            }
            case UNDO -> {
                return new UndoCommand();
            }
            default -> throw new DukeException("Warning ??? : Wrong action.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Warning ??? : Missing command input.");
        }
    }

}
