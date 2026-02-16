package peppa.task;

import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFileFormat() {
        String data = "T | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += this.description;
        return data;
    }

    @Override
    public LocalDateTime getDateTime() {
        return null; // ToDo tasks have no specific date/time
    }

    @Override
    public int compareTo(Task other) {
        LocalDateTime otherDateTime = other.getDateTime();
        
        // If the other task has a date/time, it comes before this ToDo
        if (otherDateTime != null) {
            return 1;
        }
        
        // Both tasks have no date/time - compare alphabetically by description
        return this.description.compareToIgnoreCase(other.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
