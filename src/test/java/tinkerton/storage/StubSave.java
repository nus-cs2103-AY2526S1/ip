package tinkerton.storage;

import tinkerton.task.TaskList;

public class StubSave extends Save {
    private int saveCallCount = 0;
    private TaskList lastSavedTaskList = null;

    public StubSave() {
        super("");
    }

    @Override
    public void save(TaskList tasks) {
        saveCallCount++;
        lastSavedTaskList = tasks;
    }

    public int getSaveCallCount() {
        return saveCallCount;
    }

    public TaskList getLastSavedTaskList() {
        return lastSavedTaskList;
    }
}
