package chiikawa.command;

import chiikawa.ChiikawaException;
import chiikawa.Parser;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.Task;
import chiikawa.task.Task.Priority;

public class UpdatePriorityCommand extends Command {
    private String[] taskInfoArray;

    /**
     * Constructor that takes in a String representation of the rest of the user's command.
     *
     * @param command String representation of the rest of the user's command.
     */
    public UpdatePriorityCommand(String command) {
        this.taskInfoArray = Parser.parseTaskInfo(command, " ", 2);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException {
        if (taskInfoArray.length != 2) {
            throw new ChiikawaException("you no give enuff info!! more more!!!!");
        }

        try {
            int index = Integer.parseInt(this.taskInfoArray[0]);
            String priorityStr = this.taskInfoArray[1].strip().toUpperCase();
            Priority priority = Priority.valueOf(priorityStr);

            if (index > Task.getTaskCount() || index <= 0) {
                throw new ChiikawaException("no such task, wat u doin!!");
            }

            Task updatedTask = tasks.updatePriorityTask(index, priority);
            return ui.showUpdatePriority(updatedTask);

        } catch (NumberFormatException e) {
            throw new ChiikawaException("giv 1 numba!! then u sayy HIGH or LOW!!! okee?");
        } catch (IllegalArgumentException e) {
            throw new ChiikawaException("priowity can oni be HIGH or LOW!! HIGH!! LOW!!");
        }
    }
}
