package chash.ui.gui;

import chash.ui.ChashUi;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class ChashGui extends ChashUi {
    private final VBox chatHistBox;
    private final Image userImage;
    private final Image chashImage;
    private final Image chashErrImage;

    public ChashGui(VBox chatHistBox) {
        this.chatHistBox = chatHistBox;
        this.userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
        this.chashImage = new Image(this.getClass().getResourceAsStream("/images/chash1.jpg"));
        this.chashErrImage = new Image(this.getClass().getResourceAsStream("/images/chash2.png"));
    }

    @Override
    public String readLine() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not supported on ChashGui.");
    }

    @Override
    public void printMsg(String txt) {
        this.chatHistBox.getChildren().add(
                DialogBox.getResponseDialog(txt, this.chashImage)
        );
    }

    @Override
    public void printUserInput(String txt) {
        this.chatHistBox.getChildren().add(
                DialogBox.getUserDialog(txt, this.userImage)
        );
    }

    @Override
    public void printErr(String txt) {
        this.chatHistBox.getChildren().add(
                DialogBox.getErrResponseDialog(txt, this.chashErrImage)
        );
    }
}
