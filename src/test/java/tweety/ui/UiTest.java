package tweety.ui;

import org.junit.jupiter.api.Test;
import tweety.exceptions.TweetyException;
import tweety.tasks.Deadline;
import tweety.tasks.Task;
import tweety.tasks.ToDo;
import tweety.tasks.Event;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class UiTest {
    @Test
    public void printMarkedTask_todoTask_success() {
        Ui ui = new Ui();
        Task task = new ToDo("iP");
        task.markAsDone();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));  // Redirect System.out

        try {
            ui.printMarkedTask(task);

            String output = outputStream.toString();
            String[] lines = output.split(System.lineSeparator());

            assertEquals("     ____________________________________________________________", lines[0]);
            assertEquals("     Nice! I've marked this task as done:", lines[1]);
            assertEquals("          [T][X] iP", lines[2]);
            assertEquals("     ____________________________________________________________", lines[3]);

        } finally {
            System.setOut(originalOut);
            ui.closeScanner();
        }
    }

    @Test
    public void printMarkedTask_deadlineTask_success() {
        Ui ui = new Ui();
        try {
            Task task = new Deadline("iP", "2025-09-30");

            task.markAsDone();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));  // Redirect System.out

            try {
                ui.printMarkedTask(task);

                String output = outputStream.toString();
                String[] lines = output.split(System.lineSeparator());

                assertEquals("     ____________________________________________________________", lines[0]);
                assertEquals("     Nice! I've marked this task as done:", lines[1]);
                assertEquals("          [D][X] iP (by: Sep 30 2025)", lines[2]);
                assertEquals("     ____________________________________________________________", lines[3]);

            } finally {
                System.setOut(originalOut);
                ui.closeScanner();
            }

        } catch (TweetyException e) {
            fail();
        }
    }

    @Test
    public void printMarkedTask_eventTask_success() {
        Ui ui = new Ui();
        Task task = new Event("meeting", "3pm Monday", "5pm Monday");

        task.markAsDone();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            ui.printMarkedTask(task);

            String output = outputStream.toString();
            String[] lines = output.split(System.lineSeparator());

            assertEquals("     ____________________________________________________________", lines[0]);
            assertEquals("     Nice! I've marked this task as done:", lines[1]);
            assertEquals("          [E][X] meeting (from: 3pm Monday to: 5pm Monday)", lines[2]);
            assertEquals("     ____________________________________________________________", lines[3]);
        } finally {
            System.setOut(originalOut);
            ui.closeScanner();
        }

    }
}

