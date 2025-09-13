package hermione.validators;

import hermione.exceptions.DateUtilsException;
import hermione.exceptions.TaskValidationException;
import hermione.tasks.TaskType;
import hermione.utils.DateUtils;

/**
 * Validates the fields of a task based on its type.
 */
public class TaskValidator {

    private static final int TODO_FIELD_COUNT = 3;
    private static final int DEADLINE_FIELD_COUNT = 4;
    private static final int FIXED_DURATION_FIELD_COUNT = 4;
    private static final int EVENT_FIELD_COUNT = 5;

    /**
     * Validates the fields of a task based on its type.
     * The first field indicates the type of task:
     * - "T" for Todo
     * - "D" for Deadline
     * - "E" for Event
     * This method checks the number of fields and validates each field according to
     * the task type.
     *
     * @param fields An array of strings representing the fields of a task.
     */
    public void validateFields(String[] fields) {
        if (fields.length < 1) {
            throw new TaskValidationException("Invalid task format: " + String.join(",", fields));
        }

        String taskTypeCode = fields[0];
        TaskType taskType;
        try {
            taskType = TaskType.fromCode(taskTypeCode);
        } catch (IllegalArgumentException e) {
            throw new TaskValidationException("Invalid task type: " + taskTypeCode);
        }

        switch (taskType) {
            case TODO -> validateTodoFields(fields);
            case DEADLINE -> validateDeadlineFields(fields);
            case EVENT -> validateEventFields(fields);
            case FIXED_DURATION_TASK -> validateFixedDurationFields(fields);
            default -> throw new TaskValidationException("Unsupported task type: " + taskType);
        }
    }

    /* Helper functions to validate based on a task type */
    private void validateTodoFields(String[] fields) {
        if (fields.length != TODO_FIELD_COUNT) {
            throw new TaskValidationException("Invalid number of fields for Todo Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);
    }

    private void validateFixedDurationFields(String[] fields) {
        if (fields.length != FIXED_DURATION_FIELD_COUNT) {
            throw new TaskValidationException("Invalid number of fields for Fixed Duration Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);

        String durationStr = fields[3];
        if (durationStr.isBlank()) {
            throw new TaskValidationException("Duration cannot be empty");
        }
        try {
            int duration = Integer.parseInt(durationStr);
            if (duration <= 0) {
                throw new TaskValidationException("Duration must be a positive integer");
            }
        } catch (NumberFormatException e) {
            throw new TaskValidationException("Invalid duration format: " + durationStr);
        }
    }

    private void validateDeadlineFields(String[] fields) {
        if (fields.length != DEADLINE_FIELD_COUNT) {
            throw new TaskValidationException("Invalid number of fields for Deadline Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);

        String by = fields[3];
        validateBy(by);
    }

    private void validateEventFields(String[] fields) {
        if (fields.length != EVENT_FIELD_COUNT) {
            throw new TaskValidationException("Invalid number of fields for Event Task");
        }

        String isCompleted = fields[1];
        validateIsCompleted(isCompleted);

        String description = fields[2];
        validateDescription(description);

        String from = fields[3];
        validateFrom(from);

        String to = fields[4];
        validateTo(to);
    }

    /* Helper functions to validate based on the field type */
    private void validateDescription(String description) {
        if (description.isBlank()) {
            throw new TaskValidationException("Description cannot be empty");
        }
    }

    private void validateIsCompleted(String isCompleted) {
        if (!isCompleted.equals("0") && !isCompleted.equals("1")) {
            throw new TaskValidationException("Invalid value for isCompleted field: " + isCompleted);
        }
    }

    private void validateBy(String by) {
        if (by.isBlank()) {
            throw new TaskValidationException("By cannot be empty");
        }
        try {
            DateUtils.parseDateString(by);
        } catch (DateUtilsException e) {
            throw new TaskValidationException("Invalid date format for By field: " + by);
        }
    }

    private void validateFrom(String from) {
        if (from.isBlank()) {
            throw new TaskValidationException("From cannot be empty");
        }
        try {
            DateUtils.parseDateString(from);
        } catch (DateUtilsException e) {
            throw new TaskValidationException("Invalid date format for From field: " + from);
        }
    }

    private void validateTo(String to) {
        if (to.isBlank()) {
            throw new TaskValidationException("To cannot be empty");
        }
        try {
            DateUtils.parseDateString(to);
        } catch (DateUtilsException e) {
            throw new TaskValidationException("Invalid date format for To field: " + to);
        }
    }

}
