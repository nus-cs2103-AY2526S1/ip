package sheares;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import sheares.exception.DukeException;
import sheares.task.Deadline;
import sheares.task.Event;
import sheares.task.FixedDuration;
import sheares.task.Task;
import sheares.task.Todo;


/**
 * class that stores existing data
 */
public class Storage {
    private final ArrayList<Task> ans;
    private final String filePath;

    /**
     * Constructor for storage object that stores existing data
     * @param filePath
     */
    public Storage(String filePath) {
        this.ans = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * from a given current TaskList, write all the data into the filePath
     * @param ls
     */
    public void save(TaskList ls) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (int i = 0; i < ls.size(); i++) {
                Task task = ls.get(i);
                String output = task.taskToStr();
                fw.write(output + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("file writing off");
        }
    }

    /**
     * retruns an ArrayList of Tasks that represents the past data of the chatbot that was saved
     * @return
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        //String filePath = "./data/duke.txt";
        Path path = Paths.get(this.filePath);
        Scanner sc = new Scanner(System.in);
        try {
            Path parentDir = path.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // Step 3: Check if the file itself exists. If not, create it.
            if (!Files.exists(path)) {
                Files.createFile(path);
                throw new DukeException();
            }

            // Step 4: If the file exists, proceed to read from it using a Scanner.
            System.out.println("Your progress has been saved!");
            Scanner s = new Scanner(path);
            while (s.hasNext()) {
                String input = s.nextLine();
                Task t = getTask(input);
                ans.add(t);
            }
        } catch (IOException e) {
            System.err.println("Cant read file, file is corrupted");
        } finally {
            sc.close();
        }
        return this.ans;
    }

    private static Task getTask(String input) {
        String[] pieces = input.split(" \\| ");
        Task t = null;
        if (pieces.length == 3) {
            String description = pieces[2];
            t = new Todo(description);
        } else {
            if (Objects.equals(pieces[0], "D")) {
                String description = pieces[2];
                String dateString = pieces[3];
                t = new Deadline(description, LocalDate.parse(dateString));
            } else if (Objects.equals(pieces[0], "F")) {
                String description = pieces[2];
                String duration = pieces[3];
                t = new FixedDuration(description, duration);
            } else {
                String fromToSection = pieces[3];
                String[] fromAndTo = fromToSection.split("-");
                String description = pieces[2];
                t = new Event(description, fromAndTo[0], fromAndTo[1]);
            }
        }
        String marked = pieces[1];
        if (Objects.equals(marked, "1")) {
            t.mark();
        }
        return t;
    }
}
