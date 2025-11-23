package diablo.ui;

import diablo.task.Deadline;
import diablo.task.Event;
import diablo.task.Task;
import diablo.task.ToDo;
import diablo.exception.DiabloException;

import java.io.*;
import java.util.Scanner; // Import Scanner class


/**
 * Represents the main class for the Diablo chatbot.
 */
public class Diablo {
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Diablo(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.readDiablo());
        } catch (IOException e) {
            this.taskList = new TaskList();
        }

    }

    /**
     * Makes sense of information typed in by the user, and outputs a string for Diablo to message back.
     * @param input String input by the user.
     * @return String message of Diablo response.
     */
    public String[] getOutput(String input) {
        String[] parsedInput = parser.parse(input);
        assert parsedInput[0] != null: "Parsed input should have at least an identifier";

        String inputType = parsedInput[0];
        switch (inputType) {
        case "bye":
            return handleBye();
        case "list": {
            return handleList();
        }
        case "mark": {
            return handleMark(parsedInput);
        }
        case "delete": {
            return handleDelete(parsedInput);
        }
        case "find": {
            return handleFind(parsedInput);
        }
        case "deadline": {
            return handleDeadline(parsedInput);
        }
        case "todo": {
            return handleToDo(parsedInput);
        }
        case "event": {
            return handleEvent(parsedInput);
        }
        case "help": {
            return handleHelp(parsedInput);
        }
        default: {
            return handleDefault();
        }
        }

    }

    /**
     * Writes current task list into diablo.txt for storage.
     */
    public void addToStorage() {
        try {
            storage.writeDiablo(taskList.formatForStorage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] handleBye() {
        String byeMessage = "Bye. Hope to see you again soon!";
        return new String[] {"1", byeMessage};
    }

    private String[] handleList() {
        String list = taskList.formatForWindow();
        return new String[] {"0", list};
    }

    private String[] handleFind(String[] parsedInput) {
        String list = taskList.filterAndFormatForWindow(parsedInput[1]);
        return new String[] {"0", list};
    }

    private String[] handleMark(String[] parsedInput) {
        try {
            String taskMarkedMessage = taskList.mark(Integer.parseInt(parsedInput[1]));
            return new String[] {"0", taskMarkedMessage};
        } catch (DiabloException e) {
            return new String[] {"0", e.getMessage()};
        }
    }

    private String[] handleDelete(String[] parsedInput) {
        try {
            String taskDeletedMessage = taskList.delete(Integer.parseInt(parsedInput[1]));
            return new String[] {"0", taskDeletedMessage};
        } catch (DiabloException e) {
            return new String[] {"0", e.getMessage()};
        }
    }

    private String[] handleDeadline(String[] parsedInput) {
        try {
            if (parsedInput[1].equals("-1")) {
                throw new DiabloException(parsedInput[2]);
            }

            Task deadline = new Deadline(parsedInput[1], parsedInput[2]);
            return new String[] {"0", taskList.addTask(deadline)};

        } catch (DiabloException e) {
            return new String[] {"0", e.getMessage()};
        }
    }

    private String[] handleToDo(String[] parsedInput) {
        try {
            if (parsedInput[1].equals("-1")) {
                throw new DiabloException(parsedInput[2]);
            }

            Task todo = new ToDo(parsedInput[1]);
            return new String[] {"0", taskList.addTask(todo)};

        } catch (DiabloException e) {
            return new String[] {"0", e.getMessage()};
        }
    }

    private String[] handleEvent(String[] parsedInput) {
        try {
            if (parsedInput[1].equals("-1")) {
                throw new DiabloException(parsedInput[2]);
            }

            Task event = new Event(parsedInput[1], parsedInput[2], parsedInput[3]);
            return new String[] {"0", taskList.addTask(event)};

        } catch (DiabloException e) {
            return new String[] {"0", e.getMessage()};
        }
    }

    private String[] handleDefault() {
        String unknownMessage = "I don't know what that means!!! Type 'help' to see a list of functions.";
        return new String[] {"0", unknownMessage};
    }

    private String[] handleHelp(String[] parsedInput) {
        String helpMessage = parsedInput[1];
        return new String[] {"0", helpMessage};
    }


}
