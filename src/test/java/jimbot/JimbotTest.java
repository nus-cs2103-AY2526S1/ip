package jimbot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JimbotTest {
    private static final Path TEST_DB = Path.of("data/test-database.txt");

    @BeforeEach
    void resetTestDatabase() throws IOException {
        // Make sure data directory exists
        Files.createDirectories(TEST_DB.getParent());
        // Reset file to empty before each test
        Files.write(TEST_DB, new byte[0]);
    }

    @Test
    void jimbot_todoAndExit_uiSuccess() {
        String simulatedInput = "todo finish homework\nbye\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run Jimbot
        Jimbot jimbot = new Jimbot("data/test-database.txt");
        jimbot.run();

        String actual = outContent.toString();

        String expected = """
                            ┌───────────────────────────┐
                            │ Hello! I'm Jimbot!        │
                            │ What can I do for you?    │
                   (^з^)-☆ ─┴───────────────────────────┘
                             ┌───────────────────────────────────────────┐
                             │ Got it. I've added this task:             │
                             │     [T][ ] finish homework                │
                             │ Now you have 1 tasks in the list!         │
                   (￣^￣)ゞ ─┴───────────────────────────────────────────┘
                  
                          ┌────────────────────────────────────┐
                          │ Bye! Hope to see you again soon!   │
                  \\(^O^) ─┴────────────────────────────────────┘
                  
                  """;

        assertEquals(expected, actual);
    }

    @Test
    void jimbot_deadlineEventMarkAndUnmark_uiSuccess() {
        String simulatedInput = "deadline finish ip /by 01/09/2025 1200\n event meeting /from 1130 /to 1400\n mark 2\n mark 0\n delete 2\n mark 1\n unmark 1\n bye\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(inContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Jimbot jimbo = new Jimbot("data/test-database.txt");
        jimbo.run();

        String actual = outContent.toString();

        String expected = """
                          ┌───────────────────────────┐
                          │ Hello! I'm Jimbot!        │
                          │ What can I do for you?    │
                 (^з^)-☆ ─┴───────────────────────────┘
                           ┌────────────────────────────────────────────────┐
                           │ Got it. I've added this task:                  │
                           │     [D][ ] finish ip (BY: Sep 01 2025, 12:00)  │
                           │ Now you have 1 tasks in the list!              │
                 (￣^￣)ゞ ─┴────────────────────────────────────────────────┘
                
                           ┌───────────────────────────────────────────────────────────────────────┐
                           │ Got it. I've added this task:                                         │
                           │     [E][ ] meeting (FROM: Aug 28 2025, 11:30 TO: Aug 28 2025, 14:00)  │
                           │ Now you have 2 tasks in the list!                                     │
                 (￣^￣)ゞ ─┴───────────────────────────────────────────────────────────────────────┘
                
                            ┌───────────────────────────────────────────────────────────────────────┐
                            │ Nice♪ I've marked this task as done:                                  │
                            │     [E][X] meeting (FROM: Aug 28 2025, 11:30 TO: Aug 28 2025, 14:00)  │
                            └───────────────────────────────────────────────────────────────────────┘
                ♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ ╯
                       ┌──────────────────────────┐
                       │ I can't find that task!  │
                       └──────────────────────────┘
                 (╥﹏╥) ╯
                
                           ┌───────────────────────────────────────────────────────────────────────┐
                           │ Noted. I've removed this task:                                        │
                           │     [E][X] meeting (FROM: Aug 28 2025, 11:30 TO: Aug 28 2025, 14:00)  │
                           │ Now you have 1 tasks in the list!                                     │
                 (￣^￣)ゞ ─┴───────────────────────────────────────────────────────────────────────┘
                
                            ┌────────────────────────────────────────────────┐
                            │ Nice♪ I've marked this task as done:           │
                            │     [D][X] finish ip (BY: Sep 01 2025, 12:00)  │
                            └────────────────────────────────────────────────┘
                ♪ ｖ(＾＿＾ｖ)♪ ~~ ♪(ｖ＾＿＾)ｖ ╯
                     ┌──────────────────────────────────────────────────┐
                     │ OK, I've marked this task as not done yet:       │
                     │     [D][ ] finish ip (BY: Sep 01 2025, 12:00)    │
                     └──────────────────────────────────────────────────┘
                 (｀_´)ゞ ノ
                        ┌────────────────────────────────────┐
                        │ Bye! Hope to see you again soon!   │
                \\(^O^) ─┴────────────────────────────────────┘
              
                  """;

        assertEquals(expected, actual);
    }
}
