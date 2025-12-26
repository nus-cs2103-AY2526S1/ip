package Baozii;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toSerial() {
        return "T&" + super.toSerial();
    }
}
