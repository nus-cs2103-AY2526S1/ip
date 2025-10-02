package com.neokortex.commands.impl;

import java.io.IOException;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.Storage;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;


/**
 * Marks tasks as completed in the task list.
 * <p>
 * The {@code MarkCommand} allows users to mark specific tasks as done by their
 * index (which can be checked by calling the {@link ListCommand}). It validates
 * the task ID, updates the task status, and persists the updated list in storage.
 * </p>
 *
 * <p><b>Visual Change:</b></p>
 * <pre>
 * Before: [T][ ] Buy groceries
 * After:  [T][X] Buy groceries
 * </pre>
 *
 * @see Command
 */
public class MarkCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final int taskId;

    /**
     * Constructs a new {@code MarkCommand} with the {@link Storage}, {@link TaskList},
     * and taskId
     *
     * @param storage a reference to the storage to store the new {@link TaskList}
     * @param taskList a reference to the {@link TaskList} where we will mark the
     *                 corresponding task
     * @param taskId the taskNumber as it appears on the list. The user expects
     *               1-based indexing, but behind the code uses 0-based indexing
     *               Hence we subtrack taskId by 1 before storing.
     */
    public MarkCommand(Storage storage, TaskList taskList, int taskId) {
        this.storage = storage;
        this.taskList = taskList;
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
        if (task.isMarked()) {
            response = new CommandResponse(DialoguePath.TASK_ALREADY_MARKED, ResponseStatus.SUCCESS);
            response.attachResults(new String[]{Integer.toString(this.taskId + 1)});
            return response;
        }
        this.taskList.markTask(taskId);

        try {
            storage.saveListToStorage(this.taskList);
        } catch (IOException e) {
            response = new CommandResponse(DialoguePath.MARK_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {task.getDescription()});
            return response;
        }

        response = new CommandResponse(DialoguePath.SUCCESSFULLY_MARKED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {task.getDescription()});
        return response;
    }
}
