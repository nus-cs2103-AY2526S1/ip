package klalopz.instructions;

import klalopz.storage.DataStorage;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class InvalidInstruction implements Instruction {

    public String message;

    public InvalidInstruction (String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi textUi) {
        textUi.showMessage(message);
    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
