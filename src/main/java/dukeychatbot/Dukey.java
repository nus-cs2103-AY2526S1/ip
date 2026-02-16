package dukeychatbot;

import java.util.Scanner;

/**
 * Constructs a Chatbot called Dukey, which helps compile a to-do list.
 * Dukey has other classes as part of it, including: TaskList, Ui, Storage and Parser.
 * TaskList handles functionality regarding adding and removing tasks, and marking and unmarking tasks.
 * Ui handles interactions with users.
 * Parser parses the user command and executes the logic behind it.
 * Storage handles loading and storing storage information to and from the hard drive.
 *
 * @author dongjun
 */
public class Dukey {
    private TaskList taskArray;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs the Dukey object.
     */
    public Dukey() {
        this("./data/dukey.txt");
        // Dukey.main(new String[] {});
    }

    private Dukey(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, this.ui);
        this.taskArray = new TaskList(this.storage.load(), this.ui);
        this.parser = new Parser(this.taskArray, this.ui, this.storage);
    }

    /**
     * Constructs Dukey chatbot.
     * Dukey helps users to compile a list of tasks,
     * with there being different types of tasks: todo, deadline and event.
     */
    public static void main(String[] args) {
        new Dukey("./data/dukey.txt").run();
    }

    /**
     * Runs the chatbot to start taking in user input.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isActive = true;

        System.out.println(this.ui.hello(taskArray.getTasks()));

        while (this.parser.getActiveStatus()) {
            String command = sc.nextLine().trim();
            String output = this.getResponse(command);
            System.out.println(output);
        }
    }

    /**
     * Returns welcome text for the GUI to show users.
     */
    public String welcome() {
        return this.ui.hello(taskArray.getTasks());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        if (!this.parser.getActiveStatus()) {
            return this.ui.chatboxClosedResponse();
        }

        return this.parser.parse(input);
    }
}

