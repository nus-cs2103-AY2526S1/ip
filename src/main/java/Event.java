public class Event extends Task {
    private final String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override 
    protected String kind() {
        return "[E]"; 
    }

    private final LocalDateTime from, to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        if (isDone) this.markDone();
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String input) {
        try {
            return LocalDateTime.parse(input, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            // fallback: try to parse as yyyy-MM-dd HHmm
            try {
                return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }

    @Override
    protected String kind() {
        return "[E]";
    }

    @Override
    public String toString() {
        String formattedFrom = (from != null) ? from.format(OUTPUT_FORMAT).replace(":00AM", "am").replace(":00PM", "pm") : "invalid date";
        String formattedTo = (to != null) ? to.format(OUTPUT_FORMAT).replace(":00AM", "am").replace(":00PM", "pm") : "invalid date";
        return kind() + status() + " " + super.description + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    public String getFromRaw() {
        return (from != null) ? from.format(INPUT_FORMAT) : "";
    }
    public String getToRaw() {
        return (to != null) ? to.format(INPUT_FORMAT) : "";
    }
}