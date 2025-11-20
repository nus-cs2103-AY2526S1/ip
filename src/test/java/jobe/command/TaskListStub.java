package jobe.command;

import java.util.List;

import jobe.task.Task;
import jobe.task.TaskList;

/**
 * Represents a mock TaskList object for testing purposes.
 */
public class TaskListStub extends TaskList {
    
    private Task lastTask;
    
    @Override
    public Task markTask(int index) {
        this.lastTask = new Task("mark test passed");
        return lastTask;
    }
    
    @Override
    public Task unmarkTask(int index) {
        this.lastTask = new Task("unmark test passed");
        return lastTask;
    }
    
    @Override
    public Task createTodoTask(String taskDescription) {
        this.lastTask = new Task("todo test passed");
        return lastTask;
    }
    
    @Override
    public Task createDeadlineTask(String taskDescription, String deadline) {
        this.lastTask = new Task("deadline test passed");
        return lastTask;
    }
    
    @Override
    public Task createEventTask(String taskDescription, String startDate, String endDate) {
        this.lastTask = new Task("event test passed");
        return lastTask;
    }
    
    @Override
    public Task deleteTask(int index) {
        this.lastTask = new Task("delete test passed");
        return lastTask;
    }
    
    @Override
    public List<Task> findTask(String keyword) {
        this.lastTask = new Task("find test passed");
        return List.of(this.lastTask);
    }
    
    public Task getLastTask() {
        return this.lastTask;
    }
}
