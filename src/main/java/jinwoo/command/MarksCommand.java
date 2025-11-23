package jinwoo.command;


import jinwoo.exception.DukeException;
import jinwoo.storage.Storage;
import jinwoo.tasks.TaskList;
import jinwoo.util.Ui;

/**
 * Mark, Unmark command.
 */
public class MarksCommand extends Command {

    public MarksCommand(String[] fullCommand) {
        super(fullCommand);
    }

    /**
     * Marks or Unmarks a task.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(getCommand()[1]) - 1;
            if (getCommand()[0].equals("mark")) {
                tasks.markTask(taskNum);
                ui.printMark(tasks, taskNum);
                storage.saveTasks(tasks.getTasks());
            } else if (getCommand()[0].equals("unmark")) {
                tasks.unmarkTask(taskNum);
                ui.printUnmark(tasks, taskNum);
                storage.saveTasks(tasks.getTasks());
            } else {
                throw new DukeException("Warning ???: wrong command to mark/unmark.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Warning ???: wrong task number to mark.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Warning ???: task number to mark is not in list.");
        }
    }
}
