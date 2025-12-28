package meeseeks.parser;

import meeseeks.command.*;
import meeseeks.task.Deadline;
import meeseeks.task.Event;
import meeseeks.task.RecurringTask;
import meeseeks.task.RecurrenceFrequency;
import meeseeks.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";
    private static final String FIND_COMMAND = "find ";
    private static final String MARK_COMMAND = "mark ";
    private static final String UNMARK_COMMAND = "unmark ";
    private static final String RECURRING_COMMAND = "recurring";
    private static final String OVERDUE_COMMAND = "overdue";
    
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_FROM_SEPARATOR = " /from ";
    private static final String EVENT_TO_SEPARATOR = " /to ";
    private static final String RECURRING_FREQUENCY_SEPARATOR = " /every ";
    private static final String RECURRING_DUE_SEPARATOR = " /due ";
    private static final String FILE_SEPARATOR = " \\| ";
    
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int DELETE_PREFIX_LENGTH = 7;
    private static final int FIND_PREFIX_LENGTH = 5;
    private static final int MARK_PREFIX_LENGTH = 5;
    private static final int UNMARK_PREFIX_LENGTH = 7;
    
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";
    private static final String RECURRING_TYPE = "R";
    private static final String DONE_VALUE = "1";
    private static final String NOT_DONE_VALUE = "0";
    
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final String RECURRING_FORMAT_ERROR = "Invalid recurring command format. Use: recurring <description> /every <frequency> /due <date>";
    
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "Command string cannot be null";
        assert !fullCommand.trim().isEmpty() : "Command string cannot be empty";
        
        if (fullCommand.startsWith(TODO_COMMAND)) {
            return new AddCommand(fullCommand.substring(TODO_PREFIX_LENGTH), TODO_COMMAND);
        } else if (fullCommand.startsWith(DEADLINE_COMMAND)) {
            String[] parts = fullCommand.split(DEADLINE_SEPARATOR, 2);
            assert parts.length == 2 : "Deadline command must have both description and time";
            return new AddCommand(parts[0].substring(DEADLINE_PREFIX_LENGTH), DEADLINE_COMMAND, parts[1]);
        } else if (fullCommand.startsWith(EVENT_COMMAND)) {
            String[] parts = fullCommand.split(EVENT_FROM_SEPARATOR + "|" + EVENT_TO_SEPARATOR);
            assert parts.length == 3 : "Event command must have description, from time, and to time";
            return new AddCommand(parts[0].substring(EVENT_PREFIX_LENGTH), EVENT_COMMAND, parts[1], parts[2]);
        } else if (fullCommand.equals(LIST_COMMAND)) {
            return new ListCommand();
        } else if (fullCommand.startsWith(DELETE_COMMAND)) {
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(DELETE_PREFIX_LENGTH)) - 1);
        } else if (fullCommand.equals(BYE_COMMAND)) {
            return new ExitCommand();
        } else if (fullCommand.startsWith(FIND_COMMAND)) {
            return new FindCommand(fullCommand.substring(FIND_PREFIX_LENGTH));
        } else if (fullCommand.startsWith(MARK_COMMAND)) {
            return new MarkCommand(Integer.parseInt(fullCommand.substring(MARK_PREFIX_LENGTH)) - 1);
        } else if (fullCommand.startsWith(UNMARK_COMMAND)) {
            return new UnmarkCommand(Integer.parseInt(fullCommand.substring(UNMARK_PREFIX_LENGTH)) - 1);
        } else if (fullCommand.equals("stats")) {
            return new StatsCommand();
        } else if (fullCommand.startsWith(RECURRING_COMMAND)) {
            return parseRecurringCommand(fullCommand);
        } else if (fullCommand.equals(OVERDUE_COMMAND)) {
            return new ListOverdueCommand();
        }
        return new UnknownCommand();
    }

    /**
     * Parses a recurring task command.
     * Format: recurring <description> /every <frequency> /due <date>
     * 
     * @param fullCommand the full command string
     * @return the AddRecurringCommand
     * @throws IllegalArgumentException if the command format is invalid
     */
    private static Command parseRecurringCommand(String fullCommand) {
        try {
            // Remove "recurring " prefix
            String commandBody = fullCommand.substring(RECURRING_COMMAND.length() + 1);
            
            // Split by frequency separator
            String[] parts = commandBody.split(RECURRING_FREQUENCY_SEPARATOR, 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException(RECURRING_FORMAT_ERROR);
            }
            
            String description = parts[0].trim();
            
            // Split the remaining part by due separator
            String[] frequencyAndDue = parts[1].split(RECURRING_DUE_SEPARATOR, 2);
            if (frequencyAndDue.length != 2) {
                throw new IllegalArgumentException(RECURRING_FORMAT_ERROR);
            }
            
            String frequencyStr = frequencyAndDue[0].trim();
            String dueDateStr = frequencyAndDue[1].trim();
            
            RecurrenceFrequency frequency = RecurrenceFrequency.fromString(frequencyStr);
            LocalDateTime dueDate = LocalDateTime.parse(dueDateStr, DATE_TIME_FORMATTER);
            
            return new AddRecurringCommand(description, frequency, dueDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use: d/M/yyyy HHmm (e.g., 25/12/2023 1400)");
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing recurring command: " + e.getMessage());
        }
    }


    /**
     * parse the tasks from file
     */
    public static Task parseTaskFromFile(String line) {
        assert line != null : "Line cannot be null";
        assert !line.trim().isEmpty() : "Line cannot be empty";
        
        try {
            String[] parts = line.split(FILE_SEPARATOR);
            if (parts.length < 3) {
                throw new IllegalArgumentException("Invalid format");
            }

            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals(DONE_VALUE);
            String description = parts[2].trim();

            Task task;

            switch (type) {
                case TODO_TYPE:
                    task = new Task(description);
                    break;
                case DEADLINE_TYPE:
                    if (parts.length < 4) throw new IllegalArgumentException("Deadline missing time");
                    LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                    task = new Deadline(description, by);
                    break;
                case EVENT_TYPE:
                    if (parts.length < 5) throw new IllegalArgumentException("Event missing times");
                    task = new Event(description, parts[3].trim(), parts[4].trim());
                    break;
                case RECURRING_TYPE:
                    if (parts.length < 5) throw new IllegalArgumentException("Recurring task missing frequency and due date");
                    RecurrenceFrequency frequency = RecurrenceFrequency.fromString(parts[3].trim());
                    LocalDateTime nextDueDate = LocalDateTime.parse(parts[4].trim());
                    LocalDateTime lastCompletedDate = null;
                    if (parts.length > 5 && !parts[5].trim().isEmpty()) {
                        lastCompletedDate = LocalDateTime.parse(parts[5].trim());
                    }
                    task = new RecurringTask(description, frequency, nextDueDate, lastCompletedDate, isDone);
                    // Don't mark as done again since RecurringTask constructor handles it
                    isDone = false;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type");
            }

            if (isDone) {
                task.markAsDone();
            }

            return task;
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }
}
