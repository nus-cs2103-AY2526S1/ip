package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class FindInstruction implements Instruction {
    public String arguments;

    public FindInstruction(String input) {
        this.arguments = input.trim();
    }
    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        boolean areAnyWordsFound = false;

        ui.showMessage("Klalopz has found the Tasks containing " +  arguments + ":");
        for (int i = 0; i < storage.size(); i++) {
            Task task = storage.getTask(i);

            if (task.getDetails().toLowerCase().contains(arguments.toLowerCase()) ||
                task.getTag().toString().toLowerCase().contains(arguments.toLowerCase())) {
                ui.showMessage((i + 1) + ". " + task);
                areAnyWordsFound = true;
            }
        }

        if (!areAnyWordsFound) {
            ui.showMessage("No tasks are found");
        }

        ui.showLine();
    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
