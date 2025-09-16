package abang.parser;

import abang.command.AddCommand;
import abang.command.ClearCommand;
import abang.command.Command;
import abang.command.DeleteCommand;
import abang.command.ExitCommand;
import abang.command.FindCommand;
import abang.command.ListCommand;
import abang.command.MarkCommand;
import abang.command.TagCommand;
import abang.command.UnmarkCommand;
import abang.exception.AbangException;
import abang.task.Deadline;
import abang.task.Event;
import abang.task.Task;
import abang.task.ToDo;

public class Parser {

    public static Command parse(String input) throws AbangException {
        assert input != null : "input must not be null";

        if (input.equals("clear")) {
            return new ClearCommand();
        }
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.isEmpty()) {
            throw new AbangException("Hey! You typed nothing. Please enter a command.");
        }

        String[] tokens = input.split(" ", 2);
        assert tokens.length >= 1 : "split must produce at least one token";

        if (tokens.length < 2) {
            throw new AbangException("Please key in valid command description");
        }

        String cmd = tokens[0];
        assert cmd != null && !cmd.isBlank() : "command word must be non-blank";

        switch (cmd) {
            case "delete":
                return new DeleteCommand(tokens);
            case "mark":
                return new MarkCommand(tokens);
            case "unmark":
                return new UnmarkCommand(tokens);
            case "find":
                return new FindCommand(tokens);
            case "tag":
                return new TagCommand(tokens);
            case "todo": {
                String description = tokens[1];
                if (description.trim().isEmpty()) {
                    throw new AbangException("OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new ToDo(description);
                return new AddCommand(task);
            }
            case "deadline": {
                String description = tokens[1];
                if (description.trim().isEmpty()) {
                    throw new AbangException("Please key in valid Deadline Task description");
                }
                String[] details = description.split("/by", 2);
                if (details.length != 2) {
                    throw new AbangException("Please key in valid Deadline Task description (use '/by <time>')");
                }
                if (details[0].trim().isEmpty()) {
                    throw new AbangException("Deadline name cannot be empty");
                }
                if (details[1].trim().isEmpty()) {
                    throw new AbangException("Deadline time cannot be empty");
                }
                String name = details[0].trim();
                String by = details[1].trim();
                Task task = new Deadline(name, by);
                return new AddCommand(task);
            }
            case "event": {
                String description = tokens[1];
                if (description.trim().isEmpty()) {
                    throw new AbangException("Please key in valid Event Task description");
                }
                String[] details = description.split("/from", 2);
                if (details.length != 2) {
                    throw new AbangException("Please key in valid Event Task description (use '/from <start> /to <end>')");
                }
                if (details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
                    throw new AbangException("Please key in valid Event Task description");
                }
                String name = details[0].trim();
                String[] timing = details[1].split("/to", 2);
                if (timing.length != 2) {
                    throw new AbangException("Please key in valid Event timings (missing '/to <end>')");
                }
                if (timing[0].trim().isEmpty() || timing[1].trim().isEmpty()) {
                    throw new AbangException("Please key in valid Event timings");
                }
                String start = timing[0].trim();
                String end = timing[1].trim();
                Task task = new Event(name, start, end);
                return new AddCommand(task);
            }
            default:
                throw new AbangException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
