package klalopz.instructions;

import klalopz.enums.Tag;
import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class SetTagInstruction implements Instruction {
    private final int index;
    private final String tag;

    public SetTagInstruction(String arguments) {
        String[] splitArguments = arguments.split(" ", 2);
        this.index = Integer.parseInt(splitArguments[0]) - 1;
        this.tag = splitArguments[1];

    }

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi textUi) {
        if (index < 0 || index >= storage.size()) {
            textUi.showMessage("Klalopz can't find the task :(");
            return;
        }
        Task currTask = storage.getTask(index);

        currTask.setTag(tag);

        dataStorage.save(storage);

        textUi.showMessage("Klalopz has added this tag to the following task:\n" + currTask);
        textUi.showLine();
    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
