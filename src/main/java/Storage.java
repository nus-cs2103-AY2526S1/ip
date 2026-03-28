import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import exceptions.DukeyException;
import tasklist.TaskList;
import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


/* Storage class to handle interactions with .txt file */
class Storage {

    private TaskList taskList;
    private String filePath;

    /**
    * Initialise storage.
    */
    public Storage(TaskList taskList, String filePath) {

        this.taskList = taskList;
        this.filePath = filePath;
    }

    /**
     * Load .txt file.
     * If no existing file, create new one.
     * If files exists, add tasks to taskList.
     * Handle errors that may arise from opening/ creating file.
     */
    public String load() {
        String output = "";
        //read .txt file
        try {
            // Create a File object pointing to filePath
            File file = new File(filePath);

            // If the file does not exist, create it
            if (!file.exists()) {
                if (file.createNewFile()) {
                    output = "File created: " + file.getName();
                    System.out.println("File created: " + file.getName());
                } else {
                    output = "Failed to create the file.";
                    System.out.println("Failed to create the file.");
                }
            }

            // Read the file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                add(line); // Add the task to taskList
            }
            scanner.close(); // Always close the scanner after use

        } catch (IOException e) {
            output = "An error occurred while handling the file.";
            System.out.println("An error occurred while handling the file.");
            e.printStackTrace();
        } catch (DukeyException e) {
            output = e.getMessage();
            System.out.println(e.getMessage());
        }
        return output;
    }

    /**
     * Adds .txt representation of task to taskList.
     * Read the .txt format of task and initialise the task.
     * Add the new task to taskList.
     *
     * @param line to be read
     * @throws DukeyException if task description missing, or task type is unspecified.
     */
    public void add(String line) throws DukeyException {
        //format of line in .txt file is : [E][ ] project meeting /from Mon 2pm /to 4pm)
        assert(line.length() > 1);
        char type = line.charAt(1);
        assert(line.length() > 4);
        boolean isMarked = line.charAt(4) == 'X' ? true : false;
        String rest = line.substring(7);
        Task task = null;
        try {
            switch (type) {
            case 'T':
                task = new ToDo(rest, isMarked);
                break;
            case 'D':
                task = new DeadLine(rest, isMarked);
                break;
            case 'E':
                task = new Event(rest, isMarked);
                break;
            default:
                throw new DukeyException("Unspecified task type");
            }
        } catch (DukeyException exception) {
            throw exception;
        }
        assert(task != null);
        taskList.addTask(task);
    }

    /**
     * Writes taskList into .txt file.
     *
     * @return String message to be returned.
     */
    public String rewriteFile() {
        try {
            // Step 1: Create a FileWriter object in write mode (overwrite mode)
            FileWriter writer = new FileWriter(filePath, false); // false to overwrite
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Step 2: Loop through the ArrayList and write each element to the file
            for (Task task : taskList.arr) {
                assert(task != null);
                bufferedWriter.write(task.toTxt());
                bufferedWriter.newLine(); // Add a new line after each item
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File has been rewritten with the ArrayList content.";
    }
}
