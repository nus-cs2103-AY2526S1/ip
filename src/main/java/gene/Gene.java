package gene;

import gene.command.Command;

import java.util.Scanner;


public class Gene {
    private final TaskList tasksList;

    public Gene(String filePath) {
        Storage storage = new Storage(filePath);
        this.tasksList = new TaskList(storage);
    }

    @Override
    public String toString() {
        return this.tasksList.toString();
    }

    /**
     * Kickstarts the program. It will be called by main
     * This is required as when program starts here,
     * it has access to private methods in Gene
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        Ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            Command c = Parser.parse(input);
            String response = c.execute(this.tasksList);
            Ui.printFormatResponse(response);
            isExit = c.isExit();
        }
    }

    public static void main(String[] args) {
        new Gene("./data/gene.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(this.tasksList);
    }
}
