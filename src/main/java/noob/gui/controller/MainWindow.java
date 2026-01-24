package noob.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import noob.Noob;
import noob.gui.element.SendButton;
import noob.gui.element.UserInput;
import noob.gui.handler.DisplayHandler;
import noob.gui.layout.DialogContainer;
import noob.gui.layout.MainScrollPane;

public class MainWindow extends AnchorPane {
    private static final int MAIN_LAYOUT_HEIGHT = 800;
    private static final int MAIN_LAYOUT_WIDTH = 600;
    private static final int MAIN_SCROLLPANE_HEIGHT = MAIN_LAYOUT_HEIGHT * 9 / 10;
    private static final int MAIN_SCROLLPANE_WIDTH = MAIN_LAYOUT_WIDTH * 39 / 40;
    private static final int USER_INPUT_WIDTH = MAIN_LAYOUT_WIDTH * 13 / 16;
    private static final int SEND_BUTTON_WIDTH = MAIN_LAYOUT_WIDTH / 8;

    @FXML
    private MainScrollPane mainScrollPane;
    @FXML
    private DialogContainer dialogContainer;
    @FXML
    private UserInput userInput;
    @FXML
    private SendButton sendButton;

    private Noob noob;

    @FXML
    public void initialize() {
        setUpComponents();
        setUpAnchorPane();
    }

    /**
     * Injects the NoobBot instance
     *
     * @param n NoobBot instance
     */
    public void setNoob(Noob n) {
        this.noob = n;
    }

    /**
     * Displays greeting message to user in GUI
     */
    public void greet() {
        DisplayHandler.displayGreeting(dialogContainer, noob);
    }

    /**
     * Styles the components in the GUI upon launch
     */
    private void setUpComponents() {
        mainScrollPane.setStyle(MAIN_SCROLLPANE_WIDTH, MAIN_SCROLLPANE_HEIGHT);
        dialogContainer.setStyle(mainScrollPane);
        userInput.setStyle(USER_INPUT_WIDTH);
        sendButton.setStyle(SEND_BUTTON_WIDTH);
    }

    /**
     * Styles the layout of the AnchorPane
     */
    private void setUpAnchorPane() {
        this.setPrefHeight(MAIN_LAYOUT_HEIGHT);
        this.setPrefWidth(MAIN_LAYOUT_WIDTH);
        AnchorPane.setTopAnchor(mainScrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Initializes the necessary fields of GUI components in FXML injection
     * MUST be called after bot injection to ensure that Noob is not null
     */
    public void initializeComponentFields() {
        userInput.initialize(dialogContainer, noob);
        sendButton.initialize(dialogContainer, userInput, noob);
    }
}
