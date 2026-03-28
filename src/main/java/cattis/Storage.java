package cattis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cattis.exception.CattisException;
import cattis.exception.CattisSaveFileException;
import cattis.task.Task;
import cattis.task.TaskList;

/**
 * A class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String CREATE_NEW_FILE_MSG = "Create new file ";
    private static final String CANNOT_FIND_FILE_MSG = "Cannot find file ";
    private static final String FAILED_LOAD_FILE_MSG = "Failed to load file ";
    private static final String FAILED_SAVE_FILE_MSG = "Failed to save file ";
    private static final String FAILED_CREATE_DIR_MSG = "Failed to create directory ";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from file {@code filePath} and supplies to the
     * {@code cattis} instance.
     *
     * If {@code filePath} does not exist, the system will create new file path
     * for the user.
     * @param cattis application instance
     * @throws CattisException If unsuccessfully load the file
     */
    public void load(CattisInterface cattis) throws CattisException {
        File tempFile = new File(this.filePath);
        if (!tempFile.exists()) {
            cattis.getUi().showMessage(CREATE_NEW_FILE_MSG + this.filePath);
        }
        ArrayList<String> results = new ArrayList<>();
        try (FileReader f = new FileReader(this.filePath)) {
            StringBuffer sb = new StringBuffer();
            while (f.ready()) {
                char c = (char) f.read();
                if (c == '\n') {
                    results.add(sb.toString());
                    sb = new StringBuffer();
                } else {
                    sb.append(c);
                }
            }
            if (!sb.isEmpty()) {
                results.add(sb.toString());
            }
        } catch (FileNotFoundException err) {
            throw new CattisException(CANNOT_FIND_FILE_MSG + this.filePath);
        } catch (IOException err) {
            throw new CattisException(FAILED_LOAD_FILE_MSG + err.getMessage());
        }
        for (String s : results) {
            cattis.getTaskList().add(Task.decode(s));
        }
    }

    /**
     * Saves <code>tasks</code> to the target file {@code filePath}
     * If file path is not specified or does not exist, the system will
     * create new file path for user.
     * @param tasks tasks to be saved
     * @throws CattisException If unsuccessfully save the file
     */
    public void save(TaskList tasks) throws CattisException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean isCreated = parentDir.mkdirs();
            if (!isCreated) {
                throw new CattisSaveFileException(
                        FAILED_CREATE_DIR_MSG + parentDir.getPath());
            }
        }

        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(tasks.toEncodedString());
        } catch (IOException err) {
            throw new CattisSaveFileException(FAILED_SAVE_FILE_MSG + err.getMessage());
        }
    }
}
