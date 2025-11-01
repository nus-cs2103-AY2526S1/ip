package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    
    private boolean isMarked;

    private final String description;

    /**
     * Creates a task with a description
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }
    /**
     * Formats LocalDateTime for display
     * @param time The LocalDateTime to format
     * @return Formatted to be readable
     */
    
    public static String getDateTimeAsString(LocalDateTime time) {
        if (time == null) {
            return "NIL";
        } else {
            int h = time.getHour();
            return String.format("%02d/%02d/%04d %s:%02d%s",
                    time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
                    h == 0 ? 12 : h > 12 ? h - 12 : h, time.getMinute(), h > 12 ? "pm" : "am");
        }
    }

    /**
     * Formats LocalDateTime for saving
     * @param time The LocalDateTime to format
     * @return Formatted so length of String is always the same e.g. 05-03-2025 0800 instead of 5-3-2025 800
     */
    public static String getSaveDateTimeAsString(LocalDateTime time) {
        return time == null
            ? "NIL"
            : String.format("%02d-%02d-%04d %02d%02d",
            time.getDayOfMonth(), time.getMonthValue(), time.getYear(),
            time.getHour(), time.getMinute());
    }
    
    /**
     * @return The prefix of the type of Task, e.g. for EventTask, returns "Event"
     */
    public abstract String getTaskType();

    /**
     * @return Task formatted for saving and easy parsing
     */
    public abstract String getSaveString();

    public String getDescription() {
        return this.description;
    }

    public boolean getMarked() {
        return this.isMarked;
    }

    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }
}
