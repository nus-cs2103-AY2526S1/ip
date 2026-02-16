package dukeychatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dukeychatbot.tasktypes.Task;

/**
 * Constructs the Storage class which deals with loading and saving the task list
 * onto the hard drive using the Dukey.txt file.
 *
 * @author dongjun
 */
public class Storage {
    private final String filepath;
    private ArrayList<String> fileContent = new ArrayList<>();
    private Ui ui;

    /**
     * Constructs the Storage object.
     */
    public Storage(String filePath, Ui ui) {
        this.filepath = filePath;
        this.ui = ui;
        try {
            File dukeyText = new File(this.filepath);
            File parent = dukeyText.getParentFile();

            if (parent != null) {
                parent.mkdirs();
            }
            if (!dukeyText.exists()) {
                dukeyText.createNewFile();
            }

            Scanner myReader = new Scanner(dukeyText);
            while (myReader.hasNextLine()) {
                String input = myReader.nextLine();
                fileContent.add(input);
            }
        } catch (NullPointerException | FileNotFoundException e) {
            this.ui.formattedErrorResponse("Loading hard disk file failed, error: " + e.getMessage());
        } catch (IOException e) {
            this.ui.formattedErrorResponse("Runtime error: " + e.getMessage());
        }
    }

    /**
     * Returns the file content from Dukey.txt.
     *
     * @return Content of Dukey.txt
     */
    public ArrayList<String> load() {
        return this.fileContent;
    }

    /**
     * Saves the tasks in the hard disk by updating the dukey.txt file.
     *
     * @param tasks ArrayList of type Task.
     */
    public String save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.filepath);
            // Concatenate strings together to input into the text file
            StringBuilder resultText = new StringBuilder();

            for (int count = 1; count <= tasks.size(); count++) {
                Task currentTask = tasks.get(count - 1);
                if (count == tasks.size()) {
                    resultText.append(currentTask.toString());
                } else {
                    resultText.append(currentTask.toString()).append("\n");
                }
            }
            writer.write(resultText.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            return this.ui.formattedErrorResponse("File cannot be found: " + e.getMessage());
        } catch (IOException e) {
            return this.ui.formattedErrorResponse("An error occurred while saving. Error: " + e.getMessage());
        }
        return "Successfully saved updated tasks to hard drive.";
    }
}
