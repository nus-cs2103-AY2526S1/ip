package chani.tasks;

import java.util.List;

/**
 * Represents an Event task with a start and end.
 */
public class PeriodTask extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor.
     *
     * @param description The task description.
     * @param start The start date/time.
     * @param end The end date/time.
     */
    public PeriodTask(String description, String start, String end) {
        super(description, "P");

        assert start != null : "Event start time cannot be null";
        assert end != null : "Event end time cannot be null";

        this.start = start;
        this.end = end;
    }

    @Override
    public List<String> toAttributeList() {
        List<String> list = super.toAttributeList();
        list.add(start);
        list.add(end);
        return list;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + " (between " + start + " and " + end + ")";
    }
}
