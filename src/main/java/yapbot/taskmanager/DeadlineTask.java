package yapbot.taskmanager;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String name, boolean isMarked, String deadline) {
        super(name, isMarked);
        this.deadline = DateTime.convertToISO(deadline);
    }

    @Override
    public LocalDate getISODate() {
        return this.deadline;
    }

    private String getDeadline() {
        return DateTime.convertFromISO(this.deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s -by %s", super.toString(), getDeadline());
    }
}
