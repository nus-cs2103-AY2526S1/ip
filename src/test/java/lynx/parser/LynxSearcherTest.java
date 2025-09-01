package lynx.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import lynx.storage.LynxTaskList;
import objectclasses.command.LynxCommand;
import objectclasses.exception.LynxException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

public class LynxSearcherTest {

    @Test
    public void testFindTasks() throws LynxException {
        Task testTaskA = new TodoTask("ab");
        Task testTaskB = new TodoTask("b");
        Task testTaskC = new TodoTask("c");
        testTaskC.setComplete();

        Task testTaskD = new DeadlineTask("a", LocalDateTime.of(
                2025, 11, 11, 0, 0));
        Task testTaskE = new DeadlineTask("ba", LocalDateTime.of(
                1925, 11, 11, 0, 0));
        Task testTaskF = new DeadlineTask("c", LocalDateTime.of(
                2025, 11, 12, 0, 0));
        testTaskF.setComplete();

        Task testTaskG = new EventTask("a",
                LocalDateTime.of(2025, 11, 7, 0, 0),
                LocalDateTime.of(2025, 11, 13, 0, 0));
        Task testTaskH = new EventTask("b",
                LocalDateTime.of(1925, 11, 8, 0, 0),
                LocalDateTime.of(1925, 11, 13, 0, 0));
        Task testTaskI = new EventTask("c",
                LocalDateTime.of(2025, 12, 9, 0, 0),
                LocalDateTime.of(2025, 12, 13, 0, 0));
        testTaskG.setComplete();

        LynxTaskList.addTask(testTaskA);
        LynxTaskList.addTask(testTaskB);
        LynxTaskList.addTask(testTaskC);
        LynxTaskList.addTask(testTaskD);
        LynxTaskList.addTask(testTaskE);
        LynxTaskList.addTask(testTaskF);
        LynxTaskList.addTask(testTaskG);
        LynxTaskList.addTask(testTaskH);
        LynxTaskList.addTask(testTaskI);

        LynxCommand testCommandA = new LynxCommand("/key a /key b");
        LynxCommand testCommandB = new LynxCommand("/type deadline /status complete /key c");
        LynxCommand testCommandC = new LynxCommand("/status expired /key b");
        LynxCommand testCommandD = new LynxCommand("/type todo /type deadline");
        LynxCommand testCommandE = new LynxCommand("/status expired /on 2025-11-11");
        LynxCommand testCommandF = new LynxCommand("/status complete /status incomplete /all");
        LynxCommand testCommandG = new LynxCommand("/key c");
        LynxCommand testCommandH = new LynxCommand("/all");
        LynxCommand testCommandI = new LynxCommand("/key a /on 2025-11-11 /status incomplete");

        LynxSearcher.findTasks(testCommandA, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandB, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandC, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandD, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandE, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandF, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandG, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandH, LynxTaskList.getAllTasks());
        LynxSearcher.findTasks(testCommandI, LynxTaskList.getAllTasks());

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
