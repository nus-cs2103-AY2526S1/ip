package tom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Handles input from and output to the text file where the task list is saved.
 */
public class Storage {
    private static java.nio.file.Path path;

    public Storage(java.nio.file.Path path) {
        this.path = path;
    }

    /**
     * Loads the list of tasks (if any) from the existing text file.
     * @return Existing task list.
     * @throws IOException If the FileReader encounters an exception.
     */
    public ArrayList<Task> load() throws IOException {
        File file = path.toFile();
        file.getParentFile().mkdirs();
        file.createNewFile(); // Should not throw any IOException since the directory is created above

        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<Task> taskList = new ArrayList<>();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")
                .withLocale(Locale.ENGLISH);

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split("\\|"); // type | marked | desc | by/from | to
            if (arr.length == 3) {
                Todo tmp = new Todo(arr[2].strip());
                checkMarkedOrPrioritised(arr, tmp);
                taskList.add(tmp);
            } else if (arr.length == 4) {
                LocalDateTime by = LocalDateTime.parse(arr[3].strip(), outputFormatter);
                Deadline tmp = new Deadline(arr[2].strip(), by);
                checkMarkedOrPrioritised(arr, tmp);
                taskList.add(tmp);
            } else if (arr.length == 5) {
                LocalDateTime from = LocalDateTime.parse(arr[3].strip(), outputFormatter);
                LocalDateTime to = LocalDateTime.parse(arr[4].strip(), outputFormatter);
                Event tmp = new Event(arr[2].strip(), from, to);
                checkMarkedOrPrioritised(arr, tmp);
                taskList.add(tmp);
            }
        }
        return taskList;
    }

    /**
     * Writes each task in the list to the text file in the correct format.
     * @param ls List of tasks.
     * @throws IOException If the FileWriter encounters an exception.
     */
    public static void writeLines(ArrayList<Task> ls) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (Task l : ls) {
            fw.write(l.saveDesc() + "\n");
        }
        fw.close();
    }

    private void checkMarkedOrPrioritised(String[] arr, Task tmp) {
        if (arr[1].strip().equals("1")) {
            tmp.mark();
        }
        if (arr[0].charAt(0) == '*') {
            tmp.prioritise();
        }
    }
}
