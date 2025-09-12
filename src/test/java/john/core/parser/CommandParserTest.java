package john.core.parser;

import john.core.John;
import john.core.command.*;
import john.core.exception.ParseException;
import john.model.Deadline;
import john.model.Event;
import john.model.TaskList;
import john.ports.Storage;
import john.ports.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    static class FakeStorage implements Storage {
        int saveCount = 0;
        TaskList lastSaved;

        @Override public TaskList load() { return new TaskList(); }

        @Override public void save(TaskList tasks) {
            saveCount++;
            lastSaved = tasks;
        }
    }

    /** No-op UI stub; satisfies the interface without side effects. */
    static class StubUi implements Ui {
        @Override public String nextCommand() { return null; }
        @Override public void showMessage(String msg) { /* ignore */ }
        @Override public void showWelcome(int taskCount) { /* ignore */ }
        @Override public void close() { /* ignore */ }
    }

    /* ---------- Helpers ---------- */

    private CommandResult parseAndExec(String input, TaskList tasks, FakeStorage st, StubUi ui)
            throws ParseException {
        Command cmd = CommandParser.parse(input);
        return cmd.execute(tasks, st, ui);
    }
    @Test
    void postpone_deadline_by_updatesDue_andPersists() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("report", LocalDateTime.of(2025, 9, 5, 12, 0, 0)));
        FakeStorage st = new FakeStorage();
        StubUi ui = new StubUi();

        parseAndExec("postpone 1 /by 06/09/2025 18:00:00", tasks, st, ui);

        String s = tasks.get(0).toString();
        assertTrue(s.contains("06/09/2025 18:00:00"), "deadline should reflect new /by time");
        assertEquals(1, st.saveCount);
    }

    @Test
    void postpone_event_fromTo_updatesBoth_andPersists() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Event(
                "demo",
                LocalDateTime.of(2025, 9, 7, 14, 0, 0),
                LocalDateTime.of(2025, 9, 7, 15, 0, 0)
        ));
        FakeStorage st = new FakeStorage();
        StubUi ui = new StubUi();

        parseAndExec("postpone 1 /from 07/09/2025 16:00:00 /to 07/09/2025 17:15:00", tasks, st, ui);

        String s = tasks.get(0).toString();
        assertTrue(s.contains("from: 07/09/2025 16:00:00"));
        assertTrue(s.contains("to: 07/09/2025 17:15:00"));
        assertEquals(1, st.saveCount);
    }

    @Test
    void postpone_fromTo_onDeadline_rejectedAtExecute() {
        TaskList tasks = new TaskList();
        tasks.add(new Deadline("report", LocalDateTime.of(2025, 9, 5, 12, 0, 0)));
        FakeStorage st = new FakeStorage();
        StubUi ui = new StubUi();

        ParseException ex = assertThrows(ParseException.class,
                () -> parseAndExec("postpone 1 /from 05/09/2025 12:30:00 /to 05/09/2025 13:00:00", tasks, st, ui));
        assertTrue(ex.getMessage().toLowerCase().contains("supports"));
        assertEquals(0, st.saveCount);
    }

    @Test
    void postpone_event_endBeforeStart_rejected() {
        TaskList tasks = new TaskList();
        tasks.add(new Event(
                "talk",
                LocalDateTime.of(2025, 9, 7, 14, 0, 0),
                LocalDateTime.of(2025, 9, 7, 15, 0, 0)
        ));
        FakeStorage st = new FakeStorage();
        StubUi ui = new StubUi();

        ParseException ex = assertThrows(ParseException.class,
                () -> parseAndExec("postpone 1 /from 07/09/2025 16:00:00 /to 07/09/2025 15:30:00", tasks, st, ui));
        assertTrue(ex.getMessage().toLowerCase().contains("after start"));
        assertEquals(0, st.saveCount);
    }

    @Test
    void postpone_invalidIndex_returnsFriendlyMessage_noSave() throws Exception {
        TaskList tasks = new TaskList(); // empty
        FakeStorage st = new FakeStorage();
        StubUi ui = new StubUi();

        CommandResult res = parseAndExec("postpone 3 /by 05/09/2025 16:30:45", tasks, st, ui);
        assertTrue(res.feedback().toLowerCase().contains("valid task number"));
        assertEquals(0, st.saveCount);
    }
}