package thopz;

import java.io.IOException;

/**
 * Mark a task from the tasklist
 */

public class Markcommand extends Command {

    private final int no;

    /**
     * Constructs a mark to specified tasks
     *
     * @param no  no of the task
     *
     *
     */

    public Markcommand(int no) {
        this.no = no;
    }


    /**
     * Executes the mark command by marking a certain task in the list
     *
     * @param ls   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    @Override
    public String perform(TaskList ls, Storage storage, Ui ui) {
        if (no < 0 || no > ls.size()) {
            throw new IllegalArgumentException("bruh, out of range");
        } else {
            Task task = ls.getTask(no);
            task.markTask();

            try {
                storage.save(ls.getAllTasks());
            } catch (IOException e) {
                System.out.println("Cannot save changes");
            }
            return ui.showMarked(task);
        }
    }
}
