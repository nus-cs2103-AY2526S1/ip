package leo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



public class Storage {
    private String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

    private void ensureParentDir() {
        assert this.filePath != null : "filePath must exist";
        File f = new File(filePath).getParentFile();
        if (f != null && !f.exists()) {
            f.mkdirs(); // create ./data/ if missing
        }
    }

    /**
     * Saves the contents from the TaskList into the file linked to the storage
     * Contents are formatted specifically
     * @param lst TaskList containing all the tasks the current chatbot holds
     * @throws IOException
     */
    public void save(TaskList lst) throws IOException {
        ensureParentDir();
        FileWriter fw = new FileWriter(this.filePath, false);
        lst.saveToStorage(fw);
        fw.close();
    }

    /**
     * Returns the list of tasks stored in the file in filePath
     * @return An ArrayList of tasks
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(Paths.get(filePath))) {
            return tasks;
        }

        for (String line : Files.readAllLines(Paths.get(filePath))) {
            // reads every line and takes in each line as the argument
            // parses the line
            Task t = Parser.fromSaveFormat(line);
            if (t != null) {
                tasks.add(t);
            }
        }

        return tasks;
    }
}
