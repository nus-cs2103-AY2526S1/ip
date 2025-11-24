package woop.logic;

/**
 * An abstract class for all Task types.
 */
public abstract class Task {
    public static final String DIVIDER = " | ";
    private String name;
    private boolean isFinished;
    private String tag;

    /**
     * Initialises a Task object by using the given name.
     * Automatically sets isFinished to false.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        assert name != null : "Name cannot be null!";
        this.name = name;
        this.isFinished = false;
        this.tag = "";
    }

    /**
     * Initialises a Task object by using the given name and a boolean for isFinished.
     *
     * @param name The name of the task.
     * @param isFinished True if task is finished, false otherwise.
     */
    public Task(String name, boolean isFinished, String tag) {
        assert name != null : "Name cannot be null!";
        this.name = name;
        this.isFinished = isFinished;
        this.tag = tag;
    }

    public boolean getIsFinished() {
        return this.isFinished;
    }

    /**
     * Marks task as finished.
     */
    public void mark() throws MarkedTaskException {
        if (this.isFinished) {
            throw new MarkedTaskException(this);
        }
        this.isFinished = true;
    }

    /**
     * Marks task as finished.
     */
    public void unmark() throws UnmarkedTaskException {
        if (!this.isFinished) {
            throw new UnmarkedTaskException(this);
        }
        this.isFinished = false;
    }

    /**
     * Returns encoded version of Task to store in a file.
     *
     * @return Encoded string for save state.
     */
    public String getSaveInfo() {
        String encodeIsFinished = this.isFinished ? "1" : "0";
        String tagSave = isTagged() ? tag : "null";
        return DIVIDER + encodeIsFinished + DIVIDER + this.name + DIVIDER + tagSave;
    }

    public boolean containsText(String text) {
        return this.name.contains(text);
    }

    public void setTag(String inputTag) {
        this.tag = inputTag;
    }

    public String getTag() {
        if (isTagged()) {
            return "#" + tag;
        }
        return "";
    }

    public boolean isTagged() {
        if (tag == null) {
            return false;
        }
        return !tag.isEmpty();
    }

    @Override
    public String toString() {
        String finished = "[X]";
        String unfinished = "[ ]";

        if (this.isFinished) {
            return finished + " " + this.name;
        } else {
            return unfinished + " " + this.name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Task t) {
            return t.name.equals(this.name)
                    && this.isFinished == t.isFinished;
        }
        return false;
    }
}
