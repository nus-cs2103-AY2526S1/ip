package lynx.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import lynx.storage.LynxTaskList;
import objectclasses.command.ListCommand;
import objectclasses.command.LynxCommand;
import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

public class LynxSearcherTest {

    private final LynxTaskList taskList = new LynxTaskList();

    @Test
    public void testFindTasks() throws LynxException {
        Task testTaskA = new TodoTask("ab", 0);
        Task testTaskB = new TodoTask("b", 0);
        Task testTaskC = new TodoTask("c", 0);
        testTaskC.setComplete();

        Task testTaskD = new DeadlineTask("a", 0, LocalDateTime.of(
                2025, 11, 11, 0, 0));
        Task testTaskE = new DeadlineTask("ba", 0, LocalDateTime.of(
                1925, 11, 11, 0, 0));
        Task testTaskF = new DeadlineTask("c", 0, LocalDateTime.of(
                2025, 11, 12, 0, 0));
        testTaskF.setComplete();

        Task testTaskG = new EventTask("a", 0,
                LocalDateTime.of(2025, 11, 7, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0));
        Task testTaskH = new EventTask("b", 0,
                LocalDateTime.of(1925, 11, 8, 0, 0),
                LocalDateTime.of(1925, 11, 13, 0, 0));
        Task testTaskI = new EventTask("c", 0,
                LocalDateTime.of(2025, 12, 9, 0, 0),
                LocalDateTime.of(2025, 12, 13, 0, 0));
        testTaskG.setComplete();

        taskList.addTask(testTaskA);
        taskList.addTask(testTaskB);
        taskList.addTask(testTaskC);
        taskList.addTask(testTaskD);
        taskList.addTask(testTaskE);
        taskList.addTask(testTaskF);
        taskList.addTask(testTaskG);
        taskList.addTask(testTaskH);
        taskList.addTask(testTaskI);

        LynxCommand testCommandA = new ListCommand("/key a /key b");
        LynxCommand testCommandB = new ListCommand("/type deadline /status complete /key c");
        LynxCommand testCommandC = new ListCommand("/status expired /key b");
        LynxCommand testCommandD = new ListCommand("/type todo /type deadline");
        LynxCommand testCommandE = new ListCommand("/status expired /on 2025-11-11");
        LynxCommand testCommandF = new ListCommand("/status complete /status incomplete /all");
        LynxCommand testCommandG = new ListCommand("/key c");
        LynxCommand testCommandH = new ListCommand("/all");
        LynxCommand testCommandI = new ListCommand("/key a /on 2025-11-11 /status incomplete");

        LynxSearcher.findTasks(testCommandA, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandB, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandC, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandD, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandE, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandF, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandG, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandH, taskList.getAllTasks());
        LynxSearcher.findTasks(testCommandI, taskList.getAllTasks());

        assertEquals(2, testCommandA.getSearchResult().size());
        assertEquals(1, testCommandB.getSearchResult().size());
        assertEquals(2, testCommandC.getSearchResult().size());
        assertEquals(0, testCommandD.getSearchResult().size());
        assertEquals(0, testCommandE.getSearchResult().size());
        assertEquals(0, testCommandF.getSearchResult().size());
        assertEquals(3, testCommandG.getSearchResult().size());
        assertEquals(9, testCommandH.getSearchResult().size());
        assertEquals(1, testCommandI.getSearchResult().size());
    }

}
