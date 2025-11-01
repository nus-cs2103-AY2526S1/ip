package tasks;

public class TodoTask extends Task {

    /**
     * Creates a task with only a description
     * @param description The description
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        String m = getMarked() ? "X" : " ";
        return String.format("[%s][%s] %s", "TODO", m, getDescription());
    }
    
    @Override
    public String getSaveString() {
        return String.format("%s|||%s|||%s", getTaskType(), getMarked(), getDescription());
    }
}
