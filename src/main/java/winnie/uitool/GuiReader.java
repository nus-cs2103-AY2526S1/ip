package winnie.uitool;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import winnie.chatmessage.Readable;
import winnie.chatmessage.ReceivedMessage;
import winnie.ui.DialogBox;

public class GuiReader implements UiReader {

    private VBox dialogContainer;
    private TextField userInput;
    private Image userImage;

    public GuiReader(VBox dialogContainer, TextField userInput, Image userImage) {
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
        this.userImage = userImage;
    }

    @Override
    public Readable read() {
        String inputText = userInput.getText();
        DialogBox userDialog = DialogBox.getUserDialog(inputText, userImage);
        dialogContainer.getChildren().add(userDialog);
        userInput.clear();
        return new ReceivedMessage(inputText);
    }

}
