package panda.action;

import panda.FileManager;
import panda.PandaEventDateTimeChecker;
import panda.PandaException;
import panda.PandaUi;
import panda.TaskList;
import panda.task.Deadline;
import panda.task.Event;
import panda.task.ToDo;

/**
 * AddAction is a subclass of Action, and handles actions regarding
 * ToDo, Deadline and Event task.
 */
public class AddAction extends Action {
    private final String action;
    private final String[] taskInfo;

    /**
     * Constructs AddAction with the specific action and taskInfo.
     *
     * @param action ToDo, Deadline or Event.
     * @param taskInfo Information regarding the task.
     */
    public AddAction(String action, String[] taskInfo) {
        this.action = action;
        this.taskInfo = taskInfo;
    }

    /**
     * Creates the task with the info provided, add them to the list of tasks,
     * then informs user. Text file is also updated.
     *
     * @param tasks List to add the task to.
     * @param ui User Interface to communicate with user.
     * @param fileManager File manager to save the tasks to text file.
     * @throws PandaException If taskInfo is invalid.
     */
    public void execute(TaskList tasks, PandaUi ui, FileManager fileManager) throws PandaException {
        if (action.equals("todo")) {
            ui.showTaskAdded(tasks.addTask(new ToDo(taskInfo[0])), tasks);
        } else if (action.equals("deadline")) {
            ui.showTaskAdded(tasks.addTask(new Deadline(taskInfo[0], taskInfo[1])), tasks);
        } else if (action.equals("event")) {
            Event event = new Event(taskInfo[0], taskInfo[1], taskInfo[2]);
            if (PandaEventDateTimeChecker.hasMismatchedStartAndEnd(event)) {
                throw new PandaException("MismatchedDateTime", "");
            }
            if (PandaEventDateTimeChecker.hasNoOverlapWithOtherEvents(event, tasks.getTasks())) {
                ui.showTaskAdded(tasks.addTask(event), tasks);
            } else {
                throw new PandaException("OverlappingEvents", "");
            }
        }
        fileManager.saveTasks(tasks, ui);
    }

    public boolean isExit() {
        return false;
    }
}
