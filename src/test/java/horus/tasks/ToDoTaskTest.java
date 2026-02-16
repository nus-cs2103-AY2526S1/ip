package horus.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ToDoTaskTest {

    @Test
    void testGetTaskData() throws InvalidInputException {
        ToDoTask toDoTask = new ToDoTask("Finish homework");
        String taskData = toDoTask.getTaskData();
        Task newToDoTask = Task.readTaskData(taskData);
        assertEquals(toDoTask.equals(newToDoTask), true);
    }

    @Test
    void testEquals() {
        ToDoTask todoTask1 = new ToDoTask("Call dad");
        ToDoTask todoTask2 = new ToDoTask("Call dad");
        ToDoTask todoTask3 = new ToDoTask("Call mom");

        Task task1 = new Task("Call dad");

        assertNotEquals(task1, null); //Handles null objects
        assertNotEquals(todoTask1, task1); //Task of a different class is not equal

        assertEquals(todoTask1, todoTask2);   // Tasks with the same description are equal
        assertNotEquals(todoTask1, todoTask3);
    }

    @Test
    void testToString() {
        String taskStr = "Write unit tests";
        ToDoTask toDoTask = new ToDoTask(taskStr);
        Task task = new Task(taskStr);
        assertEquals("[T]" + task.toString(),  toDoTask.toString());
    }

}