import command.Command;
import parser.Parser;
import storage.Storage;
import ui.Ui;
import task.TaskList;

import java.io.FileNotFoundException;

public class Clam {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Clam(String filepath) {
        storage = new Storage(filepath);
        ui = new Ui();

        try {
            tasks = storage.loadSaveFile();
        } catch (FileNotFoundException e) {
            // show error
            tasks = new TaskList();
            ui.chatbotPrint("It seems there's an error with loading the save file :(");
        } catch (Exception e) {
            ui.showError(e.getMessage());
        } finally {
            ui.printLine();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while(!isExit) {
            try {
                String input = ui.getInput();
                ui.printLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        Clam c = new Clam("./save.txt");
        c.run();
    }
}
