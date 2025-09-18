import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
