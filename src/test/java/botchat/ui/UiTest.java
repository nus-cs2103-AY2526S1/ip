package botchat.ui;

// src/test/java/botchat/ui/UiTest.java

import botchat.task.Task;
import botchat.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UiTest {

    private final String NL = System.lineSeparator();
    private InputStream originalIn;

    //AI assisted
    @BeforeEach
    void stashStdin() {
        originalIn = System.in;
    }

    @AfterEach
    void restoreStdin() {
        System.setIn(originalIn);
    }

    @Test
    void displayWelcomeTest() {
        Ui ui = new Ui();
        ui.displayWelcome();
        String expected = Ui.LINE + NL +
                "Hello! I'm " + Ui.NAME + NL +
                "What can I do for you?" + NL +
                Ui.LINE + NL;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayByeTest() {
        Ui ui = new Ui();
        ui.displayBye();
        String expected = Ui.LINE + NL +
                "Bye. Hope to see you again soon!" + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayMarkTest() {
        Task t = mock(Task.class);
        when(t.toString()).thenReturn("[T][X] read book");

        Ui ui = new Ui();
        ui.displayMark(t);

        String expected = Ui.LINE + NL +
                "Nice! I've marked this task as done:" + NL +
                "[T][X] read book" + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayUnmarkTest() {
        Task t = mock(Task.class);
        when(t.toString()).thenReturn("[T][ ] read book");

        Ui ui = new Ui();
        ui.displayUnmark(t);


        String expected = Ui.LINE + NL +
                "OK, I've unmarked this task as done yet:" + NL +
                "[T][ ] read book" + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayAddTest() {
        Task t = mock(Task.class);
        when(t.toString()).thenReturn("[D][ ] return book (by: Sunday)");

        TaskList list = mock(TaskList.class);
        when(list.size()).thenReturn(3);

        Ui ui = new Ui();
        ui.displayAdd(t, list);

        String expected = Ui.LINE + NL +
                "Got it. I've added this task:" + NL +
                "[D][ ] return book (by: Sunday)" + NL +
                "Now you have 3 tasks in the list." + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayDeleteTest() {
        Task t = mock(Task.class);
        when(t.toString()).thenReturn("[E][ ] meeting (from: Mon 2pm to: Mon 3pm)");

        TaskList list = mock(TaskList.class);
        when(list.size()).thenReturn(1);

        Ui ui = new Ui();
        ui.displayDelete(t, list);

        String expected = Ui.LINE + NL +
                "Noted. I've removed this task:" + NL +
                "[E][ ] meeting (from: Mon 2pm to: Mon 3pm)" + NL +
                "Now you have 1 tasks in the list." + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void displayFindTest() {
        Task t1 = mock(Task.class);
        Task t2 = mock(Task.class);
        when(t1.toString()).thenReturn("[T][ ] read book");
        when(t2.toString()).thenReturn("[D][X] return book (by: Sun)");

        TaskList list = mock(TaskList.class);
        when(list.size()).thenReturn(2);
        when(list.get(0)).thenReturn(t1);
        when(list.get(1)).thenReturn(t2);

        Ui ui = new Ui();
        ui.displayFind(list);

        String expected = Ui.LINE + NL +
                "Here are the matching tasks in your list: " + NL +
                "1. [T][ ] read book" + NL +
                "2. [D][X] return book (by: Sun)" + NL +
                Ui.LINE;
        assertEquals(expected, ui.out());
    }

    @Test
    void nextLineTest() {

        System.setIn(new ByteArrayInputStream(("first line" + NL).getBytes()));

        Ui ui = new Ui();
        String line = ui.nextLine();
        assertEquals("first line", line);
    }

}
