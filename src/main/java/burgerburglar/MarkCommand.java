package burgerburglar;

/**
 * Represents a command to mark or unmark a task as done in the task list.
 * <p>
 * This command updates the task status and saves the task list to storage.
 */
public class MarkCommand extends Command {
    private final String args;
    private final boolean isMark;

    /**
     * Constructs a MarkCommand with the given task index and mark/unmark flag.
     *
     * @param args   the string containing the index of the task to mark/unmark
     * @param isMark true to mark the task as done, false to unmark it
     */
    public MarkCommand(String args, boolean isMark) {
        assert args != null && !args.isBlank() : "Args cannot be null or blank";
        this.args = args;
        this.isMark = isMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        try {
            int index = Integer.parseInt(args.trim()) - 1;
            Task updated = tasks.markTask(index, isMark);

            storage.save(tasks);

            if (isMark) {
                return String.format("YOU DID IT! BURGER!\nMARKED: %s", updated);
            } else {
                return String.format("BURGER IS ASHAMED OF YOU.\nUNMARKED: %s", updated);
            }

        } catch (NumberFormatException e) {
            throw new BurgerException("BURGER ERROR: THAT'S NOT A NUMBER.");
        } catch (IndexOutOfBoundsException e) {
            throw new BurgerException("BURGER ERROR: INVALID TASK NUMBER.");
        }
    }
}
