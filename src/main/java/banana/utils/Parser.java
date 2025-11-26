package banana.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import banana.command.AddCommand;
import banana.command.Command;
import banana.command.DeleteCommand;
import banana.command.ExitCommand;
import banana.command.FindCommand;
import banana.command.FindKeywordCommand;
import banana.command.ListCommand;
import banana.command.MarkCommand;
import banana.command.SortCommand;
import banana.command.UnmarkCommand;
import banana.exceptions.BananaException;
import banana.task.Deadline;
import banana.task.Event;
import banana.task.Task;
import banana.task.ToDo;


/**
 * Parses user input and executes corresponding commands.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return The Command object corresponding to the user input.
     * @throws BananaException If the input is invalid or cannot be parsed.
     * @throws IOException   If there is an error during command execution.
     */
    public static Command parse(String input) throws BananaException, IOException {
        assert input != null && !input.trim().isEmpty() : "Input cannot be null or empty";
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "list":
            if (parts.length > 1) {
                throw new BananaException("The 'list' command should not have extra arguments.");
            }
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case "mark":
            if (parts.length < 2) {
                throw new BananaException("Please specify which task to mark.");
            }
            int markIndex = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            if (parts.length < 2) {
                throw new BananaException("Please specify which task to unmark.");
            }
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new BananaException("The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(parts[1].trim()));

        case "deadline":
            if (parts.length < 2) {
                throw new BananaException("The description of a deadline cannot be empty.");
            }
            String[] deadlineParts = parts[1].split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new BananaException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
            }
            String dDesc = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();
            if (dDesc.isEmpty()) {
                throw new BananaException("The description of a deadline cannot be empty.");
            }
            if (by.isEmpty()) {
                throw new BananaException("The deadline date/time cannot be empty.");
            }
            try {
                LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new BananaException("Enter correct date time format yyyy-MM-dd HHmm.");
            }
            return new AddCommand(new Deadline(dDesc, by));

        case "event":
            if (parts.length < 2) {
                throw new BananaException("The description of an event cannot be empty.");
            }
            String[] eventParts = parts[1].split("/from", 2);
            if (eventParts.length < 2) {
                throw new BananaException("Invalid event format! Use: event <desc> /from <start> /to <end>");
            }
            String eDesc = eventParts[0].trim();
            if (eDesc.isEmpty()) {
                throw new BananaException("The description of an event cannot be empty.");
            }
            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2) {
                throw new BananaException("Missing /to in event command.");
            }
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();

            try {
                LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new BananaException("Enter correct date time format yyyy-MM-dd HHmm.");
            }
            return new AddCommand(new Event(eDesc, timeParts[0].trim(), timeParts[1].trim()));

        case "delete":
            if (parts.length < 2) {
                throw new BananaException("Please specify which task to delete.");
            }
            int deleteIndex = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(deleteIndex);

        case "on":
            if (parts.length != 2) {
                throw new BananaException("Please specify a date in yyyy-MM-dd format.");
            }
            String dateStr = parts[1].trim();
            try {
                LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                throw new BananaException("Invalid format! Please enter only a date in yyyy-MM-dd format.");
            }
            return new FindCommand(dateStr);

        case "find":
            if (parts.length < 2) {
                throw new BananaException("Please provide a keyword to search.");
            }
            return new FindKeywordCommand(parts[1].trim());

        case "sort":
            return new SortCommand("date");

        default:
            throw new BananaException("I'm sorry, but I don't know what that means :-(");
        }

    }
    /**
     * Parses a task from its string representation in storage format.
     *
     * @param task The string representation of the task from storage.
     * @return The corresponding Task object.
     * @throws BananaException If the task format is invalid or unknown.
     */
    public static Task parseTask(String task) throws BananaException {
        String[] words = task.split(" \\| ");
        String taskType = words[0];
        switch (taskType) {
        case "T":
            boolean isDone = words[1].equals("1");
            String description = words[2];
            ToDo todo = new ToDo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            isDone = words[1].equals("1");
            description = words[2];
            String by = words[3];
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            isDone = words[1].equals("1");
            description = words[2];
            String[] timeParts = words[3].split(" - ");
            if (timeParts.length < 2) {
                throw new BananaException("Invalid event format in storage!");
            }
            String from = timeParts[0];
            String to = timeParts[1];
            Event event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new BananaException("Unknown task type in storage!");
        }
    }
}
