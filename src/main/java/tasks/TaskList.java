package tasks;

import candy.Ui;

import java.util.ArrayList;

import exceptions.EditTaskErrorException;
import exceptions.InvalidInputException;
import exceptions.InvalidTimeInputException;
import exceptions.MyNumberFormatException;
import exceptions.NoEndException;
import exceptions.NoStartException;
import exceptions.NoTaskException;
import storage.Storage;

/**
 * Represents all the tasks
 */
public class TaskList {
    private ArrayList<Task> allText;
    private ArrayList<String> textToSave;
    private Storage taskStorage;

    //Showed chatGPT a snippet of code with emoji
    //chatGPT suggested to put them as constants
    private static final String DONE_EMOJI = "😋";
    private static final String UNDONE_EMOJI = "😝";
    private static final String CANDY_EMOJI = "🍬";
    private static final String SAD_FACE_EMOJI = "😢";

    /**
     * Constructor to initialise array and storage
     *
     * @param filePath string description of file path
     */
    public TaskList(String filePath) {
        this.taskStorage = new Storage(filePath);
        this.textToSave = taskStorage.readToString();
        this.allText = taskStorage.readToTask();
    }

    /**
     * Rewrites the tasks in the storage
     * to the updated version
     */
    public void overwriteStorage() {
        //ChatGPT suggested to use StringBuilder instead of String
        //update the string of tasks
        StringBuilder newList = new StringBuilder();
        for (String text : textToSave) {
            newList.append(text).append("\n");
        }
        //save to storage
        taskStorage.write(newList.toString(), false);
    }

    /**
     * Converts list of tasks to String
     *
     * @param tasks     the list of task to convert
     * @param startLine the comment candy would give before saying the
     *                  tasks
     * Note: Method suggested by chatGPT for printList and findTask
     */
    private String taskListToString(ArrayList<Task> tasks, String startLine) {
        //startLine is the first line of text eg: Here are your task
        StringBuilder sb = new StringBuilder(startLine).append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ")
                    .append(tasks.get(i)).append("\n");
        }
        sb.append("You have " + tasks.size() + " sweet(s) here!");
        return sb.toString();
    }

    /**
     * Returns string of all tasks in allText
     */
    public String printList() {
        return taskListToString(allText, "All your sweets " + CANDY_EMOJI + " are here:");
    }

    /**
     * Reads the task number that user wants to update/delete
     *
     * @param order String format of the "integer" user input
     * @return      task number in Integer
     */
    public int getTaskNumber(String order) {
        try {
            int taskNumber = Integer.parseInt(order.trim());
            if (taskNumber <= 0 || taskNumber > allText.size()) {
                throw new EditTaskErrorException();
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new MyNumberFormatException();
        }
    }

    /**
     * Returns a string notifying that a task in the array
     * is marked or unmarked
     *
     * @param text the index of where task is stored
     * @param mark to mark task as done/undone
     */
    public String doMark(String text, boolean mark) {
        try {
            //with the help of chatGPT, I found the replaceAll function
            //and with its help im able to get the string without hardcoding
            String number = text.replaceAll("[^0-9-]", "");;
            int order = getTaskNumber(number);

            //marks the specified task
            Task toMark = allText.get(order - 1);
            String dialog;
            if (mark) {
                toMark.markDone();
                assert toMark.getStatusIcon().equals("X") : "task should be marked done";
                dialog = "Candy marked this sweet edible ^-^ " + DONE_EMOJI
                        + "\n    ";
            } else {
                toMark.markUndone();
                assert toMark.getStatusIcon().equals(" ") : "task should be marked undone";
                dialog = "Candy marked this sweet inedible ^-^ " + UNDONE_EMOJI
                        + "\n    ";
            }

            //edit the task in the array
            textToSave.set(order - 1, toMark.toSave());
            overwriteStorage();
            return dialog + toMark.toString();
        } catch (MyNumberFormatException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Returns string notifying a task is deleted from array
     *
     * @param text Users input
     */
    public String delete(String text) {
        try {
            //did the same as doMark
            String number = text.replaceAll("[^0-9-]", "");;
            int order = getTaskNumber(number);

            //update the array
            Task toDelete = allText.get(order - 1);
            allText.remove(order - 1);
            textToSave.remove(order - 1);

            overwriteStorage();

            return "Candy ate this sweet " + DONE_EMOJI
                    + "\n    " + toDelete.toString()
                    + "\nNow you have " + allText.size() + " sweet(s) left";
        } catch (MyNumberFormatException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }

    /**
     * Returns string to notify that task is added
     *
     * @param text string description of the task
     */
    public String addTask(String text, String type) {
        try {
            //create the task
            TaskInformation information = new TaskInformation(text, type);
            Task newTask;
            if (type.equals("todo")) {
                newTask = new TodoTask(information);
            } else if (type.equals("deadline")) {
                newTask = new DeadlineTask(information);
            } else {
                newTask = new EventTask(information);
            }

            //add to array
            allText.add(newTask);
            textToSave.add(newTask.toSave());

            //save to storage
            taskStorage.write(newTask.toSave() + "\n", true);
            return "Candy successfully made this sweet: \n    "
                    + newTask.toString() + "\nNow you have "
                    + allText.size() + " sweet(s) in your list.";
        } catch (InvalidInputException | NoEndException | NoStartException
                 | NoTaskException | InvalidTimeInputException e) {
            return Ui.printError(e);
        }
    }


    /**
     * Returns string of all tasks found with keyword
     *
     * @param text string description of the command
     */
    public String findTask(String text) {
        String keyword = text.substring(4).trim();
        if (keyword.isEmpty()) {
            return "Candy is lost. What sweet " + CANDY_EMOJI+ " are you searching for?";
        }

        ArrayList<Task> foundList = new ArrayList<>();
        int max = allText.size();

        //loop through all the task
        for (int i = 0; i < max; i++) {
            Task currentTask = allText.get(i);
            String description = currentTask.getDescription().toLowerCase();

            //should not happen:
            if (description.isEmpty()) {
                continue;
            }

            //check if it is what user is finding for and add
            if (description.contains(keyword.toLowerCase())) {
                foundList.add(currentTask);
            }
        }

        if (foundList.isEmpty()) {
            return "Candy didn't find matching sweets " + SAD_FACE_EMOJI;
        }

        //convert the array of found tasks into string
        return taskListToString(foundList, "Candy found these sweets "
                + CANDY_EMOJI + " !");
    }

    /**
     * Edits the information in the given task
     *
     * @param newInfo The String of new information users typed
     *                in
     * @param toEdit The Task to update
     */
    public void editInformation(String newInfo, Task toEdit) {
        String type = toEdit.getType();
        String fullText = type + " " + newInfo;
        TaskInformation updateInfo = new TaskInformation(fullText, type);
        toEdit.setInformation(updateInfo);
    }

    /**
     * Checks if input is of correct format with the '/'
     *
     * @param details The string of user's input after command word
     * @return        The index of '/'
     */
    public int checkIndex(String details) {
        int detailStart = details.indexOf("/");
        if (detailStart == -1) {
            throw new InvalidInputException();
        }
        return detailStart;
    }

    /**
     * Updates the tasks in the storage
     */
    public String updateTask(String text) {
        try {
            //String after the edit word
            String details = text.substring(4);

            //get index of the task to edit
            int detailStart = checkIndex(details);

            //get task
            String number = details.substring(0, detailStart).trim();
            int order = getTaskNumber(number);
            Task toEdit = allText.get(order - 1);

            //String after specifying which task to edit
            String taskDetails = details.substring(detailStart + 1).trim();

            //edit the task
            editInformation(taskDetails, toEdit);
            textToSave.set(order - 1, toEdit.toSave());
            overwriteStorage();

            return "Candy successfully remade this sweet: \n    "
                    + toEdit.toString();
        } catch (MyNumberFormatException | NoTaskException | NoStartException
                 | NoEndException | InvalidInputException
                 | InvalidTimeInputException | EditTaskErrorException e) {
            return Ui.printError(e);
        }
    }
}
