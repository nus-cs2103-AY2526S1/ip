package bin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("d MMMM ha");

    public Task(Boolean isDone, String description) {
        assert isDone != null : "isDone should not be null";
        assert description != null : "description should not be null";

        this.description = description;
        this.isDone = isDone;
    }

    public LocalDateTime readDate(String str) {
        return LocalDateTime.parse(str.trim(), format);
    }

    /**
     * Format to be added into task list.
     */
    public String addTask() {
        int num = isDone ? 1 : 0;
        return "T | " + num + " | " + description;
    }

    /**
     * Marks task as complete.
     */
    public String markAsDone() {
        this.isDone = true;
        return String.format("    Nice! I've marked this task as done:\n      %s\n", this.toString());
    }

    /**
     * Marks task as incomplete.
     */
    public String markAsNotDone() {
        this.isDone = false;
        return String.format("    OK, I've marked this task as not done yet:\n      %s\n", this.toString());
    }

    /**
     * Return the sign of completion status.
     *
     * @return String "X" if task is complete.
     */
    public String getStatusIcon() {
        return isDone
                ? "X"
                : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * returns true if description of task contains keyword.
     *
     * @param keyword String of keyword.
     * @return boolean whether description contains keyword.
     */
    public boolean contains(String keyword) {
        assert keyword != null : "Keyword should not be null";

        return this.description.contains(keyword);
    }
}
