package bro.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bro.commands.ByeCommand;
import bro.commands.Command;
import bro.commands.CommandError;
import bro.commands.DeadlineCommand;
import bro.commands.DeleteCommand;
import bro.commands.EventCommand;
import bro.commands.FindCommand;
import bro.commands.ListCommand;
import bro.commands.MarkCommand;
import bro.commands.TasksOnCommand;
import bro.commands.TodoCommand;
import bro.commands.UnknownCommand;
import bro.commands.UnmarkCommand;

/**
 * Parses user input commands and extracts relevant data.
 */
public class Parser {
    /**
     * Parses the user input and extracts the command and its associated data.
     *
     * @param input The user input string.
     * @return An array of strings where the first element is the command and the
     *         subsequent elements are the associated data.
     */
    public Command getCommand(String input) {
        Command command;
        if (input.equals("bye")) {
            command = new ByeCommand();

        } else if (input.equals("list")) {
            command = new ListCommand();

        } else if (input.startsWith("mark")) {
            command = parseMarkCommand(input);

        } else if (input.startsWith("unmark")) {
            command = parseUnmarkCommand(input);

        } else if (input.startsWith("delete")) {
            command = parseDeleteCommand(input);

        } else if (input.startsWith("tasks on")) {
            command = parseTasksOnCommand(input);

        } else if (input.startsWith("todo")) {
            command = parseTodoCommand(input);

        } else if (input.startsWith("deadline")) {
            command = parseDeadlineCommand(input);

        } else if (input.startsWith("event")) {
            command = parseEventCommand(input);

        } else if (input.startsWith("find")) {
            command = parseFindCommand(input);

        } else {
            command = new UnknownCommand();
        }

        assert command != null : "command should not be null";
        return command;
    }

    private Command parseMarkCommand(String input) {
        String[] commandData = new String[] { "mark",
                input.replace("mark", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            return new CommandError("Hey bro, please provide a task number to mark!");
        }

        try {
            Command command = new MarkCommand(
                    Integer.parseInt(commandData[1]) - 1);
            return command;
        } catch (NumberFormatException e) {
            return new CommandError("Hey bro, please provide a valid task number to mark!");
        }
    }

    private Command parseUnmarkCommand(String input) {
        String[] commandData = new String[] { "unmark",
                input.replace("unmark", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            return new CommandError("Hey bro, please provide a task number to unmark!");
        }

        try {
            Command command = new UnmarkCommand(
                    Integer.parseInt(commandData[1]) - 1);
            return command;
        } catch (NumberFormatException e) {
            return new CommandError("Hey bro, please provide a valid task number to unmark!");
        }
    }

    private Command parseDeleteCommand(String input) {
        String[] commandData = new String[] { "delete",
                input.replace("delete", "")
                        .stripLeading()
        };
        if (commandData.length < 2 || commandData[1].isBlank()) {
            return new CommandError("Hey bro, please provide a task number to delete!");
        }

        try {
            Command command = new DeleteCommand(
                    Integer.parseInt(commandData[1]) - 1);
            return command;
        } catch (NumberFormatException e) {
            return new CommandError("Hey bro, please provide a valid task number to delete!");
        }
    }

    private Command parseTasksOnCommand(String input) {
        String[] commandData = new String[] { "tasks on",
                input.replace("tasks on", "")
                        .stripLeading()
        };
        if (!dateTimeIsValid(commandData[1] + " 0000")) {
            return new CommandError("Hey bro, please provide a date in the format: d/M/yyyy");
        }
        Command command = new TasksOnCommand(commandData[1]);
        return command;
    }

    private Command parseTodoCommand(String input) {
        input = input.replace("todo", "").stripLeading();
        if (input.isBlank()) {
            return new CommandError("Hey bro, please provide a description for the todo!");
        }
        Command command = new TodoCommand(input);
        return command;
    }

    private Command parseDeadlineCommand(String input) {
        String[] inputParts = input.replace("deadline", "")
                .stripLeading()
                .split(" /by ");
        if (inputParts[0].isBlank()) {
            return new CommandError("Hey bro, please provide a description for the deadline!");
        } else if (inputParts.length < 2) {
            return new CommandError("Hey bro, please provide a deadline!");
        } else if (!dateTimeIsValid(inputParts[1])) {
            return new CommandError("Hey bro, please provide a deadline in the format: d/M/yyyy HHmm");
        }
        Command command = new DeadlineCommand(inputParts[0], inputParts[1]);
        return command;
    }

    private Command parseEventCommand(String input) {
        String[] inputParts = input.replace("event", "")
                .stripLeading()
                .split(" /from ");
        if (inputParts[0].isBlank()) {
            return new CommandError("Hey bro, please provide a description for the event!");
        } else if (inputParts.length < 2) {
            return new CommandError("Hey bro, please provide an event time range!");
        }

        String[] fromTo = inputParts[1].split(" /to ");
        if (fromTo.length < 2) {
            return new CommandError("Hey bro, please provide both start and end times for the event!");
        } else if (!dateTimeIsValid(fromTo[0]) || !dateTimeIsValid(fromTo[1])) {
            return new CommandError("Hey bro, please provide event times in the format: d/M/yyyy HHmm");
        }
        Command command = new EventCommand(inputParts[0], fromTo[0], fromTo[1]);
        return command;
    }

    private Command parseFindCommand(String input) {
        String keyword = input.replace("find", "").stripLeading();
        if (keyword.isBlank()) {
            return new CommandError("Hey bro, please provide a keyword to find!");
        }
        Command command = new FindCommand(keyword);
        return command;
    }

    private boolean dateTimeIsValid(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
