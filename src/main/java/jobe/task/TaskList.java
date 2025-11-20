package jobe.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jobe.exception.JobeException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    
    private List<Task> listOfTasks;
    private int numOfTasks;
    
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }
    
    /**
     * Marks a task corresponding to the input index as done.
     *
     * @param index Index of task user wants to mark as done.
     * @return Task which has been marked as done.
     * @throws JobeException If user entered an index which does not correspond to any task.
     */
    public Task markTask(int index) throws JobeException {
        if (index > this.size()) {
            throwIndexOutOfBoundsException();
        }
        
        Task task = this.getTask(index);
        task.setDone();
        return task;
    }
    
    /**
     * Unmarks a task corresponding to the input index as done.
     *
     * @param index Index of task user wants to mark as undone.
     * @return Task which has been marked as undone.
     * @throws JobeException If user entered an index which does not correspond to any task.
     */
    public Task unmarkTask(int index) throws JobeException {
        if (index > this.size()) {
            throwIndexOutOfBoundsException();
        }
        
        Task task = this.getTask(index);
        task.setUndone();
        return task;
    }
    
    /**
     * Creates a todo task.
     *
     * @param taskDescription Description of task.
     * @return The created todo task.
     */
    public Task createTodoTask(String taskDescription) throws JobeException {
        Task task = new TodoTask(taskDescription);
        task.checkDuplicates(task, this);
        this.addTask(task);
        return task;
    }
    
    /**
     * Creates a deadline task.
     *
     * @param taskDescription Description of task.
     * @param deadline Deadline of task.
     * @return The created deadline task.
     * @throws JobeException If user entered the deadline in the wrong date/time format.
     */
    public Task createDeadlineTask(String taskDescription, String deadline) throws JobeException {
        Task task = DeadlineTask.createDeadlineTask(taskDescription, deadline);
        task.checkDuplicates(task, this);
        this.addTask(task);
        return task;
    }
    
    /**
     * Creates an event task.
     *
     * @param taskDescription Description of task.
     * @param startDate Start date/time of the event task.
     * @param endDate End date/time of the event task.
     * @return The created event task.
     * @throws JobeException if user entered the start/end date/time in the wrong date/time format.
     */
    public Task createEventTask(String taskDescription, String startDate, String endDate) throws JobeException {
        Task task = EventTask.createEventTask(taskDescription, startDate, endDate);
        task.checkDuplicates(task, this);
        this.addTask(task);
        return task;
    }
    
    /**
     * Deletes a task corresponding to the input index.
     *
     * @param index Index of task user wants to delete.
     * @return Task which has been deleted.
     * @throws JobeException If user entered an index which does not correspond to any task.
     */
    public Task deleteTask(int index) throws JobeException {
        if (index > this.size()) {
            throwIndexOutOfBoundsException();
        }
        
        Task task = this.getTask(index);
        this.removeTask(index);
        return task;
    }
    
    /**
     * Finds a task or tasks which contain the given keyword.
     *
     * @param keyword keyword that user input.
     * @return A list of tasks which contain the keyword.
     * @throws JobeException If there are no tasks containing the keyword.
     */
    public List<Task> findTask(String keyword) throws JobeException {
        List<Task> list = this.toStream()
                .filter(task -> task.getTaskDescription().contains(keyword))
                .toList();
        
        if (list.isEmpty()) {
            throw new JobeException("OOPS!!!! There are no matching tasks in your list!");
        }
        
        return list;
    }
    
    /**
     * Adds the input task into the list of tasks. Increments the total number of tasks.
     *
     * @param task A Task object.
     */
    public void addTask(Task task) {
        this.listOfTasks.add(task);
        this.numOfTasks++;
    }
    
    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }
    
    /**
     * Removes the input task from the list of tasks. Decrements the total number of tasks.
     *
     * @param index index corresponding to the task object.
     */
    public void removeTask(int index) {
        this.listOfTasks.remove(index);
        this.numOfTasks--;
    }
    
    /**
     * Converts task into stream format.
     *
     * @return A stream containing Task objects.
     */
    public Stream<Task> toStream() {
        return this.listOfTasks.stream();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Here are the tasks in your list:\n");
        int count = 1;
        for (Task task : listOfTasks) {
            str.append(count).append(". ").append(task).append("\n");
            count++;
        }
        return str.toString();
    }
    
    /**
     * Returns number of tasks.
     *
     * @return Number of tasks.
     */
    public int size() {
        return this.numOfTasks;
    }
    
    /**
     * Converts task list and individual files into a savable format for saving to a file.
     *
     * @return a String representation of all tasks.
     */
    public String convertToFileFormat() {
        return this.toStream()
                .map(Task::convertToFileFormat)
                .collect(Collectors.joining("\n"));
    }
    
    private void throwIndexOutOfBoundsException() throws JobeException {
        throw new JobeException("OOPS!!!! There are tasks which match the input index in your list!");
    }
}
