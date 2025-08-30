package aurora;

import aurora.command.Command;
import aurora.command.CommandReader;

import aurora.storage.Storage;
import aurora.task.Task;
import aurora.ui.Ui;

import java.util.Scanner;
import java.util.List;


/**
 * Aurora is a chatbot that manages tasks.
 */
public class Aurora {
    public static void main(String[] args) {
        Ui ui = new Ui(new Scanner(System.in));
        Storage storage = new Storage("./data/aurora.txt");
        List<Task> list = storage.load();

        ui.speakIntro();
        loop(ui, list, storage);
        ui.speakOutro();
    }

    private static void loop(Ui ui, List<Task> list, Storage storage) {
        String input = ui.readInput();

        while (!input.equalsIgnoreCase("bye")) {
            Command command = CommandReader.read(input);
            ui.speak(command.execute(list));
            storage.save(list);
            input = ui.readInput();
        }
    }
}
