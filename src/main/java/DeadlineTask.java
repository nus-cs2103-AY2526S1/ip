import java.util.ArrayList;

/**
 * Encapsulate a deadline task.
 */
class DeadlineTask extends Task {
    /** Time that task need to be done before. */
    private String deadline;

    /**
     * Construct a basic task.
     * 
     * @param name Name of task to be created.
     * @param deadline Deadline of task to be created.
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(String.format("deadline %s /by %s", name, deadline));
        if (status) {
            commands.add(String.format("mark"));
        }
        return commands;
    }

    /**
     * @return String representation of a deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
