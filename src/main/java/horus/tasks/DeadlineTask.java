package horus.tasks;

/**
 * An object representing a task with a deadline.
 */
public class DeadlineTask extends Task {
    CustomDateTime by;

    /**
     * Creates a deadline task.
     *
     * @param taskStr String representing the description of task to be created
     *                and a deadline denoted by /by.
     */
    public DeadlineTask(String taskStr) {
        super(taskStr.substring(0,taskStr.indexOf("/by")) );
        by = new CustomDateTime(taskStr.substring(taskStr.indexOf("/by") + 3));
    }

    @Override
    public String getTaskData() {
        String taskData = "D," + super.getTaskData() + "/by " + this.by.getData();
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
        return "[D]" + super.toString() + " (by: " + this.by +")";
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(super.equals(inputObj))) {
            return false;
        }

        if (inputObj.getClass() != getClass()){
            return false;
        }

        DeadlineTask inputDeadlineTask = (DeadlineTask) inputObj;
        return inputDeadlineTask.by.equals(this.by);
    }
}
