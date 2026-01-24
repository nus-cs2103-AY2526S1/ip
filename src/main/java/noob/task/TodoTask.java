package noob.task;

/**
 * A subclass of Task representing simple tasks with no deadlines or date fields
 */
public class TodoTask extends Task {

    public TodoTask(String text) {
        super(text);
    }

    @Override
    public TaskType getType() {
        return TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TodoTask t) {
            return this.getDesc().equals(t.getDesc());
        }
        return false;
    }
}
