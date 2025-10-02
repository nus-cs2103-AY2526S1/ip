package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;

/**
 * Command for searching tasks by keyword in their descriptions.
 *
 * <p>
 * The {@code FindCommand} searches through all tasks in the task list and displays
 * all tasks that contain the specified keyword in their description. The search
 * is not case-sensitive and result is formatted as a {@link TaskList}, similar to
 * in a {@link ListCommand}.
 * </p>
 *
 * <p><b>Search Behavior:</b></p>
 * <ul>
 *   <li>Case-insensitive keyword matching</li>
 *   <li>Searches within task descriptions</li>
 *   <li>Returns tasks containing the keyword</li>
 *   <li>Displays results as a {@link TaskList}</li>
 *   <li>Shows appropriate message when no matches are found</li>
 * </ul>
 *
 * <p><b>Output Format:</b></p>
 * <p>Input: find groce</p>
 * <pre>
 * Here you go!
 * 1. [T][ ] Buy groceries
 * 2. [D][X] grocery shopping by tomorrow
 * </pre>
 *
 * @see Command
 * @see TaskList#getTasksContainingKeyword(String)
 * @see Task#containsKeyword(String)
 */
public class FindCommand implements Command {
    private final TaskList taskList;
    private String keyword;

    /**
     * Constructs a new {@code FindCommand} based on the given {@link TaskList} and keyword
     *
     * @param tasklist the tasklist to search for the keyword in
     * @param keyword the keyword to search for within the tasklist
     */
    public FindCommand(TaskList tasklist, String keyword) {
        this.taskList = tasklist;
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() {
        TaskList uniqueList = this.taskList.getTasksContainingKeyword(keyword);
        if (uniqueList.isEmpty()) {
            return new CommandResponse(DialoguePath.FIND_RETURNS_EMPTY_LIST, ResponseStatus.SUCCESS);
        }

        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < uniqueList.size(); i++) {
            taskListString.append(String.format("%d. %s", i + 1, uniqueList.get(i).toString()));
            taskListString.append(System.lineSeparator());
        }

        CommandResponse response = new CommandResponse(DialoguePath.FIND_SUCCESSFUL, ResponseStatus.SUCCESS);
        response.attachResults(new String[]{
            this.keyword,
            taskListString.toString()
        });

        return response;
    }
}
