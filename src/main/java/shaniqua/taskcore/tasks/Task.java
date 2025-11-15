package shaniqua.taskcore.tasks;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean done;
    private ArrayList<String> tags = new ArrayList<>();
    String name;

    /**
     * Constructs Task object.
     * @param name name of task
     * @throws InvalidTaskDataException
     */
    public Task(String name) throws InvalidTaskDataException {
        if (name == "" || name == null) {
            throw new InvalidTaskDataException("Please input name");
        }
        this.name = name;
        this.done = false;
    }

    public void tag(String tag) {
        tags.add(tag);
    }

    /**
     * Returns string representation of Task, including whether it is done
     * @return String representation of task
     */
    @Override
    public String toString() {
        StringBuilder tagString = new StringBuilder();
        for (int i = 0; i < tags.size(); i++) {
            tagString.append("#" + tags.get(i)).append(" ");
        }
        return String.format("[%s] ", done ? "X" : " ") + name + tagString.toString();
    }

    /**
     * marks Task as complete.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks Task as incomplete
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns completed status of task
     * @return boolean of status
     */
    public boolean getMarked() {
        return done;
    }

    public String getName() {
        return name;
    }

    /**
     * Compares Task Object with another for equality
     * @param object the object to compare with task.
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Task) {
            Task temp = (Task) object;
            return temp.name == this.name;
        }
        return false;
    }
}
