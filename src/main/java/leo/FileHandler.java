package leo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import leo.functions.task.Deadline;
import leo.functions.task.Event;
import leo.functions.task.Task;
import leo.functions.task.ToDo;

/**
 * The FileHandler class provides utility methods to manage file operations
 * such as:
 * - loading data from files
 * - writing data to files
 * - deleting data from files
 * - retrieving data from files
 */
public class FileHandler {
    private File file;

    /**
     * Instantiates the FileHandler object.
     *
     * @param filePath The file path used for carrying out file operations.
     */
    public FileHandler(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the file used to carry out file operations.
     *
     * @return The FileHandler object.
     * @throws IOException If file fails to create due to input errors.
     */
    public FileHandler loadFile() throws IOException {
        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        return this;
    }

    /**
     * Writes data to the file.
     *
     * @param textToAdd Text to be written to the file.
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    public void appendToFile(String textToAdd) throws IOException {
        try {
            FileWriter writer = new FileWriter(this.file, true);

            if (this.file.length() > 0) {
                //Solution adapted from
                // https://www.perplexity.ai/search/add-new-line-infront-of-text-i-zPpF4r0fTCSRDpGFnPRUxg
                writer.write(System.lineSeparator() + textToAdd);
            } else {
                writer.write(textToAdd);
            }

            writer.close();
        } catch (IOException e) {
            throw new IOException("Invalid file path given");
        }
    }

    /**
     * Deletes data from the file.
     *
     * @param listItems Remaining list of tasks after deleting.
     * @throws IOException If file is in the wrong format or cannot be accessed.
     */
    //Solution adapted from https://www.perplexity.ai/search/delete-text-from-file-in-java-8_mCJnSyQZmnkscaHNiuUw
    public void deleteFromFile(ArrayList<Task> listItems) throws IOException {
        try {
            FileWriter writer = new FileWriter(this.file, false);

            for (int i = 0; i < listItems.size(); i++) {
                if (i == 0) {
                    writer.write(listItems.get(i).appendToFile());
                } else {
                    writer.write(System.lineSeparator() + listItems.get(i).appendToFile());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IOException("Invalid file path given");
        }
    }

    /**
     * Retrieves and returns the list of tasks from the file.
     *
     * @return List of tasks.
     * @throws FileNotFoundException If file is in the wrong format or cannot be accessed.
     * @throws ArrayIndexOutOfBoundsException If task details are in the incorrect format.
     */
    public ArrayList<Task> retrieveTasksFromFile() throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        ArrayList<Task> listItems = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);

            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                if (!nextLine.isEmpty()) {
                    Task task = getTaskFromFile(nextLine);

                    if (task != null) {
                        listItems.add(task);
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Invalid file path given");
        }

        return listItems;
    }

    /**
     * Returns a task from the file.
     *
     * @param taskFromFile Text information of task stored in the file.
     * @return Task object.
     * @throws ArrayIndexOutOfBoundsException If task details are in the incorrect format.
     */
    private Task getTaskFromFile(String taskFromFile) throws ArrayIndexOutOfBoundsException {
        Task task = null;
        try {
            //Solution adapted from https://www.perplexity.ai/search/split-string-by-in-java-U7_N33gYS4651R96jeoK8Q
            String[] taskDescriptionList = taskFromFile.split("\\|");
            String taskDescription = taskDescriptionList[2].trim();

            DateTimeParser dateTimeParser = new DateTimeParser();

            switch(taskDescriptionList[0].trim()) {
            case "ToDo":
                task = new ToDo(taskDescription);
                break;
            case "Event":
                String[] eventDates = taskDescriptionList[3].split("-");
                String startDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[0].trim());
                String endDateTime = dateTimeParser.formatDateTimeFromFile(eventDates[1].trim());
                task = new Event(taskDescription, startDateTime, endDateTime);
                break;
            case "Deadline":
                //Solution adapted from
                // https://www.perplexity.ai/search/can-localdatetime-parse-days-o-Ub7ZJIDuRtifbzHjhcOC9Q
                String dateTime = dateTimeParser.formatDateTimeFromFile(taskDescriptionList[3].trim());
                task = new Deadline(taskDescription, dateTime);
                break;
            default:
                break;
            }

            if (task != null && taskDescriptionList[1].trim().equals("X")) {
                task.markTask();
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException(
                    "Failed to load the task ("
                    + taskFromFile
                    + ") due to invalid format."
            );
        }

        return task;
    }
}
