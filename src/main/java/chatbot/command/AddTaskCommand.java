package chatbot.command;

import java.io.IOException;

import chatbot.exception.BotException;
import chatbot.storage.TaskStorage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * AddTaskCommand implements the CommandExecutor interface and handles To Do, Deadline and Event commands.
 */
public class AddTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;
    private final TaskStorage taskStorage;

    /**
     * Constructor, initializes class variables.
     *
     * @param taskList List of tasks the user has added; must not be null.
     * @param ui User interface where the responses to commands are displayed; must not be null.
     * @param taskStorage Persistent storage for user's tasks.
     */
    public AddTaskCommand(TaskList taskList, UI ui, TaskStorage taskStorage) {
        assert taskList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        this.taskList = taskList;
        this.ui = ui;
        // Storage might be null (problem is handled later in the code), no assertions needed
        this.taskStorage = taskStorage;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        Task newTask = this.taskList.addTask(taskDescription);
        String response;

        if (this.taskStorage == null) {
            return "I can't find my storage so I basically forgot what you just said";
        }

        try {
            taskStorage.updateStorage(this.taskList.getAllTasks());
        } catch (IOException e) {
            response = "I didn't quite catch that, less work for me I guess";
            return response;
        }

        response = ui.addTaskResponse(newTask);
        return response;
    }
}
