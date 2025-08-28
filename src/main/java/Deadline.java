public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    @Override 
    protected String kind() { 
        return "[D]"; 
    }

    @Override 
    public String toString() {
        String formatted = (by != null) ? by.format(OUTPUT_FORMAT).replace(":00AM", "am").replace(":00PM", "pm") : "invalid date";
        return kind() + status() + " " + super.description + " (by: " + formatted + ")";
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

    public String getByRaw() {
        return (by != null) ? by.format(INPUT_FORMAT) : "";
    }

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
    }
}