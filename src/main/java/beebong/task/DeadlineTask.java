package beebong.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import beebong.exception.InvalidSerializedTaskDataException;
import beebong.util.DateTimeUtil;
import beebong.util.StringUtil;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    private DeadlineTask(String name, boolean isCompleted, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
        if (isCompleted) {
            markCompleted();
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public String serializeTask() {
        return "D" + Task.SAVE_DELIMITER + super.serializeTask()
                + Task.SAVE_DELIMITER + StringUtil.encode(DateTimeUtil.toSerializedString(this.deadline));
    }

    public static DeadlineTask deserializeTask(String taskStr) throws InvalidSerializedTaskDataException {
        // -1 limit allows for empty strings
        String[] taskData = taskStr.split(Task.SAVE_DELIMITER, -1);
        if (taskData.length != 4) {
            throw new InvalidSerializedTaskDataException();
        }

        // ["D", "0", "NAME", "DEADLINE"]
        String name = StringUtil.decode(taskData[2]);
        boolean isCompleted = taskData[1].equals("1");
        LocalDateTime deadline;
        try {
            deadline = DateTimeUtil.parseDateTime(StringUtil.decode(taskData[3]));
        } catch (DateTimeParseException e) {
            throw new InvalidSerializedTaskDataException();
        }

        return new DeadlineTask(name, isCompleted, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeUtil.toString(this.deadline) + ")";
    }
}
