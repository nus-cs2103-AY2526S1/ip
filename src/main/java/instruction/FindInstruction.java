package instruction;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an instruction to find tasks that contain a specific keyword.
 * This instruction searches through the list of tasks and returns those
 * that match the given keyword.
 */
public class FindInstruction extends Instruction {
    private String word;

    /**
     * Constructs a FindInstruction with the specified keyword.
     *
     * @param word the keyword to search for in tasks
     */
    public FindInstruction(String word) {
        this.word = word;
    }

    /**
     * Executes the find instruction by searching for tasks that contain the keyword
     * and returning the matching tasks.
     *
     * @param tasks   the list of tasks to search through
     * @param ui      the UI component used to generate the results
     * @param storage the storage component (not used in this instruction)
     * @return formatted string of matching tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printFind(tasks.getAllTasks(), word);
    }
}
