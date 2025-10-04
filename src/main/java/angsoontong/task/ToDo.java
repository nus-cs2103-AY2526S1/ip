package angsoontong.task;

public class ToDo extends angsoontong.task.Task {

    /**
     * constructor
     * @param name String name to describe task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * custom toString representation
     */
    @Override
    public String toString() {
        return withTags(String.format("[T]" + super.toString()));
    }


    /**
     * method to format task description to write to storage file
     */
    @Override
    public String toFileFormat() {
        return "T | " +
                (super.isDone() ? "1" : "0") +
                " | " + super.getName() +
                tagsForFile();
    }
}
