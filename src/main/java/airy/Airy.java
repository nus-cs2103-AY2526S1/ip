package airy;

/**
 * This is my main class which implements the logic for handling
 * the different tasks and commands
 */
public class Airy {
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * This is my Airy constructor which initializes the ArrayList for Task and Ui
     */
    public Airy() {
        // Initializes the Ui object
        this.ui = new Ui();
        // Initializes the Parser object
        this.parser = new Parser();
        // Load tasks from disk instead of starting empty
        // If no data, create fie and return empty ArrayList
        this.tasks = new TaskList(Storage.load());
    }

    /**
     * Getter method to print Welcome message
     */
    public String getWelcomeUi() {
        return ui.getWelcomeMessage();
    }

    /**
     * Marks Task and saves it to Storage.
     * Show confirmation UI when completed.
     *
     * @param taskIndex the task index to be marked and saved.
     * @return the confirmation message that the task has been marked.
     */
    public String markAndSaveTask(int taskIndex, Boolean mark) {
        Task taskObj = mark ? tasks.markTask(taskIndex) : tasks.unmarkTask(taskIndex);
        // Save after change
        Storage.save(tasks.getTasks());
        return mark ? ui.showMark(taskObj) : ui.showUnmark(taskObj);
    }

    /**
     * Adds Task to tasks array and saves it to Storage.
     * Show confirmation UI when completed.
     *
     * @param task the task object to be added and saved.
     * @return the confirmation message that the task has been added.
     */
    public String addAndSaveTask(Task task) {
        // Save to do object into array
        tasks.addTask(task);
        // Save after change
        Storage.save(tasks.getTasks());
        // Show user task has been added
        return ui.showTaskAdded(task, tasks.getSize());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String result = "";
        int tasksSize = tasks.getSize();

        try {
            if (input.isEmpty()) {
                throw new AiryException("Please enter a keyword e.g."
                        + " todo, deadline, event, list, mark & unmark");
            }

            String[] parsed = parser.parse(input);
            String command = parsed[0];
            String args = parsed[1];

            // break immediately if bye
            if (command.equals("bye")) {
                return ui.byeMessage();
            }

            switch (command) {
            case "list": {
                result = ui.showTaskList(tasks.getTasks());
                break;
            }
            case "mark": {
                // Ensure args isn't empty & parses string to int
                int taskNum = parser.parseTaskNum(command, args, tasksSize);
                result = markAndSaveTask(taskNum, true);
                break;
            }
            case "unmark": {
                int taskNum = parser.parseTaskNum(command, args, tasksSize);
                result = markAndSaveTask(taskNum, false);
                break;
            }
            case "todo": {
                // Ensures arg isn't empty
                parser.checkArg(command, args);
                // New to do object
                Task todoTask = new Todo(args);
                result = addAndSaveTask(todoTask);
                break;
            }
            case "deadline": {
                parser.checkArg(command, args);
                String[] parts = parser.parseDeadline(args);
                // New deadline object, parts[0] = taskName, parts[1] = dueDate
                Deadline deadlineTask = new Deadline(parts[0], parts[1]);
                result = addAndSaveTask(deadlineTask);
                break;
            }
            case "event": {
                parser.checkArg(command, args);
                String[] parts = parser.parseEvent(args);
                // New event object, parts[0] = taskName, parts[1] = startDate, parts[2] = endDate
                Event eventTask = new Event(parts[0], parts[1], parts[2]);
                result = addAndSaveTask(eventTask);
                break;
            }
            case "delete": {
                // Gets unsorted parsed int array to show that we deleted the tasks in the order he specified
                int[] parsedDeleteIndexes = parser.parseDelete(args, tasksSize);
                // Gets sorted array in descending order to ensure indices when deleting is correct
                int[] descDeleteIndexes = parser.descDeleteArray(parsedDeleteIndexes);
                // Saves the UI result before deletion as deletion messes up the indices
                for (int i = 0; i < parsedDeleteIndexes.length; i++) {
                    result += ui.showTaskRemoved(
                            tasks.get(parsedDeleteIndexes[i]),
                            tasks.getSize());
                }
                for (int i = 0; i < descDeleteIndexes.length; i++) {
                    tasks.deleteTask(descDeleteIndexes[i]);
                }

                // Save after change
                Storage.save(tasks.getTasks());
                break;
            }
            case "find": {
                parser.checkArg(command, args);
                // Ensures encapsulation is kept, giving all ArrayLists<Task> to TaskList
                TaskList matchingTasks = tasks.findTasks(args);
                result = ui.showMatchingTasks(matchingTasks);
                break;
            }
            default: {
                // Unrecognized command
                throw new AiryException("Unrecognized command");
            }
            }
        } catch (AiryException e) { // Catch exceptions
            return e.getMessage();
        }
        return result;
    }
}
