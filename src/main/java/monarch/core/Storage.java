package monarch.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import monarch.exceptions.MonException;
import monarch.tasks.Deadline;
import monarch.tasks.Event;
import monarch.tasks.Task;
import monarch.tasks.ToDo;

/**
 * Represents the way Monarch interacts with task storage.
 */
public class Storage {
    private static String filePath;

    /**
     * Sets the file path to the saved list of tasks.
     *
     * @param filePath A file path.
     */
    public void set(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task from a given text file.
     *
     * @return Collection of Tasks.
     */
    public Task[] load() throws MonException {
        File f = new File(this.filePath);
        ArrayList<Task> save = new ArrayList<>();
        Scanner s;
        String type;
        String status;
        String info;
        Task task;
        try {
            // Check if save file exists, create if required
            f.createNewFile();

            // Retrieve tasks from save file
            s = new Scanner(f);
            while (s.hasNext()) {
                String[] i = s.nextLine().split(",,,", 3);
                type = i[0];
                status = i[1];
                info = i[2];
                task = null;

                switch (type) {
                case "T":
                    // Sample structure: <type>,,,<status>,,,<info>
                    task = new ToDo(info);
                    break;

                case "D":
                    // Sample structure: <type>,,,<status>,,,<desc>,,,<end>
                    String[] deadlineArgs = info.split(",,,", 2);
                    task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    break;

                case "E":
                    // Sample structure: <type>,,,<status>,,,<desc>,,,<start>,,,<end>
                    String[] eventArgs = info.split(",,,", 3);
                    task = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    break;

                default:
                    System.out.println("Unknown task type: " + type);
                }


                if (task != null) {
                    // Update task status
                    if (status.equals("X")) {
                        task.markAsDone();
                    }

                    // Append task to task list
                    save.add(task);
                }
            }
            // Return compiled task list
            return save.toArray(new Task[save.size()]);

        } catch (IOException e) {
            throw new MonException("Missing save file");
        }
    }

    /**
     * Saves all tasks from an ArrayList of Tasks into a file given by the file path.
     *
     * @param taskArr An ArrayList of Task.
     * @throws IOException If the file at the file path cannot be found.
     */
    public void save(ArrayList<Task> taskArr) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String tasksList = "";
        String info = "";
        for (int i = 0; i < taskArr.size(); i++) {
            Task task = taskArr.get(i);

            switch (task.getType()) {
            case "T":
                info = task.getDescription();
                break;

            case "D":
                info = String.format("%s,,,%s", task.getDescription(), task.getInfo()[0]);
                break;

            case "E":
                info = String.format("%s,,,%s,,,%s", task.getDescription(),
                        task.getInfo()[0],
                        task.getInfo()[1]);
                break;

            default:
                // Should never reach
                throw new IOException("FileWriter failed to be created");
            }

            String taskString = String.format("%s,,,%s,,,",
                    taskArr.get(i).getType(),
                    taskArr.get(i).getStatusIcon());
            tasksList += (taskString + info + System.lineSeparator());
        }
        fw.write(tasksList);
        fw.close();
    }

    /**
     * Returns the path to the save file.
     *
     * @return The file path.
     */
    public String getFilePath() {
        return this.filePath;
    }
}
