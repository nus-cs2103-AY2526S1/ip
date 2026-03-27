package Skye.classes;
public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }
}
