package lux.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import lux.data.AliasList;
import lux.data.TaskList;
import lux.data.TodoTask;

public class StorageTest {
    @Test
    public void saveAndLoadTasksRoundtrip() throws Exception {
        File tmp = Files.createTempDirectory("lux-test").toFile();
        String base = tmp.getAbsolutePath() + File.separator;
        Storage storage = new Storage(base);

        TaskList tasks = new TaskList();
        tasks.addTasks(new TodoTask("alpha"));
        tasks.addTasks(new TodoTask("beta"));

        storage.saveTasks(tasks);
        var loaded = storage.loadTasks();
        // loaded is an ArrayList<Task>
        assertEquals(2, loaded.size());
        assertEquals("alpha", loaded.get(0).toString().contains("alpha") ? "alpha" : "");

        // aliases
        AliasList aliases = new AliasList();
        aliases.add("ls", "list");
        storage.saveAliases(aliases);
        AliasList loadedAliases = storage.loadAliases();
        assertEquals("list", loadedAliases.process("ls"));
    }
}
