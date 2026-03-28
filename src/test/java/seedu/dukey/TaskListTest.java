package seedu.dukey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import exceptions.DukeyException;
import org.junit.jupiter.api.Test;

import tasklist.TaskList;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


public class TaskListTest {


    @Test
    public void findTaskTest() {
        try {
            Task task1 = new ToDo("eat book", false);
            Task task2 = new DeadLine("return book project /by 02/12/2019 1800", false);
            Task task3 = new Event("project meeting /from 04/12/2019 1400 /to 04/12/2019 1600", false);
            TaskList dummy = new TaskList();
            dummy.addTask(task1);
            dummy.addTask(task2);
            dummy.addTask(task3);

            String sampleOutput = "Here are the matching tasks in your list:\n" +
                    "1.[T][ ] eat book\n" +
                    "2.[D][ ] return book project (by: 02-12-2019 1800)";
            assertEquals(dummy.findTask("book"), sampleOutput);

            String sampleOutput2 = "Here are the matching tasks in your list:\n" +
                    "1.[D][ ] return book project (by: 02-12-2019 1800)\n" +
                    "2.[E][ ] project meeting (from 04-12-2019 1400 to: 04-12-2019 1600)";
            assertEquals(dummy.findTask("project"), sampleOutput2);

            assertEquals(dummy.findTask("pizza"), "No matching tasks found");

        } catch (DukeyException e) { //just to pass compiler check
            return;
        }
    }
}

