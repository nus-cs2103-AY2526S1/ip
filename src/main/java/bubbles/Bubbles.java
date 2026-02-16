package bubbles;
import java.util.Scanner;

/**
 * The Bubbles class is the main entry point for running the chatbot.
 * It handles the command input and execution loop.
 */

public class Bubbles {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Bubbles(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Accepts commands from the user continuously and executes it till the user exits.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);

        String command = "";

        System.out.println(ui.showWelcome());
        ui.showLine();

        while (!command.equals("bye")) {
            command = scanner.nextLine();
            ui.showLine();

            try {
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
            } catch (BubblesException e) {
                ui.showBubblesException(e);
            }

            ui.showLine();
        }
        scanner.close();
    }

    public String getResponse(String input) {
        if (input.trim().equalsIgnoreCase("hello")) {
            return ui.showWelcome();
        }
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (BubblesException e) {
            return ui.showBubblesException(e);
        }
    }

    public static void main(String[] args) {
        new Bubbles("bubbles.txt").run();
    }
}