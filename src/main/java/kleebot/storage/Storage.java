package kleebot.storage;

import kleebot.task.Deadline;
import kleebot.task.Event;
import kleebot.task.Task;
import kleebot.task.ToDo;

import java.io.*;
import java.util.*;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        initializeStorage();
    }

    private void initializeStorage() {
        File file = new File(filePath);

        // Check if the file's directory exists, if not, create it
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs(); // Create any necessary directories
            if (!dirCreated) {
                System.out.println("hmm... i failed to create create the directory? for path: " + parentDir);
            }
        }

        // If the file doesn't exist, attempt to create it
        if (!file.exists()) {
            try {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    System.out.println("File created at: " + filePath);
                } else {
                    System.out.println("File already exists at: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Something went wrong with creating a new file!! >_< I'm so sowwy :((" + filePath);
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a list of tasks from a local data file.
     *
     * @return ArrayList of type Task
     * @throws FileNotFoundException Exception thrown if the data file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if (data.isEmpty()) break;
                    String[] splitData = data.split("\\s+\\|\\s+"); // regex splits by " | " hopefully
                    String type = splitData[0];
                    boolean done = splitData[1].equals("1");

                    switch (type) {
                    case "T":
                        ToDo tmp = new ToDo(splitData[2]);
                        if (done) {
                            tmp.markAsDone();
                        }
                        tmp.setPriority(Integer.parseInt(splitData[3]));
                        tasks.add(tmp);
                        break;
                    case "D":
                        Deadline tmpD = new Deadline(splitData[2], splitData[3]);
                        if (done) {
                            tmpD.markAsDone();
                        }
                        tmpD.setPriority(Integer.parseInt(splitData[4]));
                        tasks.add(tmpD);
                        break;
                    case "E":
                        Event tmpE = new Event(splitData[2], splitData[3], splitData[4]);
                        if (done) {
                            tmpE.markAsDone();
                        }
                        tmpE.setPriority(Integer.parseInt(splitData[5]));
                        tasks.add(tmpE);
                        break;
                    default:
                        assert false : "Unknown task type found in storage: " + type;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else { // file doesn't exist yet
            try {
                boolean _success = file.createNewFile();
                assert _success : "Failed to create new file at: " + filePath;
                System.out.println("File created" + file.getName());
            } catch (IOException e) {
                System.out.println("Something went wrong with creating a new file!! >_< I'm so sowwy :((");
                e.printStackTrace();
            }
        }
        return tasks;
    }

    /**
     * Writes all tasks recorded during the application's runtime to the local data file.
     *
     * @param tasks ArrayList of {@code Tasks} to be saved.
     */
    public void saveTasksToLocal(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath, false); // 2nd param decides whether to overwrite the file or not
            for (Task task : tasks) {
                String fileString = task.getType() + " | " + (task.getStatus() ? "1" : "0") +  " | " + task.getDescription().trim();
                if (task instanceof Deadline d) {
                    fileString += " | " + d.getBy();
                } else if (task instanceof Event e) {
                    fileString += " | " + e.getFrom() + " | " + e.getTo();
                }
                fileString += " | " + task.getPriority();
                writer.write(fileString + "\n");
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

