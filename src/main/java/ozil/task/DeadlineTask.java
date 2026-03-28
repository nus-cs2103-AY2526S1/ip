package ozil.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* Deadline tasks are an extension of tasks, containing a deadline
*/
public class DeadlineTask extends Task {
    private String deadline;
    private Date deadlineTime;

    /**
     * Creates a new instance of a deadline task.
     * @param description Description of the task.
     * @param deadline Due date for the task.
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline.trim();
        try {
            this.deadlineTime = parseDateTime(deadline.trim());
        } catch (ParseException e) {
            //Task will have no date deadline
            //Allows user to still have task but with a string instead of date
        }
    }

    @Override
    public boolean hasDate() {
        return deadlineTime != null;
    }

    @Override
    public String toString() {
        if (this.hasDate()) {
            DateFormat formatter = new SimpleDateFormat("HHmm");
            if (formatter.format(deadlineTime).equals("0000")) {
                formatter = new SimpleDateFormat("dd-MM-yyyy");
            } else {
                formatter = new SimpleDateFormat("dd-MM-yyyy HHmm");
            }
            return String.format("[D] %s by: %s ", super.toString(), formatter.format(deadlineTime));
        } else {
            assert !this.deadline.isEmpty();
            return "[D] " + super.toString() + " by: " + this.deadline;
        }
    }

    @Override
    public String convertToStorageFormat() {
        return String.format("D | %d | %s | %s ", this.isDone ? 1 : 0, this.description,
                this.deadline);
    }

    @Override
    public Date getTaskDate() {
        return this.deadlineTime;
    }
}
