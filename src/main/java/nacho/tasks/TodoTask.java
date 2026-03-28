package nacho.tasks;

/**
 * Task with just a Title
 */
public class TodoTask extends Task {

    public TodoTask(String title) {
        super(title);
    }

    @Override
    public String getStorageRepresentation() {
        String[] info = new String[3];
        info[0] = "T";
        info[1] = Integer.toString(this.isCompleted());
        info[2] = this.getTitle();

        return String.join(" | ", info);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
