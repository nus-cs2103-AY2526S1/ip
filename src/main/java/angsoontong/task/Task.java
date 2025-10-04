package angsoontong.task;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class Task {
    private boolean isDone;
    private final String NAME;
    protected final Set<String> tags = new LinkedHashSet<>();

    public Task(String name) {
        this.NAME = name;
        this.isDone = false;
    }

    /**
     * mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * unmarks task, task is not done
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * getter for boolean isDone
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * getter for name
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * add tags to this task object
     * @param newTags list of tags to be added
     */
    public void addTags(List<String> newTags) {
        if (newTags == null) return;
        for (String t : newTags) {
            String norm = normalizeTag(t);
            System.out.println("adding " + norm);
            if (!norm.isEmpty()) tags.add(norm);
        }
    }

    /**
     * add tags to this task object
     * @param toRemove list of tags to be removed
     */
    public void removeTags(List<String> toRemove) {
        if (toRemove == null) return;
        for (String t : toRemove) {
            String norm = normalizeTag(t);
            if (!norm.isEmpty()) {
                System.out.println("removing " + norm);
                tags.remove(norm);}
        }
    }

    public void clearTags() {
        tags.clear();
    }

    private String normalizeTag(String t) {
        if (t == null) return "";
        t = t.trim();
        if (t.isEmpty()) return "";
        if (!t.startsWith("#")) t = "#" + t;
        return t;
    }

    /**
     * Load tags from CSV saved in file.
     */
    public void loadTagsFromCsv(String csv) {
        if (csv == null || csv.isBlank()) return;
        for (String piece : csv.split(",")) addTags(List.of(piece));
    }

    protected String withTags(String base) {
        if (tags.isEmpty()) return base;
        // Render like: {#fun #school} appended at end
        String suffix = " { " + String.join(" ", tags) + " }";
        return base + suffix;
    }

    public String tagsForFile() { return String.join(", ", tags); }

    /**
     * custom toString representation for task
     */
    @Override
    public String toString() {
        return String.format("[" + (isDone ? "X" : "") + "] %s", NAME);
    }

    /**
     * abstract method for task subclasses to write into file
     */
    public abstract String toFileFormat();
}
