import java.util.ArrayList;

/**
 * Encapsulate a todo task.
 */
class TodoTask extends Task {
    /**
     * Construct a basic task.
     * 
     * @param name Name of task to be created.
     */
    public TodoTask(String name) {
        super(name);
    }

    @Override
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(String.format("todo %s", name));
        if (status) {
            commands.add(String.format("mark"));
        }
        return commands;
    }

    /**
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
