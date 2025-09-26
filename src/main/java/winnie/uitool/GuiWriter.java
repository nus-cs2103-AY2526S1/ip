package winnie.uitool;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import winnie.chatmessage.Sendable;
import winnie.ui.DialogBox;

public class GuiWriter implements UiWriter {
    private VBox dialogContainer;
    private Image winnieImage;

    public GuiWriter(VBox dialogContainer, Image winnieImage) {
        this.dialogContainer = dialogContainer;
        this.winnieImage = winnieImage;
    }

    @Override
    public void write(Sendable message) {
        DialogBox winnieDialog = DialogBox.getWinnieDialog(message.getMessageContent(), winnieImage);
        dialogContainer.getChildren().add(winnieDialog);
    }
}
