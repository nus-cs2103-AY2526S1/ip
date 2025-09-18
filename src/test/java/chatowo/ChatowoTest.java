package chatowo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatowo.task.Task;
import chatowo.task.ToDo;

public class ChatowoTest {
    private Chatowo chatowo;

    @BeforeEach
    public void setUp() {
        chatowo = new Chatowo();
        chatowo.init();
    }

    @Test
    public void addTask_validTodoTask_success() {
        Task task = new ToDo("read book");
        String response = chatowo.addTask(task);
        assertTrue(response.contains("Added"));
        assertTrue(response.contains("read book"));
    }

    @Test
    public void find_existingPhrase_returnsMatches() {
        Task task1 = new ToDo("read book");
        Task task2 = new ToDo("watch movie");
        chatowo.addTask(task1);
        chatowo.addTask(task2);
        String response = chatowo.find("book");
        assertTrue(response.contains("read book"));
        assertFalse(response.contains("watch movie"));
    }

    @Test
    public void undo_afterMark_taskUnmarked() {
        Task task = new ToDo("read book");
        chatowo.addTask(task);
        chatowo.mark(0);
        String response = chatowo.undo();
        assertTrue(response.contains("not done"));
        assertTrue(response.contains("[ ]"));
    }

    @Test
    public void undo_afterAdd_taskDeleted() {
        Task task = new ToDo("read book");
        chatowo.addTask(task);
        String response = chatowo.undo();
        assertTrue(response.contains("deleted"));
    }

    @Test
    public void undo_noPrevcCommand_returnsErrorMessage() {
        String response = chatowo.undo();
        assertTrue(response.contains("forgor"));
    }

    @Test
    public void listTasks_withTasks_showsAllTasks() {
        Task task1 = new ToDo("read book");
        Task task2 = new ToDo("watch movie");
        chatowo.addTask(task1);
        chatowo.addTask(task2);
        String response = chatowo.listTasks();
        assertTrue(response.contains("read book"));
        assertTrue(response.contains("watch movie"));
    }

    @Test
    public void getResponse_validCommand_success() {
        String response = chatowo.getResponse("todo read book");
        assertTrue(response.contains("Added"));
        assertTrue(response.contains("read book"));
    }
}
