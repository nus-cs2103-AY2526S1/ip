package parser;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.HelpCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.MarkCommand;
import command.NoteCommand;
import command.ToDoCommand;
import command.UnmarkCommand;


public class InputParser {
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");
    public static final Pattern FIND_ARGS_FORMAT = Pattern.compile("(?<keyword>.+)");
    public static final Pattern NOTE_ARGS_FORMAT =
            Pattern.compile("(?<targetIndex>\\d+) /content (?<noteContent>.+)");
    public static final Pattern TODO_ARGS_FORMAT =
            Pattern.compile("(?<description>.+)");
    public static final Pattern DEADLINE_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+) /by (?<time>.+)");
    public static final Pattern EVENT_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+) /from (?<startTime>.+) /to (?<endTime>.+)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + HelpCommand.MESSAGE_USAGE);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case NoteCommand.COMMAND_WORD:
            return prepareNote(arguments);

        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(arguments);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new HelpCommand();
        }
    }

    private static Command prepareMark(String args) {
        try {
            final int taskIndex = parseArgsAsDisplayedIndex(args) - 1;
            return new MarkCommand(taskIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + MarkCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("The task index provided is invalid.\n"
                    + MarkCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareUnmark(String args) {
        try {
            final int taskIndex = parseArgsAsDisplayedIndex(args) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + UnmarkCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("The task index provided is invalid.\n"
                    + UnmarkCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareDelete(String args) {
        try {
            final int taskIndex = parseArgsAsDisplayedIndex(args) - 1;
            return new DeleteCommand(taskIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + DeleteCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("The task index provided is invalid.\n"
                    + DeleteCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareFind(String args) {
        final Matcher matcher = FIND_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + FindCommand.MESSAGE_USAGE);
        }
        return new FindCommand(matcher.group("keyword"));
    }

    private static Command prepareNote(String args) {
        final Matcher matcher = NOTE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + NoteCommand.MESSAGE_USAGE);
        }
        try {
            final int taskIndex = Integer.parseInt(matcher.group("targetIndex")) - 1;
            final String noteContent = matcher.group("noteContent");
            return new NoteCommand(taskIndex, noteContent);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("The task index provided is invalid.\n"
                    + NoteCommand.MESSAGE_USAGE);
        }
    }

    private static Command prepareToDo(String args) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + ToDoCommand.MESSAGE_USAGE);
        }
        return new ToDoCommand(matcher.group("description"));
    }

    private static Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + DeadlineCommand.MESSAGE_USAGE);
        }
        try {
            LocalDate by = LocalDate.parse(matcher.group("time"));
            return new DeadlineCommand(matcher.group("description"), by);
        } catch (DateTimeParseException pe) {
            return new IncorrectCommand("This is an invalid date format.\n"
                    + "Input date as YYYY-MM-DD");
        }
    }

    private static Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("This is an invalid command format.\n"
                    + EventCommand.MESSAGE_USAGE);
        }
        try {
            LocalDate from = LocalDate.parse(matcher.group("startTime"));
            LocalDate to = LocalDate.parse(matcher.group("endTime"));
            return new EventCommand(matcher.group("description"), from, to);
        } catch (DateTimeParseException pe) {
            return new IncorrectCommand("This is an invalid date format.\n"
                    + "Input date as YYYY-MM-DD");
        }
    }

    private static int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse.", 0);
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }
}
