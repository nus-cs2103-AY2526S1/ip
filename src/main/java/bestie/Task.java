package bestie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a single actionable item tracked by Bestie.
 */
public class Task {
    protected String description;
    protected Status status;
    protected TaskType type;
    private final LinkedHashSet<String> tags;

    /**
     * Creates a new task with the given description and type.
     *
     * @param description user-entered description of the task
     * @param type        type discriminator used for formatting and storage
     */
    public Task(String description, TaskType type) {
        assert description != null : "Task description must not be null";
        assert type != null : "Task type must not be null";
        this.description = description;
        this.status = Status.NOT_DONE;
        this.type = type;
        this.tags = new LinkedHashSet<>();
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.status = Status.DONE;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsUndone() {
        this.status = Status.NOT_DONE; // bugfix
    }

    /**
     * Returns the status icon used when displaying the task.
     *
     * @return {@code "X"} if done, otherwise a single space
     */
    public String getStatusIcon() {
        return status == Status.DONE ? "X" : " "; // mark done task with X
    }

    /**
     * Returns the human-readable description of the task.
     *
     * @return description entered by the user
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds the given tags to the task, ignoring duplicates and blank entries.
     *
     * @param rawTags user-supplied tag strings (with or without leading '#')
     * @return ordered list of canonical tags that were newly added
     */
    public List<String> addTags(Collection<String> rawTags) {
        ArrayList<String> added = new ArrayList<>();
        if (rawTags == null) {
            return added;
        }
        for (String raw : rawTags) {
            String normalized = normalizeTag(raw);
            if (!normalized.isEmpty() && tags.add(normalized)) {
                added.add(normalized);
            }
        }
        return added;
    }

    /**
     * Returns the canonical tags associated with this task.
     *
     * @return ordered, immutable list of tags without the leading '#'
     */
    public List<String> getTags() {
        return List.copyOf(tags);
    }

    /**
     * Checks whether the supplied keyword matches the task description or any
     * of its tags.
     *
     * @param keyword search term entered by the user
     * @return {@code true} if the keyword matches the description or a tag
     */
    public boolean matchesKeyword(String keyword) {
        if (keyword == null) {
            return false;
        }
        String trimmed = keyword.trim();
        if (trimmed.isEmpty()) {
            return false;
        }

        String needle = trimmed.toLowerCase();
        if (description.toLowerCase().contains(needle)) {
            return true;
        }

        String normalized = normalizeTag(trimmed);
        if (normalized.isEmpty()) {
            return false;
        }
        return tags.contains(normalized);
    }

    private static String normalizeTag(String raw) {
        if (raw == null) {
            return "";
        }
        String trimmed = raw.trim();
        while (trimmed.startsWith("#")) {
            trimmed = trimmed.substring(1).trim();
        }
        if (trimmed.isEmpty()) {
            return "";
        }
        return trimmed.toLowerCase();
    }

    private String formatTagsForDisplay() {
        if (tags.isEmpty()) {
            return "";
        }
        return tags.stream()
                .map(tag -> "#" + tag)
                .collect(Collectors.joining(" "));
    }

    protected String formatTagsForStorage() {
        if (tags.isEmpty()) {
            return "";
        }
        return String.join(",", tags);
    }

    protected String appendTagsForStorage(String base) {
        assert base != null : "Base storage representation must not be null";
        if (tags.isEmpty()) {
            return base;
        }
        return base + " | " + formatTagsForStorage();
    }

    /**
     * Serializes the task into the pipe-delimited format used by
     * {@link Storage}.
     *
     * @return representation such as {@code T | 1 | read book}
     */
    public String toDataString() {
        String done = (status == Status.DONE) ? "1" : "0";
        String base = type.getShortCode() + " | " + done + " | " + description;
        return appendTagsForStorage(base);
    }

    @Override
    public String toString() {
        String base = "[" + getStatusIcon() + "] " + description;
        String tagsDisplay = formatTagsForDisplay();
        if (tagsDisplay.isEmpty()) {
            return base;
        }
        return base + " " + tagsDisplay;
    }
}
