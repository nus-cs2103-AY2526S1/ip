package seedu.duke.task;

import java.util.Set;

import seedu.duke.util.TagUtil;

/**
 * Base class for all tasks.
 */
public abstract class Task {

    protected boolean done;
    protected String name;

    /**
     * Creates a {@code Task}.
     *
     * @param name Task description.
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Returns the set of tags parsed from the task name (prefixed with {@code #}).
     *
     * @return Set of tags (lowercased, no {@code #}).
     */
    public Set<String> getTags() {
        return TagUtil.extractTags(name);
    }

    // --- getters/setters ---

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if done; {@code false} otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the done status.
     *
     * @param v Done state to set.
     */
    public void setDone(boolean v) {
        this.done = v;
    }

    /**
     * Returns the task name/description.
     *
     * @return Name.
     */
    public String getName() {
        return name;
    }

    // --- stable save format ---

    /**
     * Type discriminator used in save files ({@code "T"} / {@code "D"} / {@code "E"}).
     *
     * @return The type code.
     */
    protected abstract String typeCode();

    /**
     * Extra fields appended to the save line after the name.
     *
     * @return Array of extra fields (may be empty).
     */
    protected String[] extraFieldsForSave() {
        return new String[0];
    }

    /**
     * Returns a stable single-line representation suitable for persistence.
     *
     * @return Save line.
     */
    public String toSaveString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(typeCode())
                .append('|')
                .append(done ? 1 : 0)
                .append('|')
                .append(name);

        for (String f : extraFieldsForSave()) {
            sb.append('|').append(f);
        }
        return sb.toString();
    }

    /**
     * Returns a user-friendly representation for display.
     *
     * @return Formatted string.
     */
    @Override
    public String toString() {
        final String base = TagUtil.stripTags(name).trim();
        final String tags = String.join(" ", getTags()); // already lowercased in TagUtil
        final String suffix = tags.isEmpty() ? "" : " " + tags;
        // If base becomes empty (name was only tags), fall back to original name.
        final String display = base.isEmpty() ? name : base + suffix;
        return (done ? "[X] " : "[ ] ") + display;
    }
}
