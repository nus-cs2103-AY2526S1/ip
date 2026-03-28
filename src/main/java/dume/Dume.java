package dume;

import dume.parser.Parser;
import dume.storage.Storage;
import dume.task.Task;
import dume.task.TaskList;
import dume.ui.Ui;

import java.util.Scanner;

/**
 * Main logic of the application.
 * Initializes storage and task list, processes user commands.
 */
public class Dume {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Dume() {
        ui = new Ui();
        storage = new Storage("data/dume.txt");
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            return Parser.processGui(input, tasks, ui, storage);
        } catch (DumeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        String logo = """
                 ______   __   __  __   __         _______
                |      | |  | |  ||  |_|  |       |       |
                |  _    ||  | |  ||       | ____  |    ___|
                | | |   ||  |_|  ||       ||____| |   |___
                | |_|   ||       ||       |       |    ___|
                |       ||       || ||_|| |       |   |___
                |______| |_______||_|   |_|       |_______|
                """;

        Ui ui = new Ui();
        Storage storage = new Storage("data/dume.txt");
        TaskList tasks = new TaskList(storage.load());

        ui.welcome(logo);

        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                shouldExit = Parser.process(input, tasks, ui, storage);
            } catch (DumeException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
    }
}