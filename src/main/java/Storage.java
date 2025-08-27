import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    public Storage() {
    }

    public ArrayList<Task> loadTasks(String path) {
        ArrayList<Task> tasks = new ArrayList<>();
        Path pathy = Paths.get(path);
        if (!Files.exists(pathy)) {
            return tasks;
        }
        try {
            ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(pathy);;
            return TextTaskConverter.convertToTask(lines);
        } catch (FileNotFoundException e) {
            System.out.println("Could not retrieve tasks from file");
            return tasks;
        } catch (IOException | RomidasException e) {
            System.out.println(e.getMessage());
            return tasks;
        }


    }

    public void saveTasks(String path, ArrayList<Task> tasks) throws RomidasException {
        Path pathe = Paths.get(path);
        try (BufferedWriter writer = Files.newBufferedWriter(pathe)) {
            for (Task task : tasks) {
                writer.write(task.toText());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RomidasException("Error while saving tasks in file");
        }
    }
}
