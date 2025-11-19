package goober.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import goober.task.Task;

/**
 * Serializable container for persistent data.
 */
public class SaveData implements Serializable {
    private final List<Task> taskList = new ArrayList<>();

    /**
     * Returns task list.
     *
     * @return task list
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns tasks that contain query in their description.
     * @param query search query
     * @return search result
     */
    public List<Task> searchTask(String query) {
        List<Task> output = new ArrayList<>();
        for (Task t : taskList) {
            String desc = t.getDescription();
            if (desc != null && desc.toLowerCase().contains(query)) {
                output.add(t);
            }
        }
        return output;
    }
}
