package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.tasks.ToDo;
import klalopz.ui.TextUi;

/**
 * Represents an instruction to add a To-Do task without a specific date.
 */
public class ToDoInstruction implements Instruction {

    public String arguments;

    /**
     * Constructs a ToDoInstruction with the given arguments.
     * The argument should contain the description of the To-Do task.
     *
     * @param arguments Input string containing the task description.
     */
    public ToDoInstruction(String arguments) {
        this.arguments = arguments.trim();
    }

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi ui) {
        Task currTask = new ToDo(arguments, Boolean.FALSE);
        storage.addTask(currTask);
        dataStorage.save(storage);

        ui.showMessage(addedTask + " \n" + currTask);
        ui.showMessage("Now you have " + storage.size() + " tasks in the list.");
        ui.showLine();
    }
    @Override
    public boolean doIExit() {
        return false;
    }
}
