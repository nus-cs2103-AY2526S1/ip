package com.neokortex.commands.impl;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.Storage;
import com.neokortex.task.TaskList;



/**
 * Saves the taskList to the directory specified by path.
 *
 * <p>
 * The {@code SaveCommand} attempts to save the current {@link TaskList}
 * to the location specified by the path via the {@link Storage}. If successful,
 * the new default path in {@link Storage} will be changed to this path. Failure
 * just leads to a non-successful {@link ResponseStatus}.
 * </p>
 *
 * <p>
 * If the path specified is empty, it will just use the default path within the
 * {@link Storage}
 * </p>
 *
 * @see Command
 * @see Storage
 * @see TaskList
 */
public class SaveCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final String path;

    /**
     * Constructs an Save from the given {@link Storage}, {@link TaskList} and path.
     * The command will attempt to save the {@link TaskList} to the corresponding to
     * corresponding path via the {@link Storage}. This also sets the location of the
     * {@link Storage} to path if successful.
     *
     * @param storage the storage handler of the program
     * @param taskList the {@link TaskList} to store
     * @param path the path to procide the {@link Storage} to save the {@link TaskList}
     */
    public SaveCommand(Storage storage, TaskList taskList, String path) {
        this.taskList = taskList;
        this.storage = storage;
        this.path = path;
    }

    @Override
    public CommandResponse execute() {
        if (!this.path.isEmpty()) {
            try {
                storage.setFilePath(Path.of(this.path));
            } catch (InvalidPathException e) {
                return new CommandResponse(DialoguePath.INVALID_PATH, ResponseStatus.TOTAL_FAILURE);
            }
        }

        try {
            this.storage.saveListToStorage(this.taskList);
        } catch (IOException e) {
            return new CommandResponse(DialoguePath.UNABLE_TO_SAVE_TO_STORAGE, ResponseStatus.SUCCESS);
        }

        return new CommandResponse(DialoguePath.SUCCESSFULLY_SAVED_TASK, ResponseStatus.SUCCESS);
    }
}
