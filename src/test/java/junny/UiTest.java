package junny;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import junny.Ui.Ui;
import junny.tasks.Task;
import junny.tasks.Todo;
import junny.tasks.Deadline;
public class UiTest {

    // check greeting output
    @Test
    public void testPrintHi() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printHi();

        String output = outContent.toString();
        assertTrue(output.contains("Hello! I'm Junny"));
        assertTrue(output.contains("What can I do for you?"));

        System.setOut(System.out); // reset
    }

    // check bye output
    @Test
    public void testPrintBye() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.printBye();

        String output = outContent.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));

        System.setOut(System.out);
    }

    @Test
    public void testReadCommands() {
        String simulatedInput = "todo read book\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        Ui ui = new Ui();
        String userInput = ui.readCommands();

        assertEquals("todo read book", userInput);

        System.setIn(System.in); // reset
    }

    @Test
    public void testAddTaskAndMarkDone() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();

        Todo todo = new Todo("read book");
        tasks.add(todo);

        todo.markAsDone();  // ** need to put before, since ui.addTask add the unmarked version
        ui.addTask(todo, tasks.size());
        ui.markDone(todo);
        // assertTrue(todo.markAsDone()); ** cannot do this, since markAsDone() returns void -> not true

        String output = outContent.toString();
        assertTrue(output.contains("Got it. I've added this task:"));
        assertTrue(output.contains("Nice! I've marked this task as done:"));
        assertTrue(output.contains("[T][X] read book"));

        System.setOut(System.out);
    }

    @Test
    public void testPrintTaskOnDate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ArrayList<Task> tasks = new ArrayList<>();

        // add a deadline
        Deadline ddl = new Deadline("submit report", "2025-09-10");
        tasks.add(ddl);

        // print tasks on that date
        ui.printTaskOnDate(LocalDate.parse("2025-09-10"), tasks);
        String output = outContent.toString();
        assertTrue(output.contains("Here are the tasks in your list on the day:"));
        assertTrue(output.contains("submit report"));

        System.setOut(System.out);
    }
}
