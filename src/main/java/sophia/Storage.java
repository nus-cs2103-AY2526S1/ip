package sophia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Store Tasks in a file path
 */
public class Storage {
    private final String filePath;
    /**
     * Constructor of Storage
     * <p>
     * @param filePath specifies a valid file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save string representations of tasks to file
     * @param tasklist specifies a taskList object
     * @throws IOException specifies input or output exceptions
     */
    public void save(TaskList tasklist) throws IOException {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        tasklist.write(bw);
        bw.close();
    }

    /**
     * Returns a list of tasks of type Task by reading from the file path
     * specified in constructor
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        reader.lines().forEach(line -> {
            String[] t = line.split("\\|");
            for (int i = 0; i < t.length; i++) {
                t[i] = t[i].trim();
            }
            if (Objects.equals(t[0], "T")) {
                assert t.length == 3;
                Task temp = new TodoTask(t[2]);
                temp.setDone(t[1].equals("1"));
                tasks.add(temp);
            } else if (Objects.equals(t[0], "D")) {
                assert t.length == 4;
                Task temp = new DeadlineTask(t[2], t[3]);
                temp.setDone(t[1].equals("1"));
                tasks.add(temp);
            } else if (Objects.equals(t[0], "E")) {
                assert t.length == 5;
                Task temp = new EventTask(t[2], t[3], t[4]);
                temp.setDone(t[1].equals("1"));
                tasks.add(temp);
            }

        });

        return tasks;
    }
}
