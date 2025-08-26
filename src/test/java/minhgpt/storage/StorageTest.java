package minhgpt.storage;

import org.junit.jupiter.api.Test;

import minhgpt.task.Task;
import minhgpt.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.text.ParseException;


public class StorageTest {
    @Test
    public void parseTaskTest() {
        try {
            Task.initialise();
            Storage storage = new Storage();
            TaskList taskList = new TaskList();
            taskList.add(Task.parseTask("todo task1"));
            taskList.add(Task.parseTask("deadline task2 /by 1970-01-01"));
            taskList.add(Task.parseTask("event task3 /from 1970-01-01 /to 1970-01-02"));
            taskList.add(Task.parseTask("event task4 /from 1970-01-01 /to 1970-01-03"));
            taskList.mark(1);
            taskList.mark(3);
            storage.saveTasks(taskList);
            TaskList loaded = storage.loadTasks();
            for (int i = 0; i < taskList.size(); i++) {
                assertEquals(taskList.get(i).toString(), loaded.get(i).toString());
            }
        } catch (ParseException e) {
            fail(e.getMessage());
        } finally {
            File created = new File("mem.txt");
            created.delete();
        }
    }
}
