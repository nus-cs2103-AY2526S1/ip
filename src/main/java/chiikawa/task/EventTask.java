package chiikawa.task;

/**
 * Class for Events which are tasks that start at a specific date/time and ends at specific date/time.
 */
public class EventTask extends Task {
    private String startTime = "";
    private String endTime = "";
    private String formattedStartTime = "";
    private String formattedEndTime = "";

    /**
     * Constructor for creating new event tasks by user.
     *
     * @param name Name of the task.
     * @param startTime Starting time of the task.
     * @param endTime Ending time of the task.
     */
    public EventTask(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        this.formattedStartTime = super.reformatTime(this.startTime);
        this.formattedEndTime = super.reformatTime(this.endTime);
    }

    /**
     * Constructor for creating new event tasks as they are loaded from save file.
     *
     * @param name Name of the task.
     * @param isCompleted Status of the task.
     * @param startTime Starting time of the task.
     * @param endTime Ending time of the task.
     */
    public EventTask(String name, boolean isCompleted, String startTime, String endTime) {
        super(name, isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
        this.formattedStartTime = super.reformatTime(this.startTime);
        this.formattedEndTime = super.reformatTime(this.endTime);
    }

    @Override
    public String toString() {
        String outputStartTime = this.formattedStartTime.isEmpty() ? this.startTime : this.formattedStartTime;
        String outputEndTime = this.formattedEndTime.isEmpty() ? this.endTime : this.formattedEndTime;
        return "E " + super.toString() + " | " + outputStartTime + " to " + outputEndTime;
    }
}
