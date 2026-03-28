package alex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents the storage mechanism for the user's tasks and command aliases.
 * Handles saving to and loading from the hard disk.
 */
public class Storage {
    private String tasklistFilePath;
    private String aliasesListFilePath;

    /**
     * Constructs a <code>Storage</code> instance with file paths for tasks and aliases.
     *
     * @param tasklistFilePath Path to the file storing task list.
     * @param aliasesListFilePath Path to the file storing command aliases.
     */
    public Storage(String tasklistFilePath, String aliasesListFilePath) {
        this.tasklistFilePath = tasklistFilePath;
        this.aliasesListFilePath = aliasesListFilePath;
    }

    /**
     * Saves the user's task list to the hard disk.
     *
     * @param taskList User's list of tasks.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveTask(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(tasklistFilePath);
        fw.write(String.valueOf(taskList.toSaveList()));
        fw.close();
    }

    /**
     * Saves the user's command aliases to the hard disk.
     *
     * @param alias User's alias mappings.
     * @throws IOException If there is an error writing to the file.
     */
    public void saveAliases(Alias alias) throws IOException {
        FileWriter fw = new FileWriter(aliasesListFilePath);
        fw.write(String.valueOf(alias.listOfAliases()));
        fw.close();
    }

    /**
     * Loads the task list from the hard disk when starting a new session.
     *
     * @return List of tasks previously saved.
     * @throws FileNotFoundException If the task list file does not exist.
     */
    public ArrayList<Task> loadTasklist() throws FileNotFoundException {
        File f = new File(tasklistFilePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (sc.hasNext()) {
            Task task = this.parseTaskLine(sc.nextLine());
            taskList.add(task);
        }

        sc.close();
        return taskList;
    }

    /**
     * Converts a line of saved task data into a <code>Task</code> object.
     *
     * @param line The line of text read from the saved task file.
     * @return The corresponding <code>Task</code> object.
     */
    private Task parseTaskLine(String line) {
        String[] parts = line.split(" / ");
        String type = parts[0];
        int taskState = Integer.parseInt(parts[1]);

        Task task = loadTask(type, parts);
        task.handleTask(taskState);
        return task;
    }

    /**
     * Creates a task object from its string representation.
     *
     * @param taskType Type of task (T - Todo, D - Deadline, E - Event).
     * @param parts Array of strings representing task details.
     * @return Task object created from the provided details.
     * @throws IllegalArgumentException If the task type is unrecognized.
     */
    private Task loadTask(String taskType, String[] parts) {
        switch (taskType) {
        case "T":
            return new Todo(parts[2]);
        case "D":
            return new Deadline(parts[2], parts[3]);
        case "E":
            return new Event(parts[2], parts[3], parts[4]);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }

    /**
     * Loads the user's command aliases from the hard disk.
     *
     * @return Alias object representing previously saved aliases.
     * @throws FileNotFoundException If the alias file does not exist.
     */
    public Alias loadAliases() throws FileNotFoundException {
        File f = new File(aliasesListFilePath);
        Scanner sc = new Scanner(f);
        HashMap<CommandType, String> aliasMap = new HashMap<>();

        while (sc.hasNext()) {
            String[] aliasBreakdown = parseAliasLine(sc.nextLine());
            String commandType = aliasBreakdown[0];
            CommandType command = CommandType.stringToEnum(commandType);
            String aliasKeyword = aliasBreakdown[1];
            aliasMap.put(command, aliasKeyword);
        }

        return new Alias(aliasMap);
    }

    /**
     * Splits a line from the alias file into its command and alias keyword parts.
     *
     * @param line The line read from the alias file.
     * @return Array with the first element as the command type and the second element as the alias keyword.
     */
    public String[] parseAliasLine(String line) {
        String[] aliasParts = line.split(": ");
        return aliasParts;
    }
}