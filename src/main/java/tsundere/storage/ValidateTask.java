package tsundere.storage;

import static tsundere.Config.TASK_DEADLINE;
import static tsundere.Config.TASK_EVENT;
import static tsundere.Config.TASK_TODO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tsundere.TsundereException;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TodoTask;

/**
 *
 */
public class ValidateTask {
    /**
     * Validates that the String format stored in the text database is correct,
     * and creates the new corresponding Task.
     * @param task The text String containing information about the task
     * @return new Task
     * @throws TsundereStorageDataFormatException If text format does not comply with standards.
     */
    public static Task validateTask(String task) throws TsundereException {
        String[] params = task.split(",");
        if (params.length == 0) {
            return null;
        } else if (params[0].equals(TASK_TODO)) {
            return validateTodo(params);
        } else if (params[0].equals(TASK_DEADLINE)) {
            return validateDeadline(params);
        } else if (params[0].equals(TASK_EVENT)) {
            return validateEvent(params);
        }
        return null;
    }

    /**
     * Validates that the string format should be `todo,[checked],[name]`
     * @param task The columns of data, split by ','.
     * @return Task
     */
    private static Task validateTodo(String[] task) throws TsundereException {
        if (task.length != 3) {
            throw new TsundereStorageDataFormatException();
        }
        String checked = task[1];
        String name = task[2];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new TsundereStorageDataFormatException();
        }

        Task t = new TodoTask(name);
        if (checked.equals("T")) {
            t.markDone();
        }
        return t;
    }

    /**
     * Validates that the string format should be `deadline,[checked],[name],[byDate]`
     * @param task The columns of data, split by ','.
     * @return Task
     */
    private static Task validateDeadline(String[] task) throws TsundereException {
        if (task.length != 4) {
            throw new TsundereStorageDataFormatException();
        }
        String checked = task[1];
        String name = task[2];
        String by = task[3];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new TsundereStorageDataFormatException();
        }

        LocalDateTime byDate;
        try {
            byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            throw new TsundereStorageDataFormatException();
        }

        Task t = new DeadlineTask(name, byDate);
        if (checked.equals("T")) {
            t.markDone();
        }
        return t;
    }

    /**
     * Validates that the string format should be `event,[checked],[name],[startDate],[endDate]`
     * @param task The columns of data, split by ','.
     * @return Task
     */
    private static Task validateEvent(String[] task) throws TsundereException {
        if (task.length != 5) {
            throw new TsundereStorageDataFormatException();
        }
        String checked = task[1];
        String name = task[2];
        String start = task[3];
        String end = task[4];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new TsundereStorageDataFormatException();
        }

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            throw new TsundereStorageDataFormatException();
        }
        Task t = new EventTask(name, startDate, endDate);
        if (checked.equals("T")) {
            t.markDone();
        }

        return t;
    }
}
