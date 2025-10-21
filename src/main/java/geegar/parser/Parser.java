package geegar.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import geegar.command.AddCommand;
import geegar.command.Command;
import geegar.command.DeleteCommand;
import geegar.command.ExitCommand;
import geegar.command.FindCommand;
import geegar.command.ListCommand;
import geegar.command.MarkCommand;
import geegar.command.ScheduleCommand;
import geegar.command.UnmarkCommand;
import geegar.command.UpdateCommand;
import geegar.exception.EmptyDescriptionException;
import geegar.exception.GeegarException;
import geegar.exception.InvalidFormatDeadlineException;
import geegar.exception.InvalidFormatEventException;
import geegar.exception.InvalidTaskNumberException;
import geegar.exception.UnknownCommandException;
import geegar.task.Deadline;
import geegar.task.Event;
import geegar.task.Task;
import geegar.task.Todo;

/**
 * Parses user input into an executable Command object based on their command / keyword used
 * This class handles the translation of raw user input strings into the corresponding command objects,
 * breaking down each segments into the identified parameter for command execution
 * Exceptions are thrown if the input format is invalid or incomplete.
 */
public class Parser {

    /**
     * Parses a full user input string and returns the corresponding Command Object
     *
     * @param fullCommand the raw input string entered by the user
     * @return the Command Object that corresponds to the user input
     * @throws GeegarException if input cannot be parsed into a valid command
     */
    public static Command parse(String fullCommand) throws GeegarException {
        if (fullCommand.trim().isEmpty()) {
            throw new UnknownCommandException("");
        }

        // split into: first word which is the command
        // second portion after first space will be the following arguments
        String[] parts = fullCommand.trim().split(" ", 2);
        // first word will always be the command
        String commandWord = parts[0].toLowerCase();
        // remaining are argumentsto be inputted
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (resolveCommand(commandWord)) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseTaskNumber(arguments));
            case "unmark":
                return new UnmarkCommand(parseTaskNumber(arguments));
            case "delete":
                return new DeleteCommand(parseTaskNumber(arguments));
            case "todo":
                return new AddCommand(parseTodo(arguments));
            case "deadline":
                return new AddCommand(parseDeadline(arguments));
            case "event":
                return new AddCommand(parseEvent(arguments));
            case "on":
                return new ScheduleCommand(parseDate(arguments));
            case "find":
                return new FindCommand(parseKeyword(arguments));
            case "update":
                return parseUpdateCommand(arguments);
            default:
                throw new UnknownCommandException(commandWord);
        }
    }

    /**
     * This code was suggested using ClaudeAI to improve command possibilities
     * Based on the command word, accepts various different types of input for command
     * word to the supposed command word
     * @param commandWord any accepted subtypes of actual command word
     * @return the actual command word
     */
    private static String resolveCommand(String commandWord) {
        String cmd = commandWord.toLowerCase();

        switch (cmd) {
            case "bye": case "exit": case "quit": return "bye";
            case "list": case "ls": return "list";
            case "mark": case "done": return "mark";
            case "unmark": case "undone": return "unmark";
            case "delete": case "del": case "remove": return "delete";
            case "todo": case "t": return "todo";
            case "deadline": case "d": return "deadline";
            case "event": case "e": return "event";
            case "find": case "search": return "find";
            case "update": case "edit": return "update";
            default: return cmd;
        }
    }

    // For methods with the following format: '<geegar.Command> <geegar.task.Task Number>'
    // mark, unmark, delete
    // (e.g. mark 1)

    /**
     * Parses a task number from user input
     * Expected format: "command" "task number"
     *
     * @param arguments the input string containing the task number
     * @return the task number as an integer
     * @throws GeegarException if the argument is empty or not a valid number
     */
    private static int parseTaskNumber(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new InvalidTaskNumberException("");
        }
        try {
            int taskNumber = Integer.parseInt(arguments.trim());
            if (taskNumber < 1) {
                throw new InvalidTaskNumberException("Task number must be positive!");
            }
            return taskNumber;

        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(arguments);
        }
    }

    /**
     * Parses a todo command argument into a geegar.task.Todo
     *
     * @param arguments the description of the todo task
     * @return Todo Object
     * @throws GeegarException if the description is empty
     */
    private static Task parseTodo(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }
        return new Todo(arguments.trim());
    }

    /**
     * Parses a deadline command argument into a geegar.task.Deadline
     * Expected format: "description /by d/M/yyyy HHmm"
     *
     * @param arguments the description and deadline string
     * @return Deadline Object
     * @throws GeegarException if the description or date format is invalid
     */
    private static Task parseDeadline(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        if (!arguments.contains(" /by ")) {
            throw new InvalidFormatDeadlineException();
        }

        String[] parts = arguments.split("/by ", 2);
        String description = parts[0].trim();
        String byInput = parts[1].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (byInput.isEmpty()) {
            throw new InvalidFormatDeadlineException();
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime by = LocalDateTime.parse(byInput, formatter);
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatDeadlineException();
        }
    }

    /**
     * Parses an event command arguemtn into a geegar.task.Event
     * Expected format: "event /from d/M/yyyy HHmm /to d/M/yyyy HHmm"
     *
     * @param arguments the description and time range string
     * @return Event Object
     * @throws GeegarException if the description or date format is invalid
     */
    // example: event meeting /from 2/12/2019 1800 /to 2/12/2019 1900
    private static Task parseEvent(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        if (!arguments.contains(" /from ")) {
            throw new InvalidFormatEventException();
        }

        String[] splitByFrom = arguments.split("/from ", 2);
        if (splitByFrom.length != 2 || !splitByFrom[1].contains(" /to ")) {
            throw new InvalidFormatEventException();
        }

        String[] splitByTo = splitByFrom[1].split("/to ", 2);
        if (splitByTo.length != 2) {
            throw new InvalidFormatEventException();
        }

        String description = splitByFrom[0].trim();
        String fromInput = splitByTo[0].trim();
        String toInput = splitByTo[1].trim();

        if (description.isEmpty() || fromInput.isEmpty() || toInput.isEmpty()) {
            throw new InvalidFormatEventException();
        }

        try {
            // date format should be in dd/M/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(fromInput, formatter);
            LocalDateTime to = LocalDateTime.parse(toInput, formatter);
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatEventException();
        }
    }

    /**
     * Parses a date string from an "on" command
     * Expected format: "d/M/yyyy"
     *
     * @param arguments the date string
     * @return the parsed localDate
     * @throws GeegarException if the date is missing or in an invalid format
     */
    private static LocalDate parseDate(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("Please provide a date in the format: dd/mm/yyyy");
        }

        try {
            // date format should be in dd/M/yyyy HHmm
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return LocalDate.parse(arguments.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new GeegarException("Invalid date format! type in dd/mm/yyyy dumbahh");
        }
    }

    private static String parseKeyword(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("find");
        }
        return arguments.trim();
    }

    private static Command parseUpdateCommand(String arguments) throws GeegarException {
        if (arguments.trim().isEmpty()) {
            throw new EmptyDescriptionException("update command requires task number and details!");
        }

        String[] parts = arguments.trim().split(" ", 2);

        int taskNumber;
        try {
            taskNumber = parseTaskNumber(parts[0].trim());
        } catch (InvalidTaskNumberException e) {
            throw new InvalidTaskNumberException(parts[0]);
        }

        if (parts.length < 2) {
            throw new GeegarException("update command requires task number and details!");
        }

        String updateDetails = parts[1];

        if (updateDetails.contains(" /from ") && updateDetails.contains(" /to ")) {
            return parseUpdateEvent(taskNumber, updateDetails);
        } else if (updateDetails.contains(" /by ")) {
            return parseUpdateDeadline(taskNumber, updateDetails);
        } else {
            return new UpdateCommand(taskNumber, updateDetails.trim());
        }
    }

    private static Command parseUpdateDeadline(int taskNumber, String updateDetails) throws GeegarException {
        String[] parts = updateDetails.split(" /by ", 2);
        String newDescription = parts[0].trim();
        String byInput = parts[1].trim();

        if (newDescription.isEmpty() || byInput.isEmpty()) {
            throw new GeegarException("Both description and deadline dates are required!");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime newBy = LocalDateTime.parse(byInput, formatter);
            return new UpdateCommand(taskNumber, newDescription, newBy);
        } catch (DateTimeParseException e) {
            throw new GeegarException("Invalid date format! type in dd/mm/yyyy dumbahh");
        }
    }

    private static Command parseUpdateEvent(int taskNumber, String updateDetails) throws GeegarException {
        String[] splitByFrom = updateDetails.split(" /from ", 2);
        if (splitByFrom.length != 2 || !splitByFrom[1].contains(" /to ")) {
            throw new GeegarException("Event update requires format: description /from date /to date");
        }

        String[] splitByTo = splitByFrom[1].split("/to ", 2);
        if (splitByTo.length != 2) {
            throw new GeegarException("Event update requires format: description /from date /to date");
        }

        String newDescription = splitByFrom[0].trim();
        String fromInput = splitByTo[0].trim();
        String toInput = splitByTo[1].trim();

        if (newDescription.isEmpty() || fromInput.isEmpty() || toInput.isEmpty()) {
            throw new GeegarException("Description, from date, and to date are all required!");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime newFrom = LocalDateTime.parse(fromInput, formatter);
            LocalDateTime newTo = LocalDateTime.parse(toInput, formatter);
            return new UpdateCommand(taskNumber, newDescription, newFrom, newTo);
        } catch (DateTimeParseException e) {
            throw new GeegarException("Invalid date format! Use d/M/yyyy HHmm");
        }
    }
}
