package horus.tasks;

/**
 * An object representing a reoccurring event task.
 */
public class RecurringEventTask extends EventTask {
    int every;

    /**
     * Creates a recurring event task.
     *
     * @param taskStr String representing the description of event task to be created
     *               and the number of days this task will recur denoted by /every.
     * @throws InvalidInputException If start and end are not a valid dateTime or /every is not an integer.
     */
    public RecurringEventTask(String taskStr) throws InvalidInputException {
        super(taskStr.substring(0,taskStr.indexOf("/every")) );


        if (!this.from.checkDateTime() || !this.to.checkDateTime()) {
            throw new InvalidInputException("Error: Recurring event task " +
                    "must have a valid start and end (with format d/M/yyyy for dates and HHmm for times)");
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
        this.from.plusDays(every);
        this.to.plusDays(every);
    }

    @Override
    public void unmark() {
        this.from.minusDays(every);
        this.to.minusDays(every);
    }

    @Override
    public String getTaskData() {
        String taskData = super.getTaskData() + "/every " + this.every;

        try {
            assert new RecurringEventTask(taskData).equals(this);
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
        if (!(inputObj instanceof EventTask inputEventTask)){
            return false;
        }
        if (!(inputEventTask.equals(this))) {
            return false;
        }

        if (!(inputEventTask instanceof RecurringEventTask inputRecurringEventTask)){
            return false;
        }

        return inputRecurringEventTask.every == this.every;
    }
}