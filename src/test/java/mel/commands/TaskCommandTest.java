package mel.commands;

import mel.apps.StorageStub;
import mel.apps.UiStub;
import mel.tasks.*;
import mel.apps.Ui;
import mel.commands.TaskCommand;
import mel.exceptions.MelException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TaskCommandTest {

    private static UiStub ui = new UiStub();

    @Test
    public void testConstructor() throws MelException {
        TaskCommand tc1 = new TaskCommand("123 /by 2003-12-25", "D");
        StorageStub storage = new StorageStub();
        TaskListStub taskList = new TaskListStub();

        // test case 1 (correct local date)
        tc1.execute(taskList, ui, storage);
        assertEquals("[D][ ] 123 (by: Dec 25 2003)", ui.lastPrinted);

        // test case 2 (todo)
        TaskCommand tc2 = new TaskCommand("123", "T");
        tc2.execute(taskList, ui, storage);
        assertEquals("[T][ ] 123", ui.lastPrinted);

        //test case 3 (todo no argument)
        MelException e1 = assertThrows(MelException.class, () ->  new TaskCommand("", "T"));
        assertTrue(e1.getMessage().contains("You need a task description after"));

        //test case 4 (event no argument)
        MelException e2 = assertThrows(MelException.class, () ->  new TaskCommand("concert /from 12pm", "E"));
        assertTrue(e2.getMessage().contains("Missing"));


    }

    @Test
    public void testExecute() throws MelException {
        StorageStub storage = new StorageStub();
        TaskListStub taskList = new TaskListStub();

        // wrong date format
        TaskCommand tc1 = new TaskCommand("project finished /by 12345", "D");
        MelException e1 = assertThrows(MelException.class, () ->  tc1.execute(taskList, ui, storage));
        assertTrue(e1.getMessage().contains("Incorrect date format!"));

        // correct event format
        TaskCommand tc2 = new TaskCommand("concert /from 12pm /to 1pm", "E");
        tc2.execute(taskList, ui, storage);
        assertTrue(ui.lastPrinted.contains("concert (from: 12pm to: 1pm)"));



    }
}
