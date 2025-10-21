package task;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Class to abstract common functionality of all tasks
 */
public abstract class Task {
    private boolean completed = false;
    private final String description;

    public Task(String description) {
        this.description = description.strip();
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.description;
    }

    public String toFileString() {
        return " | " + (this.completed ? "1" : "0") + " | " + this.description;
    }

    protected static String dateToString(LocalDate d) {
        return d.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + d.getDayOfMonth()
                + " " + d.getYear();
    }

    public boolean containsString(String s) {
        return this.description.contains(s);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task t) {
            return t.description.equals(this.description) && t.completed == this.completed;
        }
        return false;
    }
}
