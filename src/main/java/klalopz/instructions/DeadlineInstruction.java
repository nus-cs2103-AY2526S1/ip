package klalopz.instructions;

import klalopz.exceptions.KlalopzException;
import klalopz.storage.DataStorage;
import klalopz.tasks.Deadline;
import klalopz.tasks.Task;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

import java.time.LocalDate;

/**
 * Represents a deadline instruction that adds a task with a due date.
 * The instruction parses an input string to extract task details and the deadline,
 * and can execute the addition of the task to the task list and storage.
 */

public class DeadlineInstruction implements Instruction {
        public String arguments;
        private final LocalDate dueDate;
        private final String details;
        
        /**
         * Constructs a DeadlineInstruction from the given arguments string.
         * The expected format is "description / date", where description is the task
         * details and date follows the {inputDateFormat}.
         *
         * @param arguments Input string containing the task description and due date.
         */
        public DeadlineInstruction(String arguments) {
            this.arguments = arguments;
            String[] tempStorage = arguments.split("/", 2);


            this.details = tempStorage[0].trim();
            this.dueDate = LocalDate.parse(tempStorage[1].trim(), Instruction.inputDateFormat);
        }

        @Override
        public void execute(TaskList storage, DataStorage dataStorage, TextUi textUi) {
            Task currTask = new Deadline(details, Boolean.FALSE, dueDate);
            storage.addTask(currTask);
            dataStorage.save(storage);

            textUi.showMessage(Instruction.addedTask + " \n" + currTask);
            textUi.showMessage("Now you have " + storage.size() + " tasks in the list.");
            textUi.showLine();
        }
        @Override
        public boolean doIExit() {
            return false;
        }
    }
