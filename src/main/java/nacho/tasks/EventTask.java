package nacho.tasks;

/**
 * Task with a time range (from - to)
 */
public class EventTask extends Task {

    private String fromDate;
    private String toDate;

    /**
     * Creates Event Task Object with start and end date
     * @param title Task Title
     * @param fromDate Start Date (String)
     * @param toDate End Date (String)
     */
    public EventTask(String title, String fromDate, String toDate) {
        super(title);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /*
    private String getDisplayDateString(String inputString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy - hh:mm a");
        return LocalDateTime.parse(inputString, inputFormatter).format(outputFormatter);
    }
     */

    @Override
    public String getStorageRepresentation() {
        String[] info = new String[5];
        info[0] = "E";
        info[1] = Integer.toString(this.isCompleted());
        info[2] = this.getTitle();
        info[3] = this.fromDate;
        info[4] = this.toDate;

        return String.join(" | ", info);
    }

    @Override
    public String toString() {
        String eventDateString = "(from: " + this.fromDate + " to: "
                + this.toDate + ")";
        return "[E]" + super.toString() + " " + eventDateString;
    }
}
