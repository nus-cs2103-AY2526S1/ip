package horus.tasks;

/**
 * An object representing a task to be done
 */
public class ToDoTask extends Task {

    /**
     * Creates a ToDoTask
     *
     * @param taskStr String representing the description of task to be created,
     */
    public ToDoTask(String taskStr) {
        super(taskStr);
    }

    @Override
    public String getTaskData() {
        String taskData = "T," + super.getTaskData();
        Task newTask;
        try {
            newTask = Task.readTaskData(taskData);
        } catch (InvalidInputException e) {
            throw new AssertionError();
        }
        assert newTask.equals(this);

        return taskData;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(super.equals(inputObj))) {
            return false;
        }

        if (inputObj.getClass() != getClass()){
            return false;
        }

        return true;
    }
}