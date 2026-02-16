package horus.tasks;

/**
 * An object representing an event task.
 */
public class EventTask extends Task {
    CustomDateTime from;
    CustomDateTime to;

    /**
     * Creates an event task.
     *
     * @param taskStr String representing the description of task to be created,
     *                the start of the event denoted by /from
     *                and the end of the event denoted by /to.
     */
    public EventTask(String taskStr) {
        super(taskStr.substring(0,taskStr.indexOf("/from")) );
        from = new CustomDateTime(taskStr.substring(taskStr.indexOf("/from") + 5,taskStr.indexOf("/to") )) ;
        to =  new CustomDateTime(taskStr.substring(taskStr.indexOf("/to") + 3 ));

    }

    @Override
    public String getTaskData() {
        String taskData = "E," + super.getTaskData() + "/from " + this.from.getData() + " /to " + this.to.getData();
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
        return "[E]" + super.toString() +" (from: " + this.from + " to: "+ this.to +")";
    }

    @Override
    public boolean equals(Object inputObj) {

        if (!(super.equals(inputObj))) {
            return false;
        }

        if (inputObj.getClass() != getClass()){
            return false;
        }

        EventTask inputEventTask = (EventTask) inputObj;
        return inputEventTask.from.equals(this.from) && inputEventTask.to.equals(this.to);

    }
}
