package john.core;

import john.adapters.ConsoleUi;
import john.core.command.Command;
import john.core.command.CommandResult;
import john.core.exception.ParseException;
import john.core.parser.CommandParser;
import john.model.TaskList;

import john.adapters.FileStorage;
import john.ports.Ui;


import java.util.Scanner;

public class John {
    private static TaskList tasks;
    private static final FileStorage storage = new FileStorage("src/data/data.txt");
    private static final ConsoleUi ui = new ConsoleUi(new Scanner(System.in), System.out);
    public static void run() {
        tasks = storage.load();
        try (Ui ignored = ui) {
            ui.showWelcome(tasks.size());
            while (true) {
                String line = ui.nextCommand();        // or however you read a full line
                if (line == null) break;
                try {
                    Command cmd = CommandParser.parse(line);
                    CommandResult res = cmd.execute(tasks, storage, ui);
                    if (!res.feedback().isBlank()) ui.showMessage(res.feedback());
                    if (res.exit()) break;
                } catch (ParseException e) {
                    ui.showMessage(e.getMessage());
                }
            }
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        John.run();
    }
}