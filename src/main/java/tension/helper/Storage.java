package tension.helper;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import tension.TensionException;
import tension.task.Task;

/**
 * Manages storage files
 */
public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Writes a task to the storage file
     */
    public void writeFile(Task taskToWrite) {
        try {
            FileWriter fileWriter = new FileWriter(this.path, true);
            fileWriter.write(taskToWrite.makeStoreString() + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes line number n in storage
     */
    public void deleteLine(int n) {
        try {
            Path filePath = Path.of(this.path);
            List<String> lines = Files.readAllLines(filePath);

            if (n < 0 || n > lines.size()) {
                System.out.println("Invalid line number: " + n);
                return;
            }

            lines.remove(n);

            Files.write(filePath, lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces line number n with string newLine
     */
    public void rewriteLine(int n, String newLine) throws TensionException {
        try {
            Path filePath = Path.of(this.path);
            List<String> lines = Files.readAllLines(filePath);

            if (n < 0 || n > lines.size()) {
                System.out.println("Invalid line number: " + n);
                return;
            }

            lines.set(n, newLine);

            Files.write(filePath, lines);
        } catch (Exception e) {
            throw new TensionException("Failed to reconstruct file");
        }
    }

    /**
     * Retrieves tasks from storage
     */
    public ArrayList<Task> retrieveTasks() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        Path filePath = Path.of(this.path);
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            tasks.add(Task.makeTaskFromMemory(line));
        }
        return tasks;
    }
}
