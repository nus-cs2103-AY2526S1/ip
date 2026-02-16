package horus.tasks;

/**
 * An object representing a reccuring task with a deadline.
 */
public class RecurringDeadlineTask extends DeadlineTask {
    int every;

    /**
     * Creates a recurring deadline task.
     *
     * @param taskStr String representing the description of deadline task to be created
     *               and the number of days this task will recur denoted by /every.
     * @throws InvalidInputException If deadline is not a valid dateTime or /every is not an integer
     */
    public RecurringDeadlineTask(String taskStr) throws InvalidInputException {
        super(taskStr.substring(0,taskStr.indexOf("/every")) );


        if (!this.by.checkDateTime()) {
            throw new InvalidInputException("Error: Recurring deadline task " +
                    "must have a valid deadline (with format d/M/yyyy for dates and HHmm for times)");
        }

        String everyStr = taskStr.substring(taskStr.indexOf("/every") + 6).strip();
        try {
            every = Integer.parseInt(everyStr);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Error: Recurrence period must be an integer (denoted by /every)");
        }
    }

    @Override
    public void mark() {
        this.by.plusDays(every);
    }

    @Override
    public void unmark() {
        this.by.minusDays(every);
    }

    @Override
    public String getTaskData() {
        String taskData = super.getTaskData() + "/every " + this.every;

        try {
            assert new RecurringDeadlineTask(taskData).equals(this);
        } catch (InvalidInputException e) {
            throw new AssertionError(e);
        }

        return taskData;
    }

    @Override
    public String toString() {
        return super.toString() +" (every " + this.every + " days)";
    }

    @Override
    public boolean equals(Object inputObj) {
        if (!(inputObj instanceof DeadlineTask inputDeadlineTask)){
            return false;
        }
        if (!(inputDeadlineTask.equals(this))) {
            return false;
        }

        if (!(inputDeadlineTask instanceof RecurringDeadlineTask inputRecurringDeadlineTask)){
            return false;
        }

        return inputRecurringDeadlineTask.every == this.every;
    }
}
