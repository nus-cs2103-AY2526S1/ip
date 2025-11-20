package jobe.command;

import java.util.List;

import jobe.task.Task;
import jobe.task.TaskList;
import jobe.ui.Ui;

/**
 * Represents a mock Ui object for testing purposes.
 */
public class UiStub extends Ui {

    private boolean isResponseCalled = false;
    
    @Override
    public void showHelloResponse() {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showDeadlineResponse(Task newTask, TaskList taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showDeleteResponse(Task deletedTask, TaskList taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showEventResponse(Task newTask, TaskList taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showFindResponse(List<Task> taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showListResponse(TaskList taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showMarkResponse(Task task) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showTodoResponse(Task newTask, TaskList taskList) {
        this.isResponseCalled = true;
    }
    
    @Override
    public void showUnmarkResponse(Task task) {
        this.isResponseCalled = true;
    }
    
    public boolean isResponseCalled() {
        return this.isResponseCalled;
    }
}
