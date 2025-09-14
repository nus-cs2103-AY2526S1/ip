package tsundere.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import tsundere.StorageStub;
import tsundere.TsundereException;
import tsundere.UiStub;
import tsundere.log.Log;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TaskList;
import tsundere.task.TodoTask;

public class UndoTest {
    private TaskList tasks = new TaskList();
    private UiStub ui = new UiStub();
    private StorageStub storage = new StorageStub();
    private Log log = new Log(tasks, ui, storage);

    @AfterEach
    public void reset() {
        tasks = new TaskList();
        ui = new UiStub();
        storage = new StorageStub();
        log = new Log(tasks, ui, storage);
    }

    @Test
    public void undo_addTaskCommand_correctlyDeletesTask() throws TsundereException {
        TodoTask todo = new TodoTask("taskname");

        AddTaskCommand addTodoTask = new AddTaskCommand(todo);
        addTodoTask.execute(tasks, ui, storage, log);

        TaskList expectedTasks = new TaskList();
        expectedTasks.add(todo);
        Log expectedLog = new Log(tasks, ui, storage);
        expectedLog.add(new AddTaskCommand(todo));

        assertEquals(expectedTasks, tasks, "task list should contain newly added task");
        assertEquals(expectedLog, log, "log should contain newly executed AddTaskCommand");

        log.undo();
        expectedTasks = new TaskList();
        expectedLog = new Log(tasks, ui, storage);

        assertEquals(expectedTasks, tasks, "task list should no longer contain newly added task");
        assertEquals(new Log(tasks, ui, storage), log, "log should have popped the AddTaskCommand");
    }

    @Test
    public void undo_deleteFirstTaskCommand_correctlyAddsTask() throws TsundereException {
        TodoTask todo = new TodoTask("todo1");
        DeadlineTask deadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask event = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        tasks.addAll(todo, deadline, event);

        DeleteCommand deleteFirstTask = new DeleteCommand(0);
        deleteFirstTask.execute(tasks, ui, storage, log);

        TaskList expectedTasks = new TaskList();
        expectedTasks.addAll(deadline, event);
        Log expectedLog = new Log(tasks, ui, storage);
        expectedLog.add(deleteFirstTask);

        assertEquals(expectedTasks, tasks, "task list should not contain deleted task");
        assertEquals(expectedLog, log, "log should contain newly executed DeleteCommand");

        log.undo();

        expectedTasks = new TaskList();
        expectedTasks.addAll(todo, deadline, event);
        expectedLog = new Log(tasks, ui, storage);

        assertEquals(expectedTasks, tasks, "task list should correctly"
                + " add back the deleted task in the correct position");
        assertEquals(expectedLog, log, "log should have popped the DeleteCommand");
    }

    @Test
    public void undo_deleteLastTaskCommand_correctlyAddsTask() throws TsundereException {
        TodoTask todo = new TodoTask("todo1");
        DeadlineTask deadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask event = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        tasks.addAll(todo, deadline, event);

        DeleteCommand deleteLastTask = new DeleteCommand(2);
        deleteLastTask.execute(tasks, ui, storage, log);

        TaskList expectedTasks = new TaskList();
        expectedTasks.addAll(todo, deadline);
        Log expectedLog = new Log(tasks, ui, storage);
        expectedLog.add(deleteLastTask);

        assertEquals(expectedTasks, tasks, "task list should not contain deleted task");
        assertEquals(expectedLog, log, "log should contain newly executed DeleteCommand");

        log.undo();

        expectedTasks = new TaskList();
        expectedTasks.addAll(todo, deadline, event);
        expectedLog = new Log(tasks, ui, storage);

        assertEquals(expectedTasks, tasks, "task list should correctly"
                + " add back the deleted task in the correct position");
        assertEquals(expectedLog, log, "log should have popped the DeleteCommand");
    }

    @Test
    public void undo_markCommand_correctlyUnmarksTask() throws TsundereException {
        TodoTask todo = new TodoTask("todo1");
        DeadlineTask deadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask event = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        tasks.addAll(todo, deadline, event);

        MarkCommand markFirstTask = new MarkCommand(0);
        markFirstTask.execute(tasks, ui, storage, log);

        TaskList expectedTasks = new TaskList();
        TodoTask expectedTodo = new TodoTask("todo1");
        expectedTodo.markDone();
        DeadlineTask expectedDeadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask expectedEvent = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        expectedTasks.addAll(expectedTodo, expectedDeadline, expectedEvent);
        Log expectedLog = new Log(tasks, ui, storage);
        expectedLog.add(markFirstTask);

        assertEquals(expectedTasks, tasks, "first task of task list should be marked");
        assertEquals(expectedLog, log, "log should contain newly executed MarkCommand");

        log.undo();

        expectedTasks.get(0).markUndone();
        expectedLog = new Log(tasks, ui, storage);

        assertEquals(expectedTasks, tasks, "first task of task list should become unmarked");
        assertEquals(expectedLog, log, "log should have popped the MarkCommand");
    }

    @Test
    public void undo_unmarkCommand_correctlyMarksTask() throws TsundereException {
        TodoTask todo = new TodoTask("todo1");
        todo.markDone();
        DeadlineTask deadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask event = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        tasks.addAll(todo, deadline, event);

        UnmarkCommand unmarkFirstTask = new UnmarkCommand(0);
        unmarkFirstTask.execute(tasks, ui, storage, log);

        TaskList expectedTasks = new TaskList();
        TodoTask expectedTodo = new TodoTask("todo1");
        DeadlineTask expectedDeadline = new DeadlineTask("deadline1", LocalDateTime.MIN);
        EventTask expectedEvent = new EventTask("event1", LocalDateTime.MIN, LocalDateTime.MAX);
        expectedTasks.addAll(expectedTodo, expectedDeadline, expectedEvent);
        Log expectedLog = new Log(tasks, ui, storage);
        expectedLog.add(unmarkFirstTask);

        assertEquals(expectedTasks, tasks, "first task of task list should be unmarked");
        assertEquals(expectedLog, log, "log should contain newly executed UnmarkCommand");

        log.undo();

        expectedTasks.get(0).markDone();
        expectedLog = new Log(tasks, ui, storage);

        assertEquals(expectedTasks, tasks, "first task of task list should become marked");
        assertEquals(expectedLog, log, "log should have popped the UnmarkCommand");
    }
}
