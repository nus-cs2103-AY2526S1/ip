package pepe.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;

class UiTest {

    private Ui ui;
    private TaskList taskList;
    private Task todo1;
    private Task todo2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        taskList = new TaskList();
        todo1 = new ToDos("Buy milk");
        todo2 = new ToDos("Read book");
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    void testUiGreet() {
        String output = ui.showUiGreetUser();
        assertTrue(output.contains("I am Pepe!\n What do you want?"));
    }

    @Test
    void testUiBye() {
        String output = ui.showUiSayBye();
        assertTrue(output.contains("Fine then! Leave! I don't care..."));
    }

    @Test
    void testUiListEmpty() {
        TaskList list = new TaskList();
        String output = ui.showUiListTask(list);
        assertTrue(output.contains("Either you're really on task...or...\n"));
    }

    @Test
    void testUiMark() {
        Task task = new ToDos("Test task");
        String output = ui.showUiMark(task);
        assertTrue(output.contains("Bro finally accomplished something!"));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiUnmark() {
        Task task = new ToDos("Test task");
        String output = ui.showUiUnmark(task);
        assertTrue(output.contains("I knew it! You couldn't have finished it that quickly..."));
        assertTrue(output.contains("Test task"));
    }

    @Test
    void testUiToDo() {
        Task task = new ToDos("Test task");
        TaskList list = new TaskList();
        list.addTask(task);
        String output = ui.showUiAddToDo(list, task);
        assertTrue(output.contains("Sure let's add this task that you'll definitely do:"));
        assertTrue(output.contains("Test task"));
        assertTrue(output.contains("Now you have 1 tasks in the list"));
    }

    @Test
    void testShowUiDelete() {
        String expected = "Amazing! Let's just pretend this task didn't exist!\n"
                + todo1 + "\n"
                + "Now you have " + taskList.size() + " tasks in the list\n";
        assertEquals(expected, ui.showUiDelete(taskList, todo1));
    }

    @Test
    void testShowUiFind() {
        TaskList findList = taskList.findTask("Buy");
        String expected = "I tried my best finding these...:\n"
                + "1. " + todo1 + "\n";
        assertEquals(expected, ui.showUiFind(findList));
    }

    @Test
    void testShowUiError() {
        String errorMsg = "Oops, something went wrong!";
        assertEquals(errorMsg, ui.showUiError(errorMsg));
    }
}
