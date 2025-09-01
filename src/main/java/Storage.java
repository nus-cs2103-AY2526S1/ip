import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Storage {
    private File file;
    public static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage () {
        try {
            new File("./data/").mkdirs();
            File file = new File("./data/LunarBot.csv");
            if (file.createNewFile()) {
                System.out.println("Creating new save data: " + file.getName());
            } else {
                System.out.println("Saved data already exists! " +
                        "Loading from hard disk!");
            }
            this.file = file;
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
    }

    /**
     * Reads the input file and returns the saved data as a list of tasks
     *
     * @return List of tasks
     */
    public List<Task> loadData() {
        try {
            List<Task> data = new ArrayList<>();
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] values = line.split(",");
                switch (values[0]) {
                case "X":
                    data.add(new Task(values[2], values[1].equals("true")));
                    break;
                case "T":
                    data.add(new Todo(values[2], values[1].equals("true")));
                    break;
                case "D":
                    data.add(new Deadline(values[2], values[1].equals("true"),
                            LocalDateTime.parse(values[3], SAVE_FORMAT)));
                    break;
                case "E":
                    data.add(new Event(values[2], values[1].equals("true"),
                            LocalDateTime.parse(values[3], SAVE_FORMAT),
                            LocalDateTime.parse(values[4], SAVE_FORMAT)));
                    break;
                default:
                    System.out.println("Something wrong occurred... irregular occurrence in saved data");
                }
            }

            return data;
        } catch (IOException exception) {
            System.out.println(exception.toString());
            return null;
        }
    }

    /**
     * Writes the new data into the save file
     *
     * @param saveData data to save to the file
     */
    public void writeFile(List<Task> saveData) {
        try (FileWriter writer = new FileWriter(this.file)) {
            for (Task data : saveData) {
                writer.write(data.getAsCsv() + "\n");
            }
        } catch (IOException exception) {
            System.out.println(exception.toString());
        }
    }
}
