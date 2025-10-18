package uy;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Represents a task that has a deadline date.
 * Accepts both user and storage formats when constructed from a string.
 */
public class Deadlines extends Task {
    protected LocalDate deadline;

    public Deadlines(String description) {
        super("Deadlines", false, "D");

        Pattern user_input_pattern = Pattern.compile("^(.*?) /by (.*?)$");
        Matcher user_input_matcher = user_input_pattern.matcher(description);

        Pattern database_entry_pattern = Pattern.compile("^(.*?) \\(by: (.*?)\\)$");
        Matcher database_entry_matcher = database_entry_pattern.matcher(description);

        if(user_input_matcher.find()) {
            String new_task_name = user_input_matcher.group(1);
            String new_deadline = user_input_matcher.group(2);
            this.task_name = new_task_name;
            this.deadline = Uy.parse_date(new_deadline);

        } else if(database_entry_matcher.find()) {
            String new_task_name = database_entry_matcher.group(1);
            String new_deadline = database_entry_matcher.group(2);
            this.task_name = new_task_name;
            this.deadline = Uy.parse_date(new_deadline);
       
        } else {
            throw new IllegalArgumentException("Invalid deadline format");
        }
    }

    @Override
    public int compareTo(Task other) {
        if(!(other instanceof Deadlines)) {
            return super.compareTo(other);
        }
        Deadlines otherDeadline = (Deadlines) other;
        return this.deadline.compareTo(otherDeadline.deadline);
    }

    @Override
    /**
     * Returns a string representation including the formatted deadline.
     */
    public String toString() {
        return super.toString() + " (by: " + Uy.format_date(this.deadline) + ")";
    }
}
