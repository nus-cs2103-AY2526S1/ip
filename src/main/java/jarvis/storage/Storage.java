package jarvis.storage;

import jarvis.parser.Parser;
import jarvis.tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class Storage {
    private final Path file;
    /*
    * Constructor when no params provided
    */
    public Storage() {
        this(Paths.get("data", "darren.assistant.DarrenAssistant.txt"));
    }

    /*
     * Constructor when file provided
     */
    public Storage(Path file) {
        this.file = file;
    }

    /*
    * Function to save a darren.assistant.tasks.TaskList of type List<darren.assistant.tasks.Task>
    *
    */

    public void save(List<Task> tasks) throws IOException {
        //check if there is parent directory
        if (file.getParent() != null) {
            file.getParent().toFile().mkdir();
        }

        //check if the file exists, if not, create a new file
        if (!file.toFile().exists()) {
            file.toFile().createNewFile();
        }

        FileWriter fw = new FileWriter(file.toFile());
        for (Task t: tasks) {
            fw.write(t.toStorageString() + System.lineSeparator());
        }
        fw.close();
    }

    public List<Task> load() {
        List<Task> tasks = new java.util.ArrayList<>();
        try {
            if (file.getParent() != null) file.getParent().toFile().mkdirs();
            if (!file.toFile().exists()) file.toFile().createNewFile();

            try (Scanner sc = new Scanner(file.toFile())) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    try {
                        // implement parseLine to build Todo/Deadline/Event
                        tasks.add(Parser.parseStoredTask(line));
                    } catch (Exception ignored) {
                        // skip malformed lines
                    }
                }
            }
        } catch (IOException e) {
            // start empty if anything goes wrong
        }
        return tasks;
    }

}

