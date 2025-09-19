package sheares.command;

import sheares.Storage;
import sheares.TaskList;
import sheares.Ui;
import sheares.task.Task;

/**
 * represents command to delete a task from the list
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a new deleteCommand for that index
     * @param index
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) {
        if (ls.size() == 0) {
            System.out.println("    There are no tasks to delete");
            return;
        }
        if (this.index < 0) {
            System.out.println("    Input is negative: Pls pick a number from 1 to " + ls.size());
            return;
        }
        if (this.index >= ls.size()) {
            System.out.println("    Input exceeds number of tasks: Pls pick a number from 1 to " + ls.size());
            return;
        }
        Task task = ls.get(this.index);
        ls.delete(this.index);
        storage.save(ls);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + ls.size() + " tasks in the list.");
    }

    @Override
    public String executeWithString(TaskList ls, Ui ui, Storage storage) {
        if (ls.size() == 0) {
            return "    There are no tasks to delete";
        }
        if (this.index < 0) {
            return "    Input is negative: Pls pick a number from 1 to " + ls.size();
        }
        if (this.index >= ls.size()) {
            return "    Input exceeds number of tasks: Pls pick a number from 1 to " + ls.size();
        }
        Task task = ls.get(this.index);
        ls.delete(this.index);
        storage.save(ls);
        String s = "";
        StringBuilder sb = new StringBuilder();
        sb.append("    Noted. I've removed this task: \n").append("      ")
                .append(task).append("\n").append("    Now you have ")
                .append(ls.size()).append(" tasks in the list");
        return sb.toString();
    }
}
