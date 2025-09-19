package abang.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private static class DummyTask extends Task {
        public DummyTask(String description) {
            super(description);
        }

        @Override
        public String toFileFormat() {
            return "DUMMY|" + getStatusIcon() + "|" + getTaskDescription();
        }
    }

    @Test
    void constructor_validDescription_initializesCorrectly() {
        Task task = new DummyTask("Test Task");
        assertEquals("Test Task", task.getTaskDescription());
        assertEquals("0", task.getStatusIcon());
        assertNull(task.getTag());
    }

    @Test
    void done_setsTaskToFinished() {
        Task task = new DummyTask("Finish report");
        task.done();
        assertEquals("1", task.getStatusIcon());
        assertTrue(task.toString().startsWith("[X]"));
    }

    @Test
    void notDone_resetsTaskToNotFinished() {
        Task task = new DummyTask("Read book");
        task.done();
        task.notDone();
        assertEquals("0", task.getStatusIcon());
        assertTrue(task.toString().startsWith("[ ]"));
    }

    @Test
    void tag_addsHashtagPrefix() {
        Task task = new DummyTask("Do laundry");
        task.tag("urgent");
        assertEquals("#urgent", task.getTag());
        assertTrue(task.toString().contains("#urgent"));
    }

    @Test
    void toFileFormat_returnsExpectedFormat() {
        Task task = new DummyTask("Submit assignment");
        assertEquals("DUMMY|0|Submit assignment", task.toFileFormat());
    }
}
