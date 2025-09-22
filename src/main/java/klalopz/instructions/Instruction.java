package klalopz.instructions;

import klalopz.storage.DataStorage;
import klalopz.exceptions.KlalopzException;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

import java.time.format.DateTimeFormatter;

/**
 * Represents a generic instruction that can be executed by the application.
 * Each instruction defines an action to perform on the task list and storage,
 * and may indicate whether it signals the application to exit.
 */
public interface Instruction {

    String addedTask = "Klalopz has added this task: ";
    DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /** Executes the instruction, modifying the task list and storage as needed. */
    void execute(TaskList storage, DataStorage dataStorage, TextUi textUi);

    /** Indicates whether this instruction should terminate the application. */
    boolean doIExit();

}
