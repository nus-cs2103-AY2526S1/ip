package joe;

import java.time.format.DateTimeParseException;

import joe.exception.InvalidJoeInputException;
import joe.parser.Parser;
import joe.storage.Storage;
import joe.task.TaskList;
import joe.ui.Ui;

public class Joe {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public static void main(String[] args) {
        Joe joe = new Joe();
        boolean isOn = true;
        try {
            joe.ui.welcomeText();
            while (isOn) {
                String input = joe.ui.takeInput();
                if (input.equals("bye")) {
                    isOn = false;
                }
                joe.parser.executeCommand(input);
            }
            joe.ui.byeText();

        } catch (InvalidJoeInputException e) {
            System.out.println(e.getMessage());
            joe.ui.line();
            joe.ui.takeInput();
        }

    }

    public Joe() {
        // Constructor can be used for initialization if needed
        this.ui = new Ui();
        this.storage = new Storage("data/joe.txt");
        this.taskList = new TaskList(this.storage.loadTodoList());
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    public String getResponse(String input) {
        try {
            String output = parser.executeCommand(input);
            return output;
        } catch (InvalidJoeInputException e) {
            return e.getMessage();
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
