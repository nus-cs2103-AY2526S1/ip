package com.neokortex.task;

import java.util.ArrayList;

/**
 * Represents a specialized list implementation for managing {@link Task} objects.
 * <p>
 * The {@code TaskList} class extends {@link ArrayList} to provide task-specific
 * functionality, like marking/unmarking specific tasks within the list,
 * searching for a task by keyword, and provide better formatting for a list of
 * {@code Task}. This class serves as the primary container for {@code Task} management.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *     <li>Bulk task operations (mark/unmark)</li>
 *    <li>Task search based on keyword</li>
 *     <li>Formatted string representation of a list of {@code Task}s</li>
 * </ul>
 *
 * <p><b>Credit: documentation was written with help from generative AI</b></p>
 * @see Task
 * @see ArrayList
 */
public class TaskList extends ArrayList<Task> {
    public boolean checkValidID(int id) {
        return id >= 0 && id < super.size();
    }

    public void markTask(int id) {
        super.get(id).mark();
    }

    public void unmarkTask(int id) {
        super.get(id).unmark();
    }

    public String getTaskDescription(int id) {
        return super.get(id).toString();
    }

    public TaskList getDeepCopy() {
        TaskList copy = new TaskList();
        for (Task task : this) {
            copy.add(task);
        }
        return copy;
    }

    /**
     * Returns a new {@code TaskList} containing all tasks that contains the given keyword
     * in its description.
     * <p>
     * The search is not case-sensitive. If no tasks contain the keyword, an empty
     * {@code TaskList} is returned.
     * </p>
     *
     * @param keyword the keyword to search for.
     * @return a new {@code TaskList} containing matching tasks
     * @see Task#containsKeyword(String)
     */
    public TaskList getTasksContainingKeyword(String keyword) {
        TaskList taskList = new TaskList();
        for (Task task : this) {
            if (task.containsKeyword(keyword)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Returns a formatted string representation of the {@code Task}s in this list.
     * <p>
     * Format:
     * <pre>
     * 1. [T][ ] Task description
     * 2. [D][X] Another task
     * ...
     * </pre>
     * Each task is numbered sequentially starting from 1.
     * </p>
     *
     * @return a formatted string showing all {@code Task}s in this list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }
        return sb.toString();
    }
}
