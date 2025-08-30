package aurora.storage;

import aurora.task.Task;
import aurora.task.TaskReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void save(List<Task> list) {
        try {
            File parentDir = file.getParentFile();

            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task task : list) {
                    fw.write(task.toText());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> load() {
        List<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                result.add(TaskReader.fromText(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            return result;
        }

        return result;
    }
}
