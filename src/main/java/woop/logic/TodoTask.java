package woop.logic;

/**
 * A class for to do type tasks.
 */
public class TodoTask extends Task {
    private static final String TASK_STRING = "T";

    /**
     * Initialises a TodoTask object using name.
     * isFinished is set to false by default.
     *
     * @param name The name of task.
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * Initialises a TodoTask object using name and isFinished and tag.
     * Used by Storage to initialise saved tasks.
     *
     * @param name The name of task.
     * @param isFinished Whether task is finished.
     * @param tag The tag of task.
     */
    public TodoTask(String name, boolean isFinished, String tag) {
        super(name, isFinished, tag);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSaveInfo() {
        return TASK_STRING + super.getSaveInfo();
    }

    @Override
    public String toString() {
        return "[" + TASK_STRING + "]" + super.toString() + " " + getTag();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof TodoTask t) {
            return super.equals(t);
        }

        return false;
    }
}
