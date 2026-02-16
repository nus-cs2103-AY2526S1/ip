package command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import task.Task;
import ui.Lmbd;

public class CommandTest { // Renamed from specific command names for clarity of purpose
    protected static final String SAVE_PATH = "data/test_tasks.txt";
    protected Lmbd lmbd;

    @BeforeEach
    void setUp() {
        // Initialize Lmbd with all commands for most tests
        Lmbd.disableLoading();
        lmbd = new Lmbd(SAVE_PATH, CommandRegistry.getCommands());
        lmbd.setTaskListSaveable(false);
    }

    @AfterEach
    void cleanUp() {
        Lmbd.enableLoading();
    }

    // Helper method to add tasks directly to Lmbd's task list for setup
    protected void addTaskDirectly(Task task) {
        lmbd.tasks.addTask(task);
    }
}
