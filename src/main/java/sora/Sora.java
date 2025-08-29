package sora;

import sora.list.TaskList;
import sora.storage.Storage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Sora {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final DateTimeFormatter format =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm",Locale.ENGLISH);

    public Sora(String filePath){
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load().getFullTasks());
        } catch (IOException e) {
            ui.showError("Cannot load storage tasks");
            tasks =new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String command = ui.readCommand();
                if(command.equals("bye")) {
                    isExit = true;
                }
                Parser.parse(command, tasks, ui, storage);
            } catch (SoraException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Sora("./data/sora.txt").run();
    }
}
