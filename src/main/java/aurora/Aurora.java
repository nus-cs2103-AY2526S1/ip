package aurora;

import java.util.Scanner;

import aurora.command.Command;
import aurora.command.CommandReader;
import aurora.storage.Storage;
import aurora.task.TaskList;
import aurora.ui.Ui;

/**
 * Aurora is a chatbot that manages tasks.
 */
public class Aurora {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;

    public Aurora(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.list = storage.load();
    }

    public void run() {
        ui.speakIntro();
        loop();
        ui.speakOutro();
    }

    private void loop() {
        String input = ui.readInput();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            ui.speak(command.execute(list));
            storage.save(list);
            input = ui.readInput();
        }
    }

    public static void main(String[] args) {
        new Aurora("./data/aurora.txt").run();
    }
}
