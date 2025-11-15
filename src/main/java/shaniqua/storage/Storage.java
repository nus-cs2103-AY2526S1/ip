package shaniqua.storage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import shaniqua.taskcore.TaskList;
import shaniqua.taskcore.tasks.Task;

public class Storage {
    private String filePath;
    private Path folder;

    /**
     * Constructs storage object to handle save and load functionality.
     *
     * @param filePath folder from which to read and write the serialised file.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty(): "File path must be valid";
        this.filePath = filePath;
        folder = Paths.get(filePath);
    }

    /**
     * Saves TaskList object to serialised file. Tasks written to file individually
     * to limit effect of corruption.
     *
     * @param t Tasklist Object to be written to file
     * @throws StorageException if writing fails.
     */
    public void saveToFile(TaskList t) throws StorageException {
        assert t != null : "TaskList cannot be null";

        try {
            if (!Files.exists(folder)) {
                Files.createDirectories(folder); // create "data" folder if missing
            }
            File saveFile = Paths.get(filePath, "tasks.ser").toFile();
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            ObjectOutputStream outputObject = new ObjectOutputStream(outputStream);
            for (int i = 0; i < t.getLength(); i++) {
                outputObject.writeObject(t.getTask(i));
            }
            outputObject.flush();
            outputObject.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * Loads saved tasks from file. Catches ClassNotFoundException if byte is corrupted.
     *
     * @param t TaskList to write to. Since only tasks written, it is necessary.
     * @throws StorageException if file is not found, or unexpected error occurs.
     *
     */
    public int loadTasks(TaskList t) throws StorageException {
        File saveFile = Paths.get(filePath, "tasks.ser").toFile();
        if (!Files.exists(folder) || !saveFile.exists()) {
            throw new StorageException("File not found");
        }
        try {
            FileInputStream inputStream = new FileInputStream(saveFile);
            ObjectInputStream inputObject = new ObjectInputStream(inputStream);
            int count = 0;
            while (true) {
                try {
                    Task temp = (Task) inputObject.readObject();
                    if (temp != null) {
                        t.addTask(temp);
                        count++;
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Skipping Corrupted Task");
                } catch (EOFException e) {
                    break;
                }
            }
            return count;
        } catch (IOException e) {
            throw new StorageException("File not found");
        }
    }
}
