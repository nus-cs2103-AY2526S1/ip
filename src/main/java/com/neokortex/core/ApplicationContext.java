package com.neokortex.core;

import com.neokortex.task.TaskList;

/**
 * Represents a container class that holds the core application state and components.
 * <p>
 * the {@code ApplicationContext} serves as a central repository for all fundamental
 * application components, providing a single access point for shared state and services.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *     <li>Encapsulates the application's state during runtime</li>
 *     <li>Centralizes access to core application components</li>
 *     <li>Provides access to application state</li>
 *     <li>Hold the condition for the program to keep running</li>
 * </ul>
 *
 * <p><b>Components:</b></p>
 * <ul>
 *     <li>{@link #getTaskList()}: Central task storage and management</li>
 *     <li>{@link #getStorage()}: Persistent data storage</li>
 * </ul>
 *
 * <p><b>Credit: documentation was written with help from generative AI</b></p>
 *
 * @see TaskList
 * @see Storage
 */
public class ApplicationContext {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a new ApplicationContext with the specified components.
     *
     * @param taskList the task list containing application tasks.
     * @param storage the storage component for persistence.
     * @throws NullPointerException if any parameter is null
     */
    public ApplicationContext(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
