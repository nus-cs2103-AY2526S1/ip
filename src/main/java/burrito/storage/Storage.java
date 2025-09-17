package burrito.storage;

import burrito.tasklist.Deadline;
import burrito.tasklist.Event;
import burrito.tasklist.Task;
import burrito.tasklist.Todo;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    static List<Task> cache = new ArrayList<>();
    static String filePath = "src/main/java/Burrito/data/";
    static String fileName = "save.txt";

    public Storage() {}

    /**
     * Populates cache with entries from file
     *
     * @return void.
     */
    public List<Task> initializeCache() {
        try {

            Files.createDirectories(Paths.get(filePath));
            File saveFile = new File(filePath + fileName);
            if (saveFile.createNewFile()) {
                System.out.println("Warning! Save file not found on Disk. Creating a new one now...");
            }

            Scanner myReader = new Scanner(saveFile);
            while (myReader.hasNextLine()) {
                boolean Done = false;
                String data = myReader.nextLine();

                if (data.charAt(1) == 'T') {
                    if (data.charAt(5) == 'X') {
                        Done = true;
                    }
                    cache.add(new Todo(data.substring(8)));
                    cache.get(cache.size() - 1).setStatus(Done);

                } else if (data.charAt(1) == 'D') {
                    if (data.charAt(5) == 'X') {
                        Done = true;
                    }
                    String[] newData = data.substring(8, data.length() - 1).split(" \\(by: ");
                    cache.add(new Deadline(newData[0], newData[1]));
                    cache.get(cache.size() - 1).setStatus(Done);

                } else if (data.charAt(1) == 'E') {
                    if (data.charAt(5) == 'X') {
                        Done = true;
                    }
                    String[] newData = data.substring(8, data.length() - 1).split(" \\(from: ");
                    String[] newData2 = newData[1].split(" to: ");

                    cache.add(new Event(newData[0], newData2[0], newData2[1]));
                    cache.get(cache.size() - 1).setStatus(Done);
                } else { }



            }
            return this.cache;

        } catch (Exception e) {
            System.out.println("Error initializing cache.");
            System.out.println(e.getMessage());
            return this.cache;
        } finally { }
    }

    /**
     * Saves task list from cache to Hard Disk
     *
     * @return void.
     */
    static public void saveToDisk(List<Task> c) {
        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            for (Task task : c) {
                fw.write(task.toString() + "\n");
            }
            fw.close();
        } catch (Exception e) {

            System.out.println("Error saving to disk.");
        } finally {}
    }
}
