package klalopz;

import javafx.application.Platform;
import klalopz.exceptions.KlalopzException;
import klalopz.instructions.Instruction;
import klalopz.instructions.Parser;

import klalopz.storage.DataStorage;
import klalopz.tasks.TaskList;
import klalopz.ui.TextUi;

public class Klalopz {
    private final DataStorage dataStorage;
    private final TextUi textUi;
    private final TaskList taskList;
    public Klalopz()  {
        this.dataStorage = new DataStorage(null);
        this.textUi = new TextUi();
        this.taskList = new TaskList(dataStorage.load());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be empty";
        try {
            Instruction currInstruction = Parser.parse(input);
            currInstruction.execute(taskList, dataStorage, textUi);

            assert !textUi.getMessages().isEmpty() : "TextUi should have a message after executing";

            String response = String.join("\n", textUi.getMessages());
            if (currInstruction.doIExit()) {
                Platform.runLater(() -> {
                    Platform.exit();
                    System.exit(0);
                });
            }
            return response;


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            textUi.clearMessages();
        }
    }
}
