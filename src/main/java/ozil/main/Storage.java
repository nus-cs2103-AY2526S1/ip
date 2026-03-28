
package ozil.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ozil.exception.ErrorMessages;
import ozil.exception.OzilException;
import ozil.task.DeadlineTask;
import ozil.task.EventTask;
import ozil.task.Task;
import ozil.task.TodoTask;

/**
 * Storage class to save the current tasklist to the hardisk.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks stored in hard disk to the new chatbot instance's tasklist
     * @return TaskList
     * @throws OzilException
     */
    public TaskList loadStoredTasks() throws OzilException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                return tasks;
            }

            // create a Scanner using the File as the source
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String fileInput = s.nextLine();
                String[] sections = fileInput.split(" \\| ");

                Task task;
                switch (sections[0]) {
                case "T":
                    task = new TodoTask(sections[2]);
                    break;
                case "D":
                    task = new DeadlineTask(sections[2], sections[3]);
                    break;
                case "E":
                    task = new EventTask(sections[2], sections[3], sections[4]);
                    break;
                default:
                    throw new OzilException(ErrorMessages.errorMessage("Unexpected type of task was"
                           + " found during file parsing"));
                }

                if (Integer.parseInt(sections[1]) == 1) {
                    task.markAsDone();
                }

                tasks.addTaskToList(task);
            }
            s.close();

        } catch (FileNotFoundException e) {
            throw new OzilException(ErrorMessages.errorMessage("An error occurred while loading tasks: "
                 + e.getMessage()));
        }

        return tasks;
    }

    /**
     * Saves chatbot's tasks into hard disk before closing chatbot
     * @param taskList Current chatbot's tasklist
     * @throws OzilException
     */
    public void save(TaskList taskList) throws OzilException {
        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file, false);
            for (int i = 1; i <= taskList.getNumberOfTasks(); i++) {
                fileWriter.append(taskList.getTask(i).convertToStorageFormat());
                fileWriter.append("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new OzilException(ErrorMessages.errorMessage(
                    "An error occurred while saving: " + e.getMessage()));
        }
    }
}
