package chlo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import chlo.exception.ChloException;
import chlo.task.Deadline;
import chlo.task.Event;
import chlo.task.Task;
import chlo.task.Todo;
import chlo.task.TaskList;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * loads list from hard disk
     * @return
     * @throws ChloException
     */
    public ArrayList<Task> load() throws ChloException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("todo")) {
                    if (line.charAt(line.length() - 1) == '/') {
                        Task task = new Todo(line.substring(5, line.length() - 1).trim());
                        task.markDone();
                        tasks.add(task);
                        System.out.println("hit");
                    } else {
                        tasks.add(new Todo(line.substring(5).trim()));
                    }
                } else if (line.startsWith("deadline")) {
                    int i = line.indexOf("/by");
                    if (line.charAt(line.length() - 1) == '/') {
                        Task task = new Deadline(line.substring(9, i).trim(), line.substring(i + 4, line.length() - 1).trim());
                        task.markDone();
                        tasks.add(task);
                    } else {
                        tasks.add(new Deadline(line.substring(9, i).trim(), line.substring(i + 4).trim()));
                    }
                } else {
                    int i = line.indexOf("/from");
                    int j = line.indexOf("/to");
                    tasks.add(new Event(line.substring(6, i).trim(), line.substring(i + 6, j).trim(), line.substring(j + 4).trim()));
                }
            }
            scanner.close();
            return tasks;
        } catch (IOException e) {
            throw new ChloException("Error loading file.");
        }
    }

    /**
     * saves list to hard disk
     * @param tasks
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).getRaw() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }
}