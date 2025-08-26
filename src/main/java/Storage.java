import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILENAME = "mem.txt";

    /**
     * Save the provided list of tasks 'tasks' into a file.
     *
     * @param tasks Tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            String content = "";
            for (int i = 0; i < tasks.size(); i++) {
                ArrayList<String> commands = tasks.get(i).toCommands();
                for (String cmd : commands) {
                    content += content.equals("") ? cmd : "\n" + cmd;
                }
            }
            writer.write(content);
            writer.close();
            Logger.info("Tasks are saved in mem.txt.");
        } catch (IOException e) {
            Logger.error(e.getMessage());
            Logger.error("Tasks are not saved.");
        }
    }

    /**
     * Load list of tasks from disk if it exists.
     *
     * @return Tasks read from disks.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));
            int i = 0;
            while (scanner.hasNextLine()) {
                i++;
                String command = scanner.nextLine();
                if (command.matches("^mark$")) {
                    // Mark the last task as done
                    if (tasks.size() > 0) {
                        tasks.get(tasks.size() - 1).markAsDone();
                    }
                } else {
                    try {
                        tasks.add(Task.parseTask(command));
                    } catch (ParseException e) {
                        Logger.error(
                                String.format("Line %d is corrupted. Proceeding to next line.", i));
                    }
                }
            }
            scanner.close();
            Logger.info(
                    "Found mem.txt, loading tasks from previous sessions. To disable, run with --fresh flag.");
        } catch (FileNotFoundException e) {
            Logger.info("No memory file detected. Starting fresh.");
        }

        return tasks;
    }
}
