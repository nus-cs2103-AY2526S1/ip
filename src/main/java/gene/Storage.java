package gene;

import gene.enums.Commands;
import gene.tasks.DeadlineTask;
import gene.tasks.EventTask;
import gene.tasks.Task;
import gene.tasks.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Parses a line from the .txt Storage file and converts it into a Task object
     * to be added to the initial TaskList
     *
     * @param line in the .txt Storage file
     * @return Task object parsed from the line
     */
    private Task parseStorageLine(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isMarked = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String firstDate = parts.length > 3 ? parts[3].trim() : "";
        String secondDate = parts.length > 4 ? parts[4].trim() : "";
        Task task = null;
        try {
            //Commands.valueOf will throw IllegalArgumentException if type is invalid
            Commands commandType = Commands.valueOf(type);
            switch (commandType) {
            case TODO:
                task = new TodoTask(description, isMarked);
                break;
            case DEADLINE:
                task = new DeadlineTask(description, firstDate, isMarked);
                if (isMarked) task.mark();
                break;
            case EVENT:
                task = new EventTask(description, firstDate, secondDate, isMarked);
                break;
            default:
                //Empty as it will definitely be one of the above 3 types
                //If it is not, illegalArgumentException will be thrown at Commands.valueOf,
                //prior to switch statement
            }
        } catch (IllegalArgumentException e) {
            //Print used here as it is not an error that should block the program
            //It will just skip the line in the .txt file
            Ui.printFormatResponse("Skipped invalid event in database");
        }
        return task;
    }

    /**
     * Reads the .txt file and outputs an initial Array List of Task
     * This is needed to read user data from previous run
     * It will skip the line in .txt file if it has the wrong format
     *
     * @return Array List of Task to be used at start of program
     */
    public ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs(); // ensure parent directories exist
            file.createNewFile();
            return tasks; // file is empty, return empty list
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseStorageLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }

    /**
     * Reads the existing data and writes into a txt file
     * This is required to save the data for subsequent run if program
     * terminates
     *
     * @param tasksList the current task list used by program
     */
    public void saveTasksToFile(ArrayList<Task> tasksList) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasksList) {
                writer.write(task.toDbString() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }
}
