package mochi;

import java.util.ArrayList;

/**
 * Represents an abstract task meant to be extended by ToDo, Deadline, and Event classes.
 * This class provides basic functionality for marking tasks as completed or not completed,
 * but requires subclasses to implement a method for saving the task's data as string.
 */
public abstract class Task {
    protected String description;
    protected boolean completed;
    private ArrayList<String> tags;

    /**
     * Creates a Task meant to be used internally by subclasses.
     *
     * @param desc the description of the task
     */
    public Task(String desc) {
        assert !desc.isEmpty() : "Task descriptions should not be empty.";
        setDescription(desc);
        this.completed = false;
    }

    /**
     * Creates a Task meant to be used internally by subclasses.
     *
     * @param desc the description of the task
     * @param status the completion status of the task
     */
    public Task(String desc, boolean status) {
        assert !desc.isEmpty() : "Task descriptions should not be empty.";
        setDescription(desc);
        this.completed = status;
    }

    /**
     * Sets the tags and the description.
     *
     * @param rawDesc the original description that may contain tags
     */
    private void setDescription(String rawDesc) {
        int tagStartIndex = rawDesc.indexOf('#');
        if (tagStartIndex != -1) {
            this.description = rawDesc.substring(0, tagStartIndex).trim();
            String[] rawTags = rawDesc.substring(tagStartIndex).split(" ");
            this.tags = new ArrayList<String>();
            for (String tag : rawTags) {
                // remove '#' character before adding
                this.tags.add(tag.substring(1));
            }
        } else {
            this.description = rawDesc;
            this.tags = new ArrayList<String>();
        }
    }

    /**
     * Marks this task as completed.
     *
     * @return A string indicating the details of the task has been marked as done.
     */
    public String mark() {
        this.completed = true;
        return String.format("""
                 Nice! I've marked this task as done:
                   [X] %s
                """, description);
    }

    /**
     * Marks this task as not completed.
     *
     * @return A string indicating the details of the task that has been marked as not done.
     */
    public String unmark() {
        this.completed = false;
        return String.format("""
                 OK, I've marked this task as not done yet:
                   [ ] %s
                """, description);
    }

    /**
     * Adds a tag to a task.
     *
     * @return A string indicating the details of the task that has been tagged.
     */
    public String tag(String tagName) {
        this.tags.add(tagName);
        return String.format("""
                 Task "%s" has been tagged with "%s" successfully.
                """, description, tagName);
    }

    /**
     * Removes a tag from a task.
     *
     * @return A string indicating the details of the task that has been untagged.
     */
    public String untag(String tagName) {
        boolean tagExists = this.tags.remove(tagName);
        if (tagExists) {
            return String.format("""
                     Task "%s" has been untagged successfully.
                    """, description);
        }
        return String.format("""
             No tag "%s" exists on this task to remove.
            """, tagName);
    }

    /**
     * Converts the tags from an ArrayList to a readable single-line string.
     *
     * @return A string with all the tags of the task.
     */
    public String tagsToString() {
        String res = "";
        if (tags.isEmpty()) {
            return res;
        }
        for (int i = 0; i < tags.size() - 1; i++) {
            res = res.concat("#" + tags.get(i) + " ");
        }
        res = res.concat("#" + tags.get(tags.size() - 1));
        return res;
    }

    public boolean descriptionContains(String word) {
        return this.description.contains(word);
    }

    // Returns the string representation of this task to be saved as persistent data
    /**
     *  Returns the string representation of this task to be saved as persistent data
     */
    public abstract String getSaveString();

    /**
     * Returns a save string containing description and tag information.
     *
     * @return A string with task description and tags.
     */
    protected String getDescriptionSaveString() {
        return (this.description + " " + tagsToString());
    }

    /**
     *  Returns the task in string format for display.
     *
     *  @return the task in the format "[status] description", e.g. "[X] return book".
     */
    @Override
    public String toString() {
        char status = completed ? 'X' : ' ';
        return String.format("[%c] %s %s", status, description, tagsToString());
    }
}
