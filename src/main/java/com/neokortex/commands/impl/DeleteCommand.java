package com.neokortex.commands.impl;

import java.io.IOException;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.Storage;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;

/**
 * Deletes the specified {@link Task} from the {@link TaskList} based on the provided taskId.
 *
 * <p>
 * The {@link DeleteCommand} then attempts to save the altered {@link TaskList} to {@link Storage}.
 * </p>
 *
 * <p>
 * Failure to save to Storage will result in the command entering a failed execution state, indicated
 * by {@link ResponseStatus}
 * </p>
 *
 * @see TaskList
 * @see Storage
 * @see Command
 */
public class DeleteCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final int taskId;

    /**
     * Constructs an DeleteCommand from the given {@link Storage}, {@link TaskList} and taskId.
     * The command will attempt to remove the {@link Task} corresponding to the given taskId
     * from the {@link TaskList} and save the altered list to {@link Storage}
     *
     * @param storage a reference to the storage to store the new {@link TaskList}
     * @param taskList a reference to the {@link TaskList} where we will delete the
     *                 corresponding task
     * @param taskId the taskNumber as it appears on the list. The user expects
     *               1-based indexing, but behind the code uses 0-based indexing
     *               Hence we subtrack taskId by 1 before storing.
     */
    public DeleteCommand(Storage storage, TaskList taskList, int taskId) {
        this.taskList = taskList;
        this.storage = storage;
        this.taskId = taskId - 1;
    }

    @Override
    public CommandResponse execute() {
        CommandResponse response;

        if (this.taskId > this.taskList.size()) {
            response = new CommandResponse(DialoguePath.TASK_ID_OOB, ResponseStatus.SUCCESS);
            response.attachResults(new String[]{Integer.toString(this.taskId + 1)});
            return response;
        }

        Task task = this.taskList.get(this.taskId);
        taskList.remove(taskId);

        try {
            storage.saveListToStorage(this.taskList);
        } catch (IOException e) {
            response = new CommandResponse(DialoguePath.DELETE_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {task.getDescription()});
            return response;
        }
        response = new CommandResponse(DialoguePath.SUCCESSFULLY_DELETED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {task.toString()});
        return response;

    }
}
