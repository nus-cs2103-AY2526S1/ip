import java.util.Scanner;

public class MinhGPT {
    /**
     * Main entry-point for the program.
     */
    public static void main(String[] args) {
        // Get all flags
        boolean isFresh = args.length > 0 && args[0].equals("--fresh");

        // Initialisation
        Task.initialise();
        Command.initialise();
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = isFresh ? new TaskList() : storage.loadTasks();
        ui.printWelcome();

        // Main program loop
        while (true) {
            ui.printNext();
            String input = scanner.nextLine().trim();
            ui.printSeperate();

            Command cmd = Command.parseCommand(input);
            cmd.execute(input, taskList, ui, storage);
            if (cmd instanceof CommandBye) {
                break;
            }
        }

        // Cleaning up resources and exit
        scanner.close();
    }
}
