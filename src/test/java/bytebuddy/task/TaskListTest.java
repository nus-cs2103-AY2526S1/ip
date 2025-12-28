package bytebuddy.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void addTask_validTask_successfullyAdded() {
        TaskList tasks = new TaskList();
        Task t = new Task("read book", TaskType.TODO);

        tasks.addTask(t);

        assertEquals(1, tasks.getSize());
        assertEquals("T | 0 | read book", tasks.getTask(0).toString());
    }

    @Test
    void removeTask_validIndex_successfullyRemoved() {
        TaskList tasks = new TaskList();
        Task t1 = new Task("read book", TaskType.TODO);
        Task t2 = new Task("write report", TaskType.TODO);

        tasks.addTask(t1);
        tasks.addTask(t2);

        tasks.removeTask(0);

        assertEquals(1, tasks.getSize());
        assertEquals("T | 0 | write report", tasks.getTask(0).toString());
    }
}
