package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import task.TaskList;

/**
 * Handles saving and loading save files corresponding to serialised TaskLists
 */
public class Storage {
    private static final String SAVE_PATH = "lmbd.save";

    /**
     * Serialises and writes to a save file named specified by the task list.
     * Creates the file and parent directories if it does not exist.
     */
    public static void save(TaskList taskList) throws IOException {
        File saveFile = new File(taskList.getSaveFile());

        try {
            if (saveFile.getParentFile() != null && !saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
        } catch (SecurityException e) {
            System.out.println("Unable to create parent directories while saving file");
            System.out.println(e.getMessage());
        }

        FileOutputStream fileOutputStream = new FileOutputStream(taskList.getSaveFile());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(taskList);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * Deserialises the TaskList from the lmbd.save file
     *
     * @return Deserialised TaskList
     */
    public static TaskList load() throws IOException, ClassNotFoundException {
        return load(SAVE_PATH);
    }

    /**
     * Deserialises the TaskList from the specified save file
     *
     * @return Deserialised TaskList
     */
    public static TaskList load(String saveFile) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(saveFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        TaskList ret = (TaskList) objectInputStream.readObject();
        objectInputStream.close();

        return ret;
    }
}
