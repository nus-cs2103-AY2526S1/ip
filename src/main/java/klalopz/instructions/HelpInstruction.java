package klalopz.instructions;

import klalopz.storage.DataStorage;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class HelpInstruction implements Instruction {

    @Override
    public void execute(TaskList storage, DataStorage dataStorage, TextUi textUi) {
        textUi.showLine();
        textUi.showMessage(
                "LIST: Displays all tasks in your list.\n" +
                        "MARK: Marks a task as completed. Usage: mark {task_number}\n" +
                        "UNMARK: Marks a task as not completed. Usage: unmark {task_number}\n" +
                        "FIND: Finds tasks containing a keyword. Usage: find {keyword}\n" +
                        "DEADLINE: Adds a task with a due date. Usage: deadline {details} / {dd-MM-yyyy}\n" +
                        "EVENT: Adds a task with a start and end date. Usage: event {details} / {startDate} / {endDate} (dd-MM-yyyy)\n" +
                        "TODO: Adds a simple task without a date. Usage: todo {details}\n" +
                        "DELETE: Removes a task. Usage: delete {task_number}\n" +
                        "ADD_TAG: Adds a tag to a task. Usage: add_tag {task_number} {tag_name_or_id}\n" +
                        "DELETE_TAG / REMOVE_TAG: Removes a tag from a task. Usage: delete_tag {task_number} {tag_name_or_id}\n" +
                        "EXIT: Exits the application."
        );
        textUi.showLine();
    }

    @Override
    public boolean doIExit() {
        return false;
    }
}
