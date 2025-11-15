package nami.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Tasks{

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * constructor of the Event class
     * @param description
     * @param startTime
     * @param endTime
     */
    public Events(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Get the type which is event
     * @return String 'E'
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Get the result, returns a string of event class
     * @return
     */
    public String getResult() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return "[" + this.getType() + "] [" + this.getStatusIcon() + "] " + this.getDescription() + " by: " + this.startTime.format(outputFormat) + " to: " + this.endTime.format(outputFormat);
    }

    /**
     * gets the list, returns a string specifically when list command is called
     * @return String of list
     */
    public String getList() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return "[" + this.getType() + "] [" + this.getStatusIcon() + "] " + this.description + "( by: " + this.startTime.format(outputFormat) + " to: " + this.endTime.format(outputFormat) + " )";
    }

    /**
     * gets the string for storage txt file
     * @return string
     */
    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }

    @Override
    public LocalDateTime getSortKey() {
        return this.startTime;
    }

}
//testing