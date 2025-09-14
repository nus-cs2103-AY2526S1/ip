package tsundere.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tsundere.TsundereException;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.task.TodoTask;

public class TextStorageTest {
    private final TextStorage storage;

    /**
     * Initializes a new text storage in the specified test folder.
     * Clears its contents between each test.
     */
    public TextStorageTest() throws TsundereException {
        this.storage = TextStorage.of();
        storage.clear();
    }

    @Test
    public void storeAndRetrieveAll_multipleTasks_correctFormat() throws TsundereException {
        TaskList tasks = new TaskList();
        TodoTask todo1 = new TodoTask("task 1");
        TodoTask todo2 = new TodoTask("task 2");
        DeadlineTask deadline1 = new DeadlineTask("task 3", LocalDateTime.parse("2025-05-25T23:59"));
        DeadlineTask deadline2 = new DeadlineTask("task 4", LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event1 = new EventTask("task 5",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event2 = new EventTask("task 6",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));

        todo2.markDone();
        deadline2.markDone();
        event2.markDone();

        TaskList list = new TaskList(Arrays.stream(
                new Task[] {todo1, todo2, deadline1, deadline2, event1, event2}).toList());
        tasks.addAll(list);
        storage.storeAll(list);

        TaskList outputTasks = null;
        try {
            outputTasks = storage.retrieveAll();
        } catch (TsundereException e) {
            System.out.println("ERROR");
        }

        assertEquals(tasks, outputTasks, "able to store and retrieve multiple items correctly");
    }

    @Test
    public void mark_multipleTasks_correctFormat() {
        TaskList tasks = new TaskList();
        TodoTask todo1 = new TodoTask("task 1");
        TodoTask todo2 = new TodoTask("task 2");
        DeadlineTask deadline1 = new DeadlineTask("task 3", LocalDateTime.parse("2025-05-25T23:59"));
        DeadlineTask deadline2 = new DeadlineTask("task 4", LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event1 = new EventTask("task 5",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event2 = new EventTask("task 6",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));

        TaskList list = new TaskList(Arrays.stream(
                new Task[] {todo1, todo2, deadline1, deadline2, event1, event2}).toList());
        tasks.addAll(list);
        try {
            todo2.markDone();
            deadline2.markDone();
            event2.markDone();
            storage.storeAll(tasks);
        } catch (TsundereException e) {
            throw new RuntimeException(e);
        }

        TaskList outputTasks = null;
        try {
            outputTasks = storage.retrieveAll();
        } catch (TsundereException e) {
            System.out.println("ERROR");
        }
        assertEquals(tasks, outputTasks);
    }
}
