package task;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    public TodoTask(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String save() {
        return super.save() + "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
