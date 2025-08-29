import java.io.File;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";

    public Storage() {
        File folder = new File("./data");
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                System.out.println("Error: Could not create data folder.");
            }
        }
    }

    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0]; // T, D, E
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                switch (type) {
                    case "T" -> task = new ToDo(description);
                    case "D" -> task = new Deadline(description, parts[3]);
                    case "E" -> task = new Event(description, parts[3], parts[4]);
                    default -> {
                        continue; // skip unknown line
                    }
                }

                if (isDone) {
                    task.markTask();
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            sb.append(taskList.get(i).toSaveString()).append(System.lineSeparator());
        }

        try {
            File file = new File(FILE_PATH);

            // Write to file
            FileWriter fw = new FileWriter(file);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks: " + e.getMessage());
        }
    }
}
