package sophia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * TestStorage class contains the methods which tests functions in storage class
 */
public class StorageTest {
    /**
     * Tests if the storage class is able to read from the file specified
     * @throws IOException
     */
    @Test
    public void testStorage() throws IOException {
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
        //System.out.println(file.getPath());
        Storage storage = new Storage(file.getPath());
        List<Task> xs = storage.load();
        List<Task> ys = new ArrayList<>();
        ys.add(new TodoTask("read"));
        ys.add(new DeadlineTask("return books", "2025-09-23"));
        Task t = new EventTask("group meeting", "2025-08-23", "2025-08-24");
        t.setDone(true);
        ys.add(t);
        //System.out.println(ys);
        org.junit.jupiter.api.Assertions.assertEquals(ys.toString(), xs.toString());
    }

    @Test
    public void anotherDummyTest() {
        org.junit.jupiter.api.Assertions.assertEquals(4, 4);
    }
}

