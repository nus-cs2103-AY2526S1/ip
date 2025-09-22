package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

/**
 * Represents an instruction to mark a task as completed.
 * The instruction takes the index of the task to mark and updates its status.
 */
public class MarkInstruction implements Instruction {
    public String arguments;
    private final int index;

    /**
     * Constructs a MarkInstruction with the given arguments.
     * The argument should be the 1-based index of the task to mark as completed.
     *
     * @param arguments Input string containing the task index to mark.
     */
    public MarkInstruction(String arguments) {
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
        currTask.setCompleted(Boolean.TRUE);
        dataStorage.save(storage);

        ui.showMessage("Well done! Klalopz has marked this task:\n" + currTask);
        ui.showLine();
    }

    @Override
    public boolean doIExit() {
        return false;
    }

}
