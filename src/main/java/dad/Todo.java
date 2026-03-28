package dad;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toRecord() {
        return "T|" + super.toRecord();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}


