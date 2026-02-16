package storage;

import exception.NicholasException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.TaskList;
import tasks.ToDoTask;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file storage and writing
 */
public class Storage {

    /* Default file path */
    private static final String STORAGE_PATH = "./tasks/Nicholas.txt";

    /* Handles folder and file creation */
    public void fileSetup() {
        File file = new File(STORAGE_PATH);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /* Write to file */
    public void saveToFile(TaskList tasks) {
        try {
            assert !new File(STORAGE_PATH).exists() : "File not setup yet";
            FileWriter writer = new FileWriter(STORAGE_PATH, false);

            writer.write(tasks.toString());


            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadFile(TaskList tasks) {
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(STORAGE_PATH);

        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (String line : lines) {
                String[] temp = line.split("\\|");
                boolean isDone = temp[1].equals("X");
                String description = temp[2];
                System.out.println(description);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                switch (temp[0]) {
                case "T" -> {
                    tasks.addItem(new ToDoTask(description, isDone));
                }
                case "D" -> {
                    LocalDate dateTime = LocalDate.parse(temp[3].trim(), formatter);
                    tasks.addItem(new DeadlineTask(dateTime, description, isDone));
                }
                case "E" -> {
                    LocalDate startTime = LocalDate.parse(temp[3].trim(), formatter);
                    LocalDate endTime = LocalDate.parse(temp[4].trim(), formatter);
                    tasks.addItem(new EventTask(description, startTime, endTime, isDone));
                }
                default -> {
                    continue;
                }
                }
            }
        } catch (Exception e) {
            System.out.println("Corrupted file. Skipping and ending task");
        }
    }
}

