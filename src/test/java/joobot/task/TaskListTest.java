package joobot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setup() {
        taskList = new TaskList(new ArrayList<>());
    }

    @Test
    public void addTask_increasesSize() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("read another book"));
        assertEquals(2, taskList.size());
    }

    @Test
    public void deleteTask_removesCorrectTask() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new Deadline("submit report", "2/12/2019 1800"));
        Task removed = taskList.deleteTask(0); // remove first task
        assertEquals("[T][ ] read book", removed.toString());
        assertEquals(1, taskList.size());
    }

    @Test
    public void markTask_changesStatus() {
        taskList.addTask(new ToDo("read book"));
        taskList.getTask(0).markDone();
        assertTrue(taskList.getTask(0).isDone());
        taskList.getTask(0).markNotDone();
        assertFalse(taskList.getTask(0).isDone());
    }
}
