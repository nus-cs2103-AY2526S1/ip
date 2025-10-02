package com.neokortex.task;

import java.util.List;

/**
 * Represents a container class that stores the results of loading tasks from storage.
 * <p>
 * The {@code ListLoadWrapper} class holds both successfully loaded tasks and
 * failed lines extracted from the deserialization of a {@code TaskList} process.
 * It provides a structured way to handle scenarios where some lines were unable to
 * be parsed whilst still enabling the successfully parsed Tasks to be stored.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *     <li>Encapsulate successful task loading results</li>
 *     <li>Track failed lines for error reporting</li>
 * </ul>
 *
 * @see TaskList
 * @see com.neokortex.core.Storage#loadListFromStorage()
 */
public class ListLoadWrapper {
    private final TaskList taskList;
    private final List<String> failedSerializations;

    /**
     * Constructs a new {@code ListLoadWrapper} with its results.
     *
     * @param taskList the successfully loaded tasks.
     * @param failedSerializations the list of string lines that could not be parsed.
     */
    public ListLoadWrapper(TaskList taskList, List<String> failedSerializations) {
        this.taskList = taskList;
        this.failedSerializations = failedSerializations;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public List<String> getFailedSerializations() {
        return failedSerializations;
    }
}
