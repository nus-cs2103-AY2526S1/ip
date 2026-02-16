package chatbot.task;

/**
 * TodoTask is a subclass of Task.
 * The class handles creation of a TodoTask when user uses the todo command.
 */
public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String stringFormatCompleteStatus() {
        return "[T]" + super.stringFormatCompleteStatus();
    }

    @Override
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }
        String taskNameLowerCase = getTaskName().toLowerCase();
        String keywordLowerCase = keyword.toLowerCase();
        return taskNameLowerCase.contains(keywordLowerCase);
    }

    @Override
    public String toSaveFormat() {
        return "T | "
                + super.stringFormatCompleteStatus() + "| "
                + getTaskName();
    }
}
