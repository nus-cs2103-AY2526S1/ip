package klalopz.instructions;

import klalopz.storage.DataStorage;
import klalopz.exceptions.KlalopzException;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

/**
 * Represents an instruction that terminates the application.
 * When executed, it triggers the UI closing message and signals
 * the program to exit.
 */
public class ExitInstruction implements Instruction {
    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        ui.sayClosing();
    }

    @Override
    public boolean doIExit() {
        return true;
    }
}
