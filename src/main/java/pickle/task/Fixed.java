package pickle.task;


/**
 * An unscheduled task with a fixed duration.
 * Displays the time needed for the task to be completed.
 */
public class Fixed extends Task {

    private String hours;

    /**
     * Initialize the Fixed Task.
     * @param description Information of the Task.
     * @param hours Number of hours this task needs.
     */
    public Fixed(String description, String hours) {
        super(description);
        this.hours = hours;
    }

    public String type() {
        return "F";
    }

    /**
     * Format to save the file into the storage text file.
     * @return a string to save the file
     */
    public String writeToFile() {

        String state = super.isComplete() ? "1" : "0";
        return "F | " + state + " | " + getDescription().trim() + " | " + this.hours;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + hours + " hours)";
    }
}
