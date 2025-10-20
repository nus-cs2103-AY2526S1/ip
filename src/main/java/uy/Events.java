package uy;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents an event task with a start (from) and end (to) date.
 * Supports parsing from user and storage formats.
 */
public class Events extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Events(String task_name, LocalDate from, LocalDate to) {
        super(task_name, false, "E");
        this.from = from;
        this.to = to;
    }

    /**
     * Construct an Events instance by parsing a user or storage description.
     * Accepts formats like "name /from yyyy-mm-dd /to yyyy-mm-dd" or
     * "name (from: MMM dd yyyy to: MMM dd yyyy)".
     *
     * @param description input string
     * @throws IllegalArgumentException if the format is invalid
     */
    public Events(String description) throws IllegalArgumentException {
        super("Event", false, "E");

        Pattern user_input_pattern = Pattern.compile("^(.*?) /from (.*?) /to (.*)$");
        Matcher user_input_matcher = user_input_pattern.matcher(description);

        Pattern database_entry_pattern = Pattern.compile("^(.*?) \\(from: (.*?) to: (.*?)\\)$");
        Matcher database_entry_matcher = database_entry_pattern.matcher(description);

        if(user_input_matcher.find()) {
            String task_name = user_input_matcher.group(1);
            String from = user_input_matcher.group(2);
            String to = user_input_matcher.group(3);
            this.task_name = task_name;
            this.from = Uy.parse_date(from);
            this.to = Uy.parse_date(to);

        } else if (database_entry_matcher.find()){
            String task_name = database_entry_matcher.group(1);
            String from = database_entry_matcher.group(2);
            String to = database_entry_matcher.group(3);
            this.task_name = task_name;
            this.from = Uy.parse_date(from);
            this.to = Uy.parse_date(to);
        
        } else {
            throw new IllegalArgumentException("Invalid event format");
        }
    }

    @Override
    public int compareTo(Task other) {
        if(!(other instanceof Events)) {
            return super.compareTo(other);
        }
        Events otherEvent = (Events) other;
        int fromComparison = this.from.compareTo(otherEvent.from);
        if (fromComparison != 0) {
            return fromComparison;
        }
        return this.to.compareTo(otherEvent.to);
    }

    @Override
    /**
     * Return a string representation suitable for display and storage.
     */
    public String toString() {
        return super.toString() + " (from: " + Uy.format_date(this.from) + " to: " + Uy.format_date(this.to) + ")";
    }   
}

