package mel.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Objects;
import java.util.regex.Pattern;

import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.Deadline;
import mel.tasks.Event;
import mel.tasks.TaskList;
import mel.tasks.Todo;


/**
 * Represents the different types of task commands as one class such as
 * deadline, todo and event, written with the help of ChatGPT as part of A-AiAssisted
 */
public class TaskCommand extends Command {

    private static final String TODO_CODE     = "T";
    private static final String DEADLINE_CODE = "D";
    private static final String EVENT_CODE    = "E";

    private static final String BY_DELIM    = "/by";
    private static final String FROM_DELIM  = "/from";
    private static final String TO_DELIM    = "/to";

    private String taskCode;
    private String argument;



    /**
     * Returns the corresponding task as a Command.
     * Throws an exception when the argument for the corresponding task is incorrect.
     * @param argument
     * @param taskCode
     * @throws MelException
     */
    public TaskCommand(String argument, String taskCode) throws MelException {
        this.argument = argument;
        this.taskCode = taskCode;

        switch (taskCode) {
            case TODO_CODE -> validateTodo(this.argument);
            case DEADLINE_CODE -> validateDeadline(this.argument);
            case EVENT_CODE -> validateEvent(this.argument);
            default -> throw new MelException("Failed to make a task");
        }

    }


    private static void validateTodo(String arg) throws MelException {
        if (isBlank(arg)) {
            throw new MelException.NoArgumentFoundException("todo");
        }
    }

    private static void validateDeadline(String arg) throws MelException {
        String[] descAndTime = split2(arg, BY_DELIM);
        requireTwoNonBlankParts(descAndTime, "deadline");
    }

    private static void validateEvent(String arg) throws MelException {
        String[] descAndFrom = split2(arg, FROM_DELIM);
        requireTwoNonBlankParts(descAndFrom, "event");

        String[] fromAndTo = split2(descAndFrom[1], TO_DELIM);
        requireTwoNonBlankParts(fromAndTo, "event");
    }

    private static void requireTwoNonBlankParts(String[] parts, String typeForError) throws MelException {
        if (parts.length < 2 || isBlank(parts[0]) || isBlank(parts[1])) {
            throw new MelException.NoArgumentFoundException(typeForError);
        }
    }

    private static String[] split2(String input, String delimiter) throws MelException {
        String[] parts = input.split(Pattern.quote(delimiter), 2);
        if (parts.length < 2) {
            throw new MelException("Missing '" + delimiter + "' section.");
        }
        return parts;
    }

    private static String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private static String safe(String s) {
        return Objects.requireNonNullElse(s, "");
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }


        /**
         * Prints out the corresponding output for each task
         * @param tasks
         * @param ui
         * @param storage
         * @throws MelException
         */
        public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
            switch (taskCode) {
                case "T" -> {
                    ui.printOut(tasks.add(new Todo(argument.trim())));
                    // storage.save(tasks); // uncomment if you persist after each command
                }

                case "D" -> {
                    String[] descAndBy = split2(argument, "/by");
                    String desc = descAndBy[0].trim();
                    String byRaw = descAndBy[1].trim();

                    LocalDate by;
                    try {
                        by = LocalDate.parse(byRaw); // expects YYYY-MM-DD
                    } catch (DateTimeParseException e) {
                        throw new MelException("Incorrect date format! Use YYYY-MM-DD.");
                    }

                    ui.printOut(tasks.add(new Deadline(desc, by)));
                    // storage.save(tasks);
                }

                case "E" -> {
                    String[] descAndFrom = split2(argument, "/from");
                    String desc = descAndFrom[0].trim();

                    String[] fromAndTo = split2(descAndFrom[1], "/to");
                    String from = fromAndTo[0].trim();
                    String to   = fromAndTo[1].trim();

                    ui.printOut(tasks.add(new Event(desc, from, to)));
                    // storage.save(tasks);
                }

                default -> throw new MelException("Unsupported task code: " + taskCode);
            }
        }

}
