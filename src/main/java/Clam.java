import command.Command;
import exception.TodoException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import ui.Ui;
import task.TaskList;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;

public class Clam {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
