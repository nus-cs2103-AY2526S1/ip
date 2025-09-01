package aurora.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import aurora.task.Task;
import aurora.task.TaskList;
import aurora.task.TaskReader;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void save(TaskList list) {
        try {
            File parentDir = file.getParentFile();

            if (!parentDir.exists() && !parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir);
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task task : list.getTasks()) {
                    fw.write(task.toText());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskList load() {
        TaskList result = new TaskList();
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
