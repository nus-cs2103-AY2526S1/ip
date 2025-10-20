package khat.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

public class ListCommandTest {

    @Test
    void execute_listCommand_showsAllTasks() {
        TaskList tasks = new TaskList();
        class TestUi extends Ui {
            private boolean isCalled = false;
            @Override
            public void showAllTasks(TaskList t) {
                isCalled = true;
            }
            public boolean wasCalled() {
                return isCalled;
            }
        }
        TestUi ui = new TestUi();
        Storage storage = new Storage("test/test.txt");
        ListCommand cmd = new ListCommand();
        assertDoesNotThrow(() -> cmd.execute(tasks, ui, storage));
        assertTrue(ui.wasCalled());
    }
}
