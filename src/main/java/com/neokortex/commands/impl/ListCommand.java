package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.task.TaskList;

/**
 * Displays all tasks in the task list.
 *
 * <p>
 * The {@code ListCommand} shows a numbered list of all tasks currently stored.
 * Has special dialogue if the list is empty.
 * </p>
 *
 * <p><b>Output Format:</b></p>
 * <pre>
 * Here are the tasks on your list:
 * 1. [T][ ] Buy groceries
 * 2. [D][X] Submit report by tomorrow
 * 3. [E][ ] Meeting from 2pm to 3pm
 * </pre>
 *
 * @see Command
 * @see TaskList
 */
public class ListCommand implements Command {
    private final TaskList taskList;

    /**
     * Constructs a new ListCommand which prints the current {@link TaskList}.
     *
     * @param taskList the current {@link TaskList}
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public CommandResponse execute() {
        if (this.taskList.isEmpty()) {
            return new CommandResponse(DialoguePath.LIST_EMPTY, ResponseStatus.SUCCESS);
        }

        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            taskListString.append(String.format("%d. %s", i + 1, this.taskList.get(i).toString()));
            taskListString.append(System.lineSeparator());
        }

        CommandResponse response = new CommandResponse(DialoguePath.LIST_NON_EMPTY, ResponseStatus.SUCCESS);
        response.attachResults(new String[]{
                taskListString.toString()
        });

        return response;
    }
}
