package chiikawa.command;

import chiikawa.ChiikawaException;
import chiikawa.Parser;
import chiikawa.Storage;
import chiikawa.TaskList;
import chiikawa.Ui;
import chiikawa.task.EventTask;

/**
 * Class for adding a new event task.
 */
public class AddEventCommand extends Command {
    private String[] taskInfoArray;

    /**
     * Constructor that takes in String representation of the taskInfo and converts it to String array.
     *
     * @param taskInfo String representation of the rest of the user's input.
     */
    public AddEventCommand(String taskInfo) {
        this.taskInfoArray = Parser.parseTaskInfo(taskInfo, "/", 3);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChiikawaException {
        if (taskInfoArray.length != 3) {
            throw new ChiikawaException("you no give enuff info!! more more!!!!");
        }

        for (String s: taskInfoArray) {
            if (s.trim().isEmpty()) { // Copilot suggested to trim first to rid of "  " cases!
                throw new ChiikawaException("blank!! u gave me blanks!!");
            }
        }

        EventTask newEventTask = new EventTask(
                this.taskInfoArray[0],
                this.taskInfoArray[1],
                this.taskInfoArray[2]);
        tasks.addTask(newEventTask);
        return ui.showAddTask(newEventTask);
    }
}
