package Baozii;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Storage {
    private final Path path;
    public Storage(String s) {
        path = Path.of(s);
    }

    /**
     * Create a bufferedreader from the given path, for the UI class to read
     * @return a BufferedReader object

     */
    public BufferedReader load() throws IOException {
        if (!Files.exists(path)) Files.createFile(path);
        return Files.newBufferedReader(path, StandardCharsets.UTF_8);
    }

    /**
     * Stores the tasklist into the given path, in serialised string.
     * @param tasks the tasklist object being stored

     */
    public void store(TaskList tasks) throws IOException {
        Files.writeString(path, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        tasks.store(path);
    }
}
