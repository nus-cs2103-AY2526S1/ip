package chiikawa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import chiikawa.task.DeadlineTask;
import chiikawa.task.EventTask;
import chiikawa.task.Task;
import chiikawa.task.ToDoTask;

/**
 * Class representing the storage system of Chiikawa,
 * saving and loading the file from the hard disk.
 */
public class Storage {
    private String filePath;
    private File f;
    private File parentDir;

    /**
     * Constructor for a new instance of Storage.
     *
     * @param filePath The path where the save file can be found.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
        this.parentDir = f.getParentFile();
    }

    /**
     * Loads the save file from the hard disk.
     *
     * @return ArrayList consisting of all the Tasks saved in the save file.
     * @throws ChiikawaException Throws different exceptions such as missing directory or file.
     */
    public ArrayList<Task> loadFile() throws ChiikawaException {
        Scanner s = null;
        ArrayList<Task> taskList = new ArrayList<>();

        if (!this.parentDir.exists()) {
            this.parentDir.mkdirs();
            throw new ChiikawaException("diwectory nawt faound!!\ni make, no worry");
        }

        if (!this.f.exists()) {
            try {
                this.f.createNewFile();
                throw new ChiikawaException("file nawt faound!!\ni make, no worry");
            } catch (IOException e) {
                throw new ChiikawaException("IOException or samting... idkk!!!");
            }
        }

        try {
            s = new Scanner(f);
        } catch (IOException e) {
            throw new ChiikawaException("scanner pwoblem....");
        }

        while (s.hasNextLine()) {
            String[] line = Parser.parseTaskInfo(s.nextLine(), "\\|", 4);
            boolean isCompleted = line[1].equals("1");
            String command = line[0];

            assert command != null : "command must be non-null!";
            switch (command) {
            case "T":
                ToDoTask newToDoTask = new ToDoTask(line[2], isCompleted);
                taskList.add(newToDoTask);
                break;

            case "D":
                DeadlineTask newDeadlineTask = new DeadlineTask(
                        line[2],
                        isCompleted,
                        line[3]);
                taskList.add(newDeadlineTask);
                break;

            case "E":
                String[] eventTime = Parser.parseTaskInfo(line[3], "to", 2);
                EventTask newEventTask = new EventTask(
                        line[2],
                        isCompleted,
                        eventTime[0],
                        eventTime[1]);
                taskList.add(newEventTask);
                break;

            default:
                break;
            }
        }
        return taskList;
    }

    /**
     * Saves the file to the hard disk.
     *
     * @param taskList The ArrayList of tasks to be saved into the hard disk.
     * @throws ChiikawaException
     */
    public void saveFile(ArrayList<Task> taskList) throws ChiikawaException {
        try {
            FileWriter fw = new FileWriter(this.filePath, false);
            for (Task currTask : taskList) {
                fw.append(currTask.toString()).append("\n");
            }
            fw.close();

        } catch (IOException e) {
            throw new ChiikawaException("file got pwoblem!!");
        }
    }
}
