package tsundere.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tsundere.StorageStub;
import tsundere.TsundereException;
import tsundere.UiStub;
import tsundere.log.Log;
import tsundere.storage.AbstractStorage;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TaskList;
import tsundere.task.TodoTask;
import tsundere.task.TsundereOutOfBoundsException;
import tsundere.ui.UiMessages;

public class DeleteTest {
    @Test
    public void delete_validIndex_correctlyDeletesTask() throws TsundereException, IOException {
        TaskList tasks = new TaskList();

        TodoTask toBeDeletedTask = new TodoTask("todo");
        EventTask task2 = new EventTask("event",
                LocalDateTime.parse("2026-01-01T23:59"),
                LocalDateTime.parse("2026-01-02T23:59"));
        DeadlineTask task3 = new DeadlineTask("deadline", LocalDateTime.parse("2026-01-01T23:59"));
        tasks.add(toBeDeletedTask);
        tasks.add(task2);
        tasks.add(task3);

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        storage.storeAll(tasks);
        Log log = new Log(tasks, ui, storage);
        DeleteCommand command = new DeleteCommand(0);
        command.execute(tasks, ui, storage, log);

        String expectedUi = UiMessages.getMessage(UiMessages.DELETE_SUCCESS, toBeDeletedTask);
        AbstractStorage expectedStorage = new StorageStub();
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(task2);
        expectedTaskList.add(task3);
        expectedStorage.storeAll(expectedTaskList);

        String outputUi = ui.getOutput();

        assertEquals(expectedTaskList, tasks, "delete command should delete the correct task");
        assertEquals(expectedStorage, storage, "storage tasks should not contain the deleted task");
        assertEquals(expectedUi, outputUi, "ui message should match the deleted task");
    }

    @Test
    public void delete_invalidIndex_throwsOutOfBoundsException() throws TsundereException, IOException {
        TaskList tasks = new TaskList();

        TodoTask task = new TodoTask("todo");
        tasks.add(task);

        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();
        storage.storeAll(tasks);
        Log log = new Log(tasks, ui, storage);
        DeleteCommand command = new DeleteCommand(1);
        command.execute(tasks, ui, storage, log);

        String expectedUi = new TsundereOutOfBoundsException().getMessage();
        AbstractStorage expectedStorage = new StorageStub();
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(task);
        expectedStorage.storeAll(expectedTaskList);

        String outputUi = ui.getOutput();

        assertEquals(expectedTaskList, tasks, "nothing should have been deleted");
        assertEquals(expectedStorage, storage, "storage should remain the same");
        assertEquals(expectedUi, outputUi, "ui message should show the exception message");
    }
}
