public class EventTask extends Task {
    String startdate;
    String enddate;

    public EventTask(String description, String startdate, String enddate) {
        super(description);
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + startdate + " to: " + enddate + ")";
    }
}
