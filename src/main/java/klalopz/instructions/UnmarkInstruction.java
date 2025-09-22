package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

/**
 * Represents an instruction to unmark a previously completed task.
 * The instruction takes the index of the task to unmark and updates its status.
 */
public class UnmarkInstruction implements Instruction {

    public String arguments;
    private final int index;

    /**
     * Constructs an UnmarkInstruction with the given arguments.
     * The argument should be the 1-based index of the task to unmark.
     *
     * @param arguments Input string containing the task index to unmark.
     */
    public UnmarkInstruction(String arguments) {
        this.arguments = arguments;

        this.index = Integer.parseInt(arguments.trim()) - 1;
    }
    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        if (index < 0 || index >= storage.size()) {
            ui.showMessage("Klalopz can't find the task :(");
            return;
        }

        Task currTask = storage.getTask(index);
        currTask.setCompleted(Boolean.FALSE);
        dataStorage.save(storage);

        ui.showMessage("Understood! Klalopz has unmarked this task:\n" + currTask);
        ui.showLine();
    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
