package shadow.commands;

import shadow.storage.Storage;

/**
 * Represents a command for listing all tasks currently stored in the application.
 * <p>
 * {@code ListTasks} is a singleton class which extends the {@code Command} abstract class.
 * It provides functionality to display all tasks with their respective indices and descriptions.
 */
public class ListTasks extends Command {
    private static ListTasks instance;

    /**
     * Private constructor to prevent direct instantiation.
     * ListTasks is a singleton.
     */
    private ListTasks() {
    }

    /**
     * Executes the list operation by printing all tasks stored in Storage.
     * Each task is displayed with its index (starting from 1) and description.
     */
    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder("List of tasks:\n");
        for (int i = 0; i < Storage.getInstance().getTasks().size(); ++i) {
            sb.append(String.format("%d: %s\n", i + 1, Storage.getInstance().getTasks().get(i)));
        }
        return sb.toString();
    }

    /**
     * Returns the singleton instance of the {@code ListTasks} command.
     * <p>
     * Creates a new instance if it does not already exist.
     * </p>
     *
     * @param parts the command parts parsed from user input (ignored in this method)
     * @return the singleton {@code ListTasks} instance
     * @throws IllegalArgumentException never thrown by this method but declared for consistency
     */
    public static ListTasks of(String[] parts) throws IllegalArgumentException {
        assert(parts[0].equals("list"));
        if (ListTasks.instance == null) {
            ListTasks.instance = new ListTasks();
        }
        return ListTasks.instance;
    }
}
