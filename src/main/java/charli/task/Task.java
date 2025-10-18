package charli.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Abstract base class representing a task in the Charli chatbot.
 * Provides common functionality for all task types including description and completion status.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    private final java.util.Set<String> tags = new java.util.LinkedHashSet<>();

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        assert description != null && !description.isEmpty()
                : "Task description must not be null or blank";

        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    // ----- Tags API -----
    private static String normalizeTag(String tag) {
        if (tag == null) {
            return null;
        }

        String t = tag.trim().toLowerCase(Locale.ROOT);

        if (t.isEmpty()) {
            return null;
        }
        if (!t.startsWith("#")) {
            t = "#" + t;
        }
        if (t.contains(",")) {
            throw new IllegalArgumentException("Tag cannot contain comma: " + t);
        }
        return t;
    }

    public void addTag(String tag) {
        String t = normalizeTag(tag);
        if (t == null) { return; }
        tags.add(t); // Set prevents duplicates naturally
    }

    public void removeTag(String tag) {
        String t = normalizeTag(tag);
        if (t == null) { return; }
        tags.remove(t);
    }

    public List<String> getTags() {
        return List.copyOf(tags);
    }

    public void addTagsCsv(String csv) {
        if (csv == null || csv.trim().isEmpty()) { return; }
        Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .forEach(this::addTag);
    }

    /**
     * Returns the string representation of the task.
     * Format: [Status] Description
     *
     * @return The formatted string representation
     */
    @Override
    public String toString() {
        String base = "[" + getStatusIcon() + "] " + description;
        if (!tags.isEmpty()) { base += " " + tags.toString(); }
        return base;
    }

}

