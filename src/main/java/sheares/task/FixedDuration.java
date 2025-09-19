package sheares.task;

/**
 * represents a FixedDuration Type Task
 */
public class FixedDuration extends Task {
    private final String duration;

    /**
     * Constructor for a FixedDuration Task using des and duration as parameters
     * @param des
     * @param duration
     */
    public FixedDuration(String des, String duration) {
        super(des);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + this.duration + ")";
    }

    /**
     * returns String representation that we write to data file
     */
    public String taskToStr() {
        String doneIndicator = super.isDone ? "1" : "0";
        StringBuilder sb = new StringBuilder();
        sb.append('F').append(" | ").append(doneIndicator).append(" | ").append(super.des).append(" | ")
                .append(this.duration);
        return sb.toString();
    }
}
