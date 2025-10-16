package lazysourcea.logic;

import lazysourcea.parser.Parser;
import lazysourcea.storage.Storage;
import lazysourcea.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class CommandExecutorTest {

    private FakeTaskList taskList;
    private FakeStorage storage;
    private FakeParser parser;
    private List<String> output;
    private Consumer<String> uiOut;
    private Consumer<String> listOut;
    private CommandExecutor exec;

    @BeforeEach
    void setup() {
        taskList = new FakeTaskList();
        storage = new FakeStorage();
        parser = new FakeParser();
        output = new ArrayList<>();
        uiOut = output::add;
        listOut = output::add;
        exec = new CommandExecutor(taskList, storage, parser, uiOut);
    }

    private Parser.Parsed parsed(Parser.CommandType type, String raw, String arg) {
        return new Parser.Parsed(type, arg, raw);
    }

    // --- basic commands ---

    @Test
    void bye_returnsTrue_andWritesBye() {
        boolean exit = exec.execute(parsed(Parser.CommandType.BYE, "bye", ""), listOut);
        assertTrue(exit);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("bye")));
        assertEquals(0, storage.saveCount);
    }

    @Test
    void list_invokesListTasks_andDoesNotSave() {
        taskList.tasks.add(new Todo("alpha"));
        exec.execute(parsed(Parser.CommandType.LIST, "list", ""), listOut);
        assertTrue(taskList.listCalled);
        assertTrue(output.stream().anyMatch(s -> s.contains("alpha")));
        assertEquals(0, storage.saveCount);
    }

    // --- TODO ---

    @Test
    void todo_withDescription_addsTask_andSaves() {
        exec.execute(parsed(Parser.CommandType.TODO, "todo read book", "read book"), listOut);
        assertEquals(1, taskList.tasks.size());
        assertEquals("read book", taskList.tasks.get(0).getDescription());
        assertEquals(1, storage.saveCount);
    }

    @Test
    void todo_emptyDescription_showsError() {
        exec.execute(parsed(Parser.CommandType.TODO, "todo", ""), listOut);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("cannot empty")));
        assertEquals(0, storage.saveCount);
    }

    // --- DEADLINE ---

    @Test
    void deadline_valid_addsDeadline_andSaves() {
        parser.deadlineArgs = new Parser.DeadlineArgs("return", LocalDate.of(2025, 10, 20));
        exec.execute(parsed(Parser.CommandType.DEADLINE, "deadline return /by 2025-10-20",
                "return /by 2025-10-20"), listOut);
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof Deadline);
        assertEquals(1, storage.saveCount);
    }

    @Test
    void deadline_invalid_showsError_andDoesNotSave() {
        parser.throwOnDeadline = true;
        exec.execute(parsed(Parser.CommandType.DEADLINE, "deadline bad", "bad"), listOut);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("invalid deadline")));
        assertEquals(0, storage.saveCount);
    }

    // --- DELETE ---

    @Test
    void delete_valid_removesTask_andSaves() {
        taskList.tasks.add(new Todo("t1"));
        parser.indexToReturn = 0; // valid 0-based index
        exec.execute(parsed(Parser.CommandType.DELETE, "delete 1", "1"), listOut);
        assertEquals(0, taskList.tasks.size());
        assertEquals(1, storage.saveCount);
    }

    @Test
    void delete_outOfRange_showsError() {
        parser.indexToReturn = 99;
        exec.execute(parsed(Parser.CommandType.DELETE, "delete 99", "99"), listOut);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("range")));
        assertEquals(0, storage.saveCount);
    }

    // --- HELP / FIND ---

    @Test
    void help_printsSomething() {
        exec.execute(parsed(Parser.CommandType.HELP, "help", ""), listOut);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("help")));
    }

    @Test
    void find_emptyArg_showsUsage() {
        exec.execute(parsed(Parser.CommandType.FIND, "find", ""), listOut);
        assertTrue(output.stream().anyMatch(s -> s.toLowerCase().contains("usage")));
    }

    // --- Fake dependencies ---

    static class FakeTaskList extends TaskList {
        final List<Task> tasks = new ArrayList<>();
        boolean listCalled = false;

        FakeTaskList() {
            super();
        }

        @Override
        public void addTask(Task t) {
            tasks.add(t);
        }

        @Override
        public Task removeTask(int index) {
            if (index < 0 || index >= tasks.size()) throw new IndexOutOfBoundsException();
            return tasks.remove(index);
        }

        @Override
        public int listSize() {
            return tasks.size();
        }

        @Override
        public Task getTask(int index) {
            return tasks.get(index);
        }

        @Override
        public void listTasks(Consumer<String> out) {
            listCalled = true;
            for (int i = 0; i < tasks.size(); i++)
                out.accept((i + 1) + ". " + tasks.get(i).getDescription());
        }

        @Override
        public List<Task> asList() {
            return new ArrayList<>(tasks);
        }
    }

    static class FakeStorage extends Storage {
        int saveCount = 0;

        FakeStorage() {
            super("fake");
        }

        @Override
        public void save(List<Task> tasks) {
            saveCount++;
        }
    }

    static class FakeParser extends Parser {
        boolean throwOnDeadline = false;
        int indexToReturn = 0;
        DeadlineArgs deadlineArgs;

        @Override
        public int parseIndex(String arg, int upperExclusive) {
            return indexToReturn;
        }

        @Override
        public DeadlineArgs parseDeadlineArgs(String arg) {
            if (throwOnDeadline) throw new IllegalArgumentException("bad");
            return deadlineArgs;
        }

        @Override
        public EventArgs parseEventArgs(String arg) {
            throw new IllegalArgumentException("unsupported");
        }
    }
}
