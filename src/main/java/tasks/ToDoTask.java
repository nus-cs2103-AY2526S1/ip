package tasks;

/**
 * ToDo class for creation of todo tasks
 * <p>
 * This class creates a ToDoTask object which contains a description.
 */
public class ToDoTask extends Task {
    public ToDoTask(String description){
        super(description);
    }

    public ToDoTask(String description, boolean isDone){
        super(description, isDone);
    }

    public String saveFileFormat() {
        return "T|" + super.saveFileFormat();
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
