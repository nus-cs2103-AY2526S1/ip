package Dan.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class ToDo extends Task {
    public ToDo(String description, String isDone) {
       super(isDone, description);
    }

    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    public static ToDo create(ArrayList<String> taskInfo) throws IllegalArgumentException  {
        if (taskInfo.size() != 2) {
            throw new IllegalArgumentException();
        }

        String isDone = taskInfo.get(0);
        String description = taskInfo.get(1);

        return new ToDo(description, isDone);
    }

    @Override
    public LocalDate getReminderDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
