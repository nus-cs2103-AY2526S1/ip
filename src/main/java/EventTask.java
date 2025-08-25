import java.util.ArrayList;

/**
 * Encapsulate an event task.
 */
class EventTask extends Task {
    /** Start time of event. */
    private String from;
    /** End time of event. */
    private String to;

    /**
     * Construct a basic task.
     * 
     * @param name Name of task to be created.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(String.format("event %s /from %s /to %s", name, from, to));
        if (status) {
            commands.add(String.format("mark"));
        }
        return commands;
    }

    /**
     * @return String representation of an event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
