package jobe.command;

import jobe.storage.Storage;
import jobe.task.TaskList;

/**
 * Represents a mock storage object for testing purposes.
 */
public class StorageStub extends Storage {
    
    private boolean isTaskSaved = false;
    
    public StorageStub() {
        super(new TaskList()); // still needs to call super, but you can ignore the taskList
    }
    
    @Override
    public void saveTasks(TaskList taskList) {
        this.isTaskSaved = true;
    }
    
    @Override
    public void loadTasks(TaskList taskList) {
    }
    
    public boolean isTaskSaved() {
        return this.isTaskSaved;
    }
}


