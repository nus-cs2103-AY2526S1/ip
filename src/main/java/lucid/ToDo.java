package lucid;

/**
 * Standard task with no extra fields
 */
public class ToDo extends Task {
    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataString() {
        return Storage.TYPE_TODO + " | " + (this.isComplete() ? Storage.STATUS_DONE : Storage.STATUS_NOT_DONE) + " | "
                + this.getName() + "\n";
    }
}
