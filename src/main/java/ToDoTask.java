public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }
}
