package chatbot.command;

import chatbot.exception.BotException;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * FindTaskCommand implements CommandExecutor interface and handles finding of tasks by keywords
 */
public class FindTasksCommand implements CommandExecutor {
    private final TaskList taskList;
    private final chatbot.ui.UI ui;

    /**
     * Constructor, initializes class variables.
     *
     * @param taskList List of tasks the user has added; must not be null.
     * @param ui User interface where the responses to commands are displayed; must not be null.
     */
    public FindTasksCommand(TaskList taskList, UI ui) {
        assert taskList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        this.taskList = taskList;
        this.ui = ui;
    }

    private String parseTaskArray(Task[] tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            int idx = i + 1;
            Task curTask = tasks[i];
            String task = idx + "." + curTask + "\n";
            sb.append(task);
        }

        if (sb.isEmpty()) {
            return "Theres nothing";
        }

        return sb.toString();
    }

    @Override
    public String execute(String search) throws BotException {
        Task[] matchingTasks = taskList.findTasks(search);
        return ui.findTaskResponse(parseTaskArray(matchingTasks));
    }
}
