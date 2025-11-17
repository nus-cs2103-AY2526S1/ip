package sophia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * A test class that contains test methods to check whether the methods are performing correctly
 */
public class SophiaListTest {
    /**
     * Tests whether deleteTask function in Sophia works
     * @throws SophiaException if there is any exception expected
     * @throws IOException if there is any problem brought about by file operation
     */
    @Test
    public void testDelete() throws SophiaException, IOException {
        File file = new File("./data/test_storage.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        br.write("""
            T | 0 | read
            D | 0 | return books | 2025-09-23
            E | 1 | group meeting | 2025-08-23 | 2025-08-24
            """);
        br.flush();
        Sophia sophia = new Sophia(file.getPath());
        sophia.deleteTask("delete 2");
        //System.out.println(sophia.showTasks());
        sophia.deleteTask("delete 2");

        org.junit.jupiter.api.Assertions.assertEquals("1. [T][ ] read", sophia.showTasks());
    }
}
