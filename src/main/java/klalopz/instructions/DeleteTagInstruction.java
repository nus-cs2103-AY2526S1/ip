package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class DeleteTagInstruction implements Instruction {
    private final int index;
    private static final String EMPTY_TAG_ID = "-1";

    public DeleteTagInstruction(String arguments) {
        this.index = Integer.parseInt(arguments.trim()) - 1;
    }

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi textUi) {
        Task currTask = storage.getTask(index);
        currTask.setTag(EMPTY_TAG_ID);
        dataStorage.save(storage);

        textUi.showMessage("Klalopz has deleted this tag to the following task:\n" + currTask);
        textUi.showLine();

    }

    @Override
    public boolean doIExit() {
        return false;
    }
}
