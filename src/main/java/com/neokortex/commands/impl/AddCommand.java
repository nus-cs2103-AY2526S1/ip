package com.neokortex.commands.impl;

import java.io.IOException;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.Storage;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;

/**
 * Places the specified {@link Task} into the {@link TaskList}
 *
 * <p>
 * The {@link AddCommand} attempts to add a {@link Task} into the {@link TaskList}. The command
 * then attempts to save the new {@link TaskList} to {@link Storage}.
 * </p>
 *
 * <p>
 * Failure to save to Storage will result in the command entering a failed execution state, indicated
 * by {@link ResponseStatus}
 * </p>
 *
 * @see TaskList
 * @see Storage
 * @see Task
 * @see Command
 */
public class AddCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private Task task;


    /**
     * Constructs an AddCommand from the given {@link Storage}, {@link TaskList} and {@link Task}.
     * The command will attempt to add the {@link Task} to the {@link TaskList} and save to {@link Storage}
     *
     * @param storage the storage handler of the program
     * @param taskList the {@link TaskList} to store the task to
     * @param task the {@link Task} to add to the {@link TaskList}
     */
    public AddCommand(Storage storage, TaskList taskList, Task task) {
        this.storage = storage;
        this.taskList = taskList;
        this.task = task;
    }


    @Override
    public CommandResponse execute() {
        CommandResponse response;

        this.taskList.add(this.task);

        try {
            this.storage.saveListToStorage(this.taskList);
        } catch (IOException e) {
            response = new CommandResponse(DialoguePath.ADD_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {task.getDescription()});
            return response;
        }

        response = new CommandResponse(DialoguePath.SUCCESSFULLY_ADDED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {this.task.toString()});
        return response;

    }
}
