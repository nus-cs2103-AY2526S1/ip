package meo;

import meo.command.Command;
import meo.data.TextList;
import meo.parser.CommandParser;
import meo.ui.Ui;

/**
 * Run Meo
 */

public class Meo {
    private FileHandler fileHandler;
    private TextList textList = FileHandler.getList();
    private Ui ui = new Ui();

    /**
     * Starts the bot Meo.
     */
    public void run() {
        ui.showWelcomeMessage();
        // if (FileHandler.getList() != null) {
        //     textList = FileHandler.getList();
        // }
        runCommandParseUntilExit();
    }

    /**
     * Runs the command parser on user's input until input is "exit".
     */
    private void runCommandParseUntilExit() {
        Command command = new Command();
        do {
            try {
                String commandText = ui.getUserCommand();
                command = new CommandParser().parser(commandText);
                command.execute(ui, textList, fileHandler);
            } catch (MeoException e) {
                ui.showErrorMessage();
                System.out.println(e);
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        } while (!command.isExit());
    }

    public static void main(String[] args) {
        new Meo().run();
    }

    public String getResponse(String input) {
        Command command = new Command();
        try {
            String commandText = input;
            command = new CommandParser().parser(commandText);
            return command.execute(ui, textList, fileHandler);
        } catch (MeoException e) {
            ui.showErrorMessage();
            System.out.println(e);
            e.printStackTrace();
            return e.getMessage();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return "Meo, something went wrong.";
        }
    }
}
