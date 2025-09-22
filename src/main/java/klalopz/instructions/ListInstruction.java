package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

/**
 * Represents an instruction to display all tasks currently in the task list.
 * When executed, it prints each task with its index, type, completion status, and title.
 * If the task list is empty, a message is shown to indicate no tasks are present.
 */
public class ListInstruction implements Instruction {

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        if (storage.isEmpty()) {
            ui.showMessage("HEY! You haven't added anything yet!");
        } else {
            ui.showMessage("No [type] [Completed] Title #Tags");
            ui.showLine();

            for (int i = 0; i < storage.size(); i++) {
                Task currTask = storage.getTask(i);
                ui.showMessage((i + 1) + ". " + currTask);
                ui.showLine();
            }
        }
    }

    @Override
    public boolean doIExit() {
        return false;
    }
}
