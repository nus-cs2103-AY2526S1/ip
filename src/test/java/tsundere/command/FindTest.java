package tsundere.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tsundere.StorageStub;
import tsundere.TsundereException;
import tsundere.UiStub;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TaskList;
import tsundere.task.TodoTask;
import tsundere.ui.UiMessages;

public class FindTest {
    @Test
    public void find_allMatches_returnsListOfTasks() throws TsundereException {
        TaskList tasks = new TaskList();
        String searchString = "searchable";
        TodoTask todo1 = new TodoTask("1searchable1");
        EventTask event2 = new EventTask("2searchable2",
                LocalDateTime.parse("2026-01-01T23:59"),
                LocalDateTime.parse("2026-01-02T23:59"));
        DeadlineTask deadline3 = new DeadlineTask("3searchable3", LocalDateTime.parse("2026-01-01T23:59"));
        tasks.add(todo1);
        tasks.add(event2);
        tasks.add(deadline3);

        FindCommand command = new FindCommand(searchString);
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();

        command.execute(tasks, ui, storage, null);
        String expected = UiMessages.getMessage(UiMessages.LIST_TASKS, tasks);
        String output = ui.getOutput();

        assertEquals(expected, output,
                "find command should return all matches");
    }
    @Test
    public void find_noMatches_returnsEmptyListOfTasks() throws TsundereException {
        TaskList tasks = new TaskList();
        String searchString = "searchable";
        TodoTask todo1 = new TodoTask("some");
        EventTask event2 = new EventTask("random",
                LocalDateTime.parse("2026-01-01T23:59"),
                LocalDateTime.parse("2026-01-02T23:59"));
        DeadlineTask deadline3 = new DeadlineTask("names", LocalDateTime.parse("2026-01-01T23:59"));
        tasks.add(todo1);
        tasks.add(event2);
        tasks.add(deadline3);

        FindCommand command = new FindCommand(searchString);
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub();

        command.execute(tasks, ui, storage, null);
        String expected = UiMessages.getMessage(UiMessages.LIST_TASKS, new TaskList());
        String output = ui.getOutput();

        assertEquals(expected, output, "find command should return no matches");
    }
}
