package klalopz.tasks;

import klalopz.enums.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with a description and completion status.
 * This class is extended by specific task types such as ToDo, Deadline, and Event.
 */
public class Task {
    private String details;
    private boolean isCompleted;
    private Tag tag;
    private static final String EMPTY_TAG_ID = "-1";

    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Constructs a Task with the given description, completion status and a default tag of NONE.
     *
     * @param details The description of the task.
     * @param isCompleted True if the task is completed, false otherwise.
     */
    public Task(String details, boolean isCompleted) {
        this.details = details;
        this.isCompleted = isCompleted;
        this.tag = Tag.NONE;
    }

    /**
     * Returns a visual representation of the completion status.
     *
     * @return "[X]" if completed, "[ ]" otherwise.
     */
    public String getCompletedLogo() {
        return isCompleted ? "[X]" : "[ ]";
    }

    /**
     * Returns the task type logo. Default is "[?]" for generic tasks.
     *
     * @return The task logo string.
     */
    public String getTaskLogo() {
        return "[?]";
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void setCompleted(Boolean status) {
        this.isCompleted = status;
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    /**
     * Serializes the task into a string for storage.
     *
     * @return Serialized string representing the task.
     */
    public String serialize() { return this.getTaskLogo() + " | " +
                                this.details + " | " + this.isCompleted
                                + " | " + this.getTag().getId(); }

    /**
     * Deserializes a string from storage back into a Task or its subclass.
     *
     * @param data The serialized task string.
     * @return A Task object corresponding to the serialized data.
     * @throws IllegalArgumentException If the task type is unknown.
     */
    public static Task deserialize(String data) {
        String[] splitData = data.split( " \\| ");
        String type = splitData[0];
        Task task = getTask(splitData, type);

        int tagIndex = switch (type) {
            case "[T]", "[?]" -> 3;
            case "[D]" -> 4;
            case "[E]" -> 5;
            default -> -1;
        };

        if (tagIndex >= 0 && tagIndex < splitData.length) {
            try {
                task.setTag(splitData[tagIndex]);
            } catch (IllegalArgumentException e) {
                task.setTag(EMPTY_TAG_ID);
            }
        } else {
            task.setTag(EMPTY_TAG_ID);
        }

        return task;
    }

    private static Task getTask(String[] splitData, String type) {
        String detail = splitData[1];
        boolean isCompleted = Boolean.parseBoolean(splitData[2]);


        return switch (type) {
            case "[?]" -> new Task(detail, isCompleted);
            case "[T]" -> new ToDo(detail, isCompleted);
            case "[D]" -> new Deadline(detail, isCompleted, LocalDate.parse(splitData[3], Task.DATE_FORMATTER));
            case "[E]" -> new Event(detail, isCompleted, LocalDate.parse(splitData[3], Task.DATE_FORMATTER),
                    LocalDate.parse(splitData[4], Task.DATE_FORMATTER));
            default -> throw new IllegalArgumentException("Unknown Task detected");
        };
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(String tagInput) {
        assert tagInput != null : "Tag input cannot be null";
        tagInput = tagInput.trim();

        Tag parsedTag = Tag.NONE;

        try {
            int id = Integer.parseInt(tagInput);
            parsedTag = Tag.fromId(id);
        } catch (NumberFormatException ignored) {
            parsedTag = Tag.fromName(tagInput);
        }

        this.tag = parsedTag;
    }



    /**
     * Checks if the given string is a valid integer within the specified range.
     *
     * @param input the string to check
     * @param min   the minimum allowed value (inclusive)
     * @param max   the maximum allowed value (inclusive)
     * @return true if input is a valid integer in range, false otherwise
     */
    public static boolean isNumberInRange(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input.trim());
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        String tagString = (tag != null && tag != Tag.NONE) ? " " + tag : "";
        return this.getTaskLogo() + this.getCompletedLogo() + " "
                + this.getDetails() + tagString;
    }
}

