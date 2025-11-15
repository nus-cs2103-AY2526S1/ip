package nami.storage;
import nami.task.Deadlines;
import nami.task.Events;
import nami.task.Tasks;
import nami.task.ToDo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load task from the text file
     * @return ArrayList of Tasks
     */
    public ArrayList<Tasks> load() {
        ArrayList<Tasks> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                switch (type) {
                    case "T":
                        Tasks todo = new ToDo(description);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        String deadlineStr = parts[3];
                        LocalDateTime deadline = LocalDateTime.parse(deadlineStr);
                        Tasks d = new Deadlines(description, deadline);
                        if (isDone) d.markAsDone();
                        tasks.add(d);
                        break;
                    case "E":
                        String start = parts[3];
                        String end = parts[4];
                        LocalDateTime startTime = LocalDateTime.parse(start);
                        LocalDateTime endTime = LocalDateTime.parse(end);
                        Tasks e = new Events(description, startTime, endTime);
                        if (isDone) e.markAsDone();
                        tasks.add(e);
                        break;
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves a task into the txt file
     * @param tasks
     */
    public void save(ArrayList<Tasks> tasks) {
        assert tasks != null : "tasks must not be null";
        for (Tasks t : tasks) {
            assert t != null : "no null elements in tasks";
        }
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(filePath);
            for (Tasks t : tasks) {
                fw.write(t.toStorageFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
