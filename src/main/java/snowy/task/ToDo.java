package snowy.task;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        return "[T]" +
                (this.checkStatus() ? "[X] " : "[ ] ") +
                super.toString();
    }
}
