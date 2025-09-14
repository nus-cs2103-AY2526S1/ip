package tsundere.parser;

import static tsundere.command.InvalidFormatCommand.Format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import tsundere.command.AbstractCommand;
import tsundere.command.AddTaskCommand;
import tsundere.command.ByeCommand;
import tsundere.command.DeleteCommand;
import tsundere.command.EchoCommand;
import tsundere.command.FindCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.command.ListCommand;
import tsundere.command.MarkCommand;
import tsundere.command.UndoCommand;
import tsundere.command.UnmarkCommand;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TodoTask;



/**
 * Parses the user input into an executable command.
 */
public class CommandParser {
    /**
     * Parses the command based on the first word.
     *
     * @param fullCommand The user input to be parsed.
     * @return A runnable command that effects storage, ui, and tasks.
     */
    public static AbstractCommand parse(String fullCommand) {
        String[] words = fullCommand.split(" ");

        if (words.length == 0 || words[0].isBlank() || words[0].equals("help")) {
            return new InvalidFormatCommand(Format.HELP);
        }

        String command = words[0];
        return switch (command) {
        case "todo" -> parseTodo(words);
        case "deadline" -> parseDeadline(words);
        case "event" -> parseEvent(words);
        case "list" -> new ListCommand();
        case "find" -> parseFind(words);
        case "mark" -> parseMark(words);
        case "unmark" -> parseUnmark(words);
        case "delete" -> parseDelete(words);
        case "undo" -> new UndoCommand();
        case "bye" -> new ByeCommand();
        default -> new EchoCommand(fullCommand);
        };
    }

    /**
     * Verifies that the user input follows the format `todo [name]`.
     *
     * @param words The full user input split by spaces.
     * @return {@link AddTaskCommand} if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseTodo(String[] words) {
        if (words.length < 2) {
            return new InvalidFormatCommand(Format.TODO);
        }

        String name = Arrays.stream(words).skip(2).reduce(words[1], (prev, next) -> prev + " " + next);

        return new AddTaskCommand(new TodoTask(name));
    }

    /**
     * Verifies that the user input follows the format `event [name] /from [date] /to [date]`,
     * where date is of the format `yyyy-MM-dd'T'HH:mm`.
     *
     * @param words The full user input split by spaces.
     * @return AddTaskCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseEvent(String[] words) {
        if (words.length < 6) {
            return new InvalidFormatCommand(Format.EVENT);
        }
        StringBuilder name = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        enum Params { NAME, FROM, TO }
        Params type = Params.NAME;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                switch (type) {
                case NAME -> type = Params.FROM;
                case FROM -> {
                    from.append(words[i]);
                    from.append(' ');
                }
                case TO -> {
                    to.append(words[i]);
                    to.append(' ');
                }
                default -> {
                    assert false : "parseEvent error";
                }
                }
            } else if (words[i].equals("/to")) {
                switch (type) {
                case NAME, FROM -> type = Params.TO;
                case TO -> {
                    to.append(words[i]);
                    to.append(' ');
                }
                default -> {
                    assert false : "parseEvent error";
                }
                }
            } else {
                switch (type) {
                case NAME -> {
                    name.append(words[i]);
                    name.append(' ');
                }
                case FROM -> {
                    from.append(words[i]);
                    from.append(' ');
                }
                case TO -> {
                    to.append(words[i]);
                    to.append(' ');
                }
                default -> {
                    assert false : "parseEvent error";
                }
                }
            }
        }
        if (name.isEmpty() || from.toString().split(" ").length != 1
                || to.toString().split(" ").length != 1) {
            return new InvalidFormatCommand(InvalidFormatCommand.Format.EVENT);
        }

        name.deleteCharAt(name.length() - 1);
        from.deleteCharAt(from.length() - 1);
        to.deleteCharAt(to.length() - 1);

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            if (!fromDate.isBefore(toDate)) {
                return new InvalidFormatCommand(Format.EVENT_DATE);
            }
        } catch (DateTimeParseException e) {
            return new InvalidFormatCommand(Format.EVENT);
        }

        return new AddTaskCommand(new EventTask(name.toString(), fromDate, toDate));
    }

    /**
     * Verifies that the user input follows the format `deadline [name] /by [date]`,
     * where date is of the format `yyyy-MM-dd'T'HH:mm`.
     *
     * @param words The full user input split by spaces.
     * @return AddTaskCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseDeadline(String[] words) {
        if (words.length < 4) {
            return new InvalidFormatCommand(Format.DEADLINE);
        }
        StringBuilder name = new StringBuilder();
        StringBuilder by = new StringBuilder();

        enum Param { NAME, BY }
        ;
        Param type = Param.NAME;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                switch (type) {
                case NAME -> type = Param.BY;
                case BY -> {
                    by.append(words[i]);
                }
                default -> {
                    assert false : "parseDeadline error";
                }
                }
            } else {
                switch (type) {
                case NAME -> {
                    name.append(words[i]);
                    name.append(' ');
                }
                case BY -> {
                    by.append(words[i]);
                }
                default -> {
                    assert false : "parseDeadline error";
                }
                }
            }
        }

        if (name.isEmpty() || by.isEmpty() || by.toString().split(" ").length != 1) {
            return new InvalidFormatCommand(Format.DEADLINE);
        }

        name.deleteCharAt(name.length() - 1);

        LocalDateTime byDate;
        try {
            byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            return new InvalidFormatCommand(Format.DEADLINE);
        }


        return new AddTaskCommand(new DeadlineTask(name.toString(), byDate));
    }

    /**
     * Verifies that the user input follows the format `find [substring to match]`,
     * @param words The full user input split by spaces.
     * @return FindCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseFind(String[] words) {
        if (words.length < 2) {
            return new InvalidFormatCommand(Format.FIND);
        }
        return new FindCommand(Arrays.stream(words).skip(2)
                .reduce(words[1], (prev, next) -> prev + ' ' + next));
    }

    /**
     * Verifies that the user input follows the format `mark [index]`,
     * @param words The full user input split by spaces.
     * @return MarkCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseMark(String[] words) {
        try {
            if (words.length < 2) {
                return new InvalidFormatCommand(Format.MARK);
            }
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand(Format.MARK);
        }
    }

    /**
     * Verifies that the user input follows the format `unmark [index]`,
     * @param words The full user input split by spaces.
     * @return UnmarkCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseUnmark(String[] words) {
        try {
            if (words.length < 2) {
                return new InvalidFormatCommand(Format.UNMARK);
            }
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand(Format.UNMARK);
        }
    }

    /**
     * Verifies that the user input follows the format `delete [index]`,
     * @param words The full user input split by spaces.
     * @return DeleteCommand if user input is valid, else InvalidFormatCommand
     */
    public static AbstractCommand parseDelete(String[] words) {
        try {
            if (words.length < 2) {
                return new InvalidFormatCommand(Format.DELETE);
            }
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException e) {
            return new InvalidFormatCommand(Format.DELETE);
        }
    }
}
