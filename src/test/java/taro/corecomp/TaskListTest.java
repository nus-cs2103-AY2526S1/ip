package taro.corecomp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taro.task.Task;
import taro.ui.Ui;

class TaskListTest {

    private TaskList taskList;

    static class DummyTask extends Task {
        private final String name;

        DummyTask(String name) {
            super(name, false);
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class DummyUi extends Ui {
        private final StringBuilder output = new StringBuilder();

        @Override
        public void show(String message) {
            output.append(message).append("\n");
        }

        public String getOutput() {
            return output.toString();
        }
    }

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void testAddAndSize() {
        assertEquals(0, taskList.size());
        Task t1 = new DummyTask("task1");
        taskList.add(t1);
        assertEquals(1, taskList.size());
        assertEquals(t1, taskList.get(0));
    }

    @Test
    void testGet() {
        Task t1 = new DummyTask("task1");
        Task t2 = new DummyTask("task2");
        taskList.add(t1);
        taskList.add(t2);

        assertEquals("task1", taskList.get(0).toString());
        assertEquals("task2", taskList.get(1).toString());
    }

    @Test
    void testDelete() {
        Task t1 = new DummyTask("task1");
        Task t2 = new DummyTask("task2");
        taskList.add(t1);
        taskList.add(t2);

        Task removed = taskList.delete(1); // 1-based index
        assertEquals(t1, removed);
        assertEquals(1, taskList.size());
        assertEquals(t2, taskList.get(0));
    }

    @Test
    void testDeleteInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            taskList.delete(1);
        });
    }

    @Test
    void testListOutput() {
        Task t1 = new DummyTask("task1");
        Task t2 = new DummyTask("task2");
        taskList.add(t1);
        taskList.add(t2);

        DummyUi ui = new DummyUi();
        taskList.list(ui);

        String output = ui.getOutput();
        assertTrue(output.contains("Here are tasks in your list:"));
        assertTrue(output.contains("1. task1"));
        assertTrue(output.contains("2. task2"));
    }

    @Test
    void testConstructorWithInitList() {
        ArrayList<Task> init = new ArrayList<>();
        init.add(new DummyTask("initTask"));
        TaskList listWithInit = new TaskList(init);

        assertEquals(1, listWithInit.size());
        assertEquals("initTask", listWithInit.get(0).toString());
    }
}
