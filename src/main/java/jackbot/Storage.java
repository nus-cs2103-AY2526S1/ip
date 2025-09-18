package jackbot;

import jackbot.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persists and restores the task list to/from a line-based text file.
 *
 * <p>Format: one serialized {@link Task} per line as produced by
 * {@link Task#serialize()} and read via {@link Task#deserialize(String)}.</p>
 *
 * <p>Behavior:</p>
 * <ul>
 *   <li>If the file does not exist, {@link #load()} returns an empty list (no error).</li>
 *   <li>On I/O problems, a {@link JackbotException} is thrown with a user-friendly message.</li>
 *   <li>If a line cannot be parsed, {@link #load()} throws a {@code JackbotException}
 *       (nothing is loaded), allowing the UI to show a loading error.</li>
 * </ul>
 *
 * <p><b>Thread-safety:</b> this class is not thread-safe; use from a single thread.</p>
 *
 */
public class Storage {
    /** Path to the text file backing storage. */
    private final String filePath;

    /**
     * Creates a storage bound to the given file path.
     *
     * @param filePath path to the task file (will be created on first save)
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the backing file.
     *
     * <p>If the file is missing, this returns an empty list. If an I/O error
     * occurs, or if any line cannot be parsed into a {@link Task}, a
     * {@link JackbotException} is thrown.</p>
     *
     * @return list of tasks (never {@code null})
     * @throws JackbotException on I/O failure or parse error
     */
    public List<Task> load() throws JackbotException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            // No file is not an error; just return empty.
            return tasks;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(Task.deserialize(line));
                } catch (Exception e) {
                    // Skip bad lines but inform via exception to be handled by Ui.showLoadingError
                    throw new JackbotException("Failed to parse a line in the task file.");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new JackbotException("Cannot read task file.");
        }
    }

    /**
     * Saves the given tasks to the backing file, overwriting existing content.
     *
     * <p>Each task is written on its own line using {@link Task#serialize()}.</p>
     *
     * @param tasks tasks to persist (order is preserved)
     * @throws JackbotException on I/O failure
     */
    public void save(List<Task> tasks) throws JackbotException {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task t : tasks) {
                writer.write(t.serialize());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new JackbotException("Cannot save tasks to file.");
        }
    }
}
