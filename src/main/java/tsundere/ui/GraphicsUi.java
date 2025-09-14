package tsundere.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tsundere.DialogBox;
import tsundere.command.AbstractCommand;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Ui class handling displaying of responses to the user's GUI.
 */
public class GraphicsUi extends AbstractUi {
    private VBox dialogContainer;
    private Image tsundereImage;

    /**
     * Sets the DialogContainer where responses should be added to.
     */
    public GraphicsUi() {
    }
    public void setResources(VBox vBox, Image image) {
        dialogContainer = vBox;
        tsundereImage = image;
    }
    @Override
    public void displayMessage(UiMessages type) {
        String response = UiMessages.getMessage(type);
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, response, tsundereImage));
    }

    @Override
    public void displayMessage(String s) {
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, s, tsundereImage));
    }

    @Override
    public void displayMessage(UiMessages type, Task task) {
        String response = UiMessages.getMessage(type, task);
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, response, tsundereImage));
    }

    @Override
    public void displayMessage(UiMessages type, TaskList tasks) {
        String response = UiMessages.getMessage(type, tasks);
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, response, tsundereImage));
    }

    @Override
    public void displayMessage(UiMessages type, AbstractCommand command) {
        String response = UiMessages.getMessage(type, command);
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, response, tsundereImage));
    }

    @Override
    public void displayMessage(InvalidFormatCommand.Format format) {
        String response = UiMessages.getMessage(UiMessages.HELP, format);
        dialogContainer.getChildren().addAll(DialogBox.getTsundereDialog(dialogContainer, response, tsundereImage));
    }
}
