package chash.ui.gui;

import chash.ui.ChashUi;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/** {@link ChashUi} GUI implementation through JavaFX */
public class ChashGui extends ChashUi {
    private final VBox chatHistBox;
    private final Image userImage;
    private final Image chashImage;
    private final Image chashErrImage;

    /**
     * Initializes the GUI user interface with javafx control
     *
     * @param chatHistBox Main window's output box assigned to chatbot
     * */
    public ChashGui(VBox chatHistBox) {
        assert chatHistBox != null;

        this.chatHistBox = chatHistBox;
        this.userImage = new Image(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png"))
        );
        this.chashImage = new Image(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chash1.jpg"))
        );
        this.chashErrImage = new Image(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/images/chash2.png"))
        );
    }

    /** Not implemented on GUI UI */
    @Override
    public String readLine() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not supported on ChashGui.");
    }

    /**
     * Prints CHASH response to GUI
     *
     * @param txt Message text
     */
    @Override
    public void printMsg(String txt) {
        assert txt != null;

        this.chatHistBox.getChildren().add(
                DialogBox.getResponseDialog(txt, this.chashImage)
        );
    }

    /**
     * Prints user input to GUI
     *
     * @param txt Message text
     */
    @Override
    public void printUserInput(String txt) {
        assert txt != null;

        this.chatHistBox.getChildren().add(
                DialogBox.getUserDialog(txt, this.userImage)
        );
    }

    /**
     * Prints CHASH error message to GUI
     *
     * @param txt Error text
     */
    @Override
    public void printErr(String txt) {
        assert txt != null;

        this.chatHistBox.getChildren().add(
                DialogBox.getErrResponseDialog(txt, this.chashErrImage)
        );
    }
}
