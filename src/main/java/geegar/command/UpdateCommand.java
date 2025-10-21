package geegar.command;

import java.time.LocalDateTime;

import geegar.exception.GeegarException;
import geegar.exception.InvalidTaskNumberException;
import geegar.gui.Gui;
import geegar.storage.Storage;
import geegar.task.Deadline;
import geegar.task.Event;
import geegar.task.Task;
import geegar.task.TaskList;
import geegar.task.Todo;

/**
 * A command that updates an existing tasks' description or dates
 */
public class UpdateCommand extends Command {

    private int taskNumber;
    private String newDescription;
    private LocalDateTime newBy;
    private LocalDateTime newFrom;
    private LocalDateTime newTo;

    /**
     * Constructor for updating a Todo Task Description
     */
    public UpdateCommand(int taskNumber, String newDescription) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
    }

    /**
     * Constructor for updating a Deadline Task
     */
    public UpdateCommand(int taskNumber, String newDescription, LocalDateTime newBy) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
        this.newBy = newBy;
    }

    /**
     * Constructor for updating a Event Task
     */
    public UpdateCommand(int taskNumber, String newDescription, LocalDateTime newFrom, LocalDateTime newTo) {
        this.taskNumber = taskNumber;
        this.newDescription = newDescription;
        this.newFrom = newFrom;
        this.newTo = newTo;
    }

    @Override
    public void execute(TaskList tasks, Gui gui, Storage storage) throws GeegarException {
        assert tasks != null : "TaskList must not be null";
        assert gui != null : "Gui must not be null";
        assert storage != null : "Storage must not be null";
        int taskIndex = this.taskNumber - 1;

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new InvalidTaskNumberException(String.valueOf(this.taskNumber));
        }
        Task taskToUpdate = tasks.get(taskNumber - 1);
        Task updatedTask = createUpdatedTask(taskToUpdate);

        tasks.delete(taskNumber - 1);
        tasks.getTasks().add(taskNumber - 1, updatedTask);

        storage.save(tasks.getTasks());

        gui.printTaskAdded(updatedTask, tasks.size());

    }

    /**
     * Creates an updated version of the task based on the task type
     */
    private Task createUpdatedTask(Task originalTask) throws GeegarException {
        boolean isCompleted = originalTask.isDone();

        if (originalTask instanceof Todo) {

            String description = newDescription != null ? newDescription : originalTask.getDescription();
            Todo updatedTodo = new Todo(description, isCompleted);
            return updatedTodo;

        } else if (originalTask instanceof Deadline) {

            Deadline originalDeadline = (Deadline) originalTask;
            String description = newDescription != null ? newDescription : originalTask.getDescription();
            LocalDateTime by = newBy != null ? newBy : originalDeadline.getBy();

            Deadline updatedDeadline = new Deadline(description, by, isCompleted);
            return updatedDeadline;

        } else if (originalTask instanceof Event) {

            Event originalEvent = (Event) originalTask;
            String description = newDescription != null ? newDescription : originalTask.getDescription();
            LocalDateTime from = newFrom != null ? newFrom : originalEvent.getFrom();
            LocalDateTime to = newTo != null ? newTo : originalEvent.getTo();

            Event updatedEvent = new Event(description, from, to, isCompleted);
            return updatedEvent;
        }

        throw new GeegarException("Unknown task type cannot be updated!");
    }
}
