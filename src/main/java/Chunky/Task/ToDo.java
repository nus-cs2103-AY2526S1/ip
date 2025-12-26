package Chunky.Task;

/**
 * To Do class to create To Do objects
 */
public class ToDo extends Task {
    private String location;

    public ToDo(String description, String location) {
        super(description);
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "[T] " + super.toString() + " at " + this.location;
    }
}
