package bubbles;

/**
 * Represents a command to find a task based on a keyword in the task description
 */
public class FindCommand extends Command{
    private String command;
    private String word;

    /**
     * Constructs a {@code FindCommand} with the given command string.
     * This command searches for tasks which contains the given keyword
     * and displays it.
     *
     * @param command the user input for the keyword
     */
    public FindCommand(String command) throws BubblesException {
        super(command);
        this.command = command;

        if (this.command.length() < 5){
            throw new BubblesException("Whoops! Don't forget the keyword to search for.");
        }

        this.word = command.substring(5).trim();
    }

    /**
     * Executes the find command which extracts the keyword from the command
     * and searches for tasks which contains the keyword in the task description
     * and displays them.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int i = 1;
        StringBuilder result = new StringBuilder();

        for (Task task : tasks.getAll()) {
            String str = task.toString();

            if (str.length() > 7 && task.getDescription().contains(this.word)) {
                if (result.isEmpty()) {
                    result.append("Here are the bubbles that match:\n");
                }
                result.append("\t").append(i).append(". ").append(task).append("\n");
                i++;
            }
        }

        if (result.isEmpty()) {
            return "Uh-oh! Not found in my bubble land!";
        }

        return result.toString();
    }
}
