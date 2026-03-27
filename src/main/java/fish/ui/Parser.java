package fish.ui;

import fish.FishException;
import fish.command.AddCommand;
import fish.command.Command;
import fish.command.DeleteCommand;
import fish.command.ExitCommand;
import fish.command.FindCommand;
import fish.command.ListCommand;
import fish.command.MarkCommand;
import fish.command.SortDeadlineCommand;
import fish.command.UnmarkCommand;
import fish.task.TaskList;

public class Parser {
    public static Command parse(String input, TaskList tasks) throws FishException {
        String s = input.trim();
        if (s.equals("bye")) {
            return new ExitCommand();
        }
        if (s.equals("list")) {
            return new ListCommand();
        }

        if (s.startsWith("mark ")) {
            int n = parseIndex(s.substring(5), tasks);
            return new MarkCommand(n);
        }
        if (s.startsWith("unmark ")) {
            int n = parseIndex(s.substring(7), tasks);
            return new UnmarkCommand(n);
        }
        if (s.startsWith("delete ")) {
            int n = parseIndex(s.substring(7), tasks);
            return new DeleteCommand(n);
        }

        if (s.startsWith("todo ")) {
            return new AddCommand("todo", s.substring(5).trim());
        }
        if (s.startsWith("deadline ")) {
            String body = s.substring(9).trim();
            String[] parts = body.split("/by", 2);
            if (parts.length < 2) {
                throw new FishException("The correct format should be: deadline <desc> /by <time>");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            return new AddCommand("deadline", description, by);
        }
        if (s.startsWith("event ")) {
            String body = s.substring(6).trim();
            String[] fromSplit = body.split("/from", 2);
            if (fromSplit.length < 2) {
                throw new FishException("The correct format should be: event <desc> /from <start> /to <end>");
            }
            String description = fromSplit[0].trim();
            String[] toSplit = fromSplit[1].split("/to", 2);
            if (toSplit.length < 2) {
                throw new FishException("The correct format should be: event <desc> /from <start> /to <end>");
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            return new AddCommand("event", description, from, to);
        }
        if (s.startsWith("find ")) {
            String body = s.substring(5);
            return new FindCommand(body);
        }

        if (s.equals("sort deadlines")) {
            return new SortDeadlineCommand();
        }

        throw new FishException("Sorry I need a valid command");
    }

    private static int parseIndex(String s, TaskList tasks) throws FishException {
        try {
            int k = Integer.parseInt(s.trim());
            if (k <= 0 || k > tasks.size()) {
                throw new NumberFormatException();
            }
            return k - 1;
        } catch (NumberFormatException e) {
            throw new FishException("Please provide a valid task index");
        }
    }
}
