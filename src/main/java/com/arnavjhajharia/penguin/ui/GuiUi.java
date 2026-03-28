// src/main/java/com/arnavjhajharia/penguin/ui/GuiUi.java
package com.arnavjhajharia.penguin.ui;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GuiUi implements Ui {

    private final VBox dialogContainer;
    private final ScrollPane scroller;
    private final Image userAvatar;
    private final Image penguinAvatar;

    private final BlockingQueue<String> inputQueue = new LinkedBlockingQueue<>();
    private volatile boolean closed = false;

    public GuiUi(VBox dialogContainer, ScrollPane scroller, Image userAvatar, Image penguinAvatar) {
        this.dialogContainer = dialogContainer;
        this.scroller = scroller;
        this.userAvatar = userAvatar;
        this.penguinAvatar = penguinAvatar;

        // auto-scroll to bottom
        dialogContainer.heightProperty().addListener((obs, oldV, newV) ->
                scroller.setVvalue(1.0)
        );
    }

    // called by MainFx when user hits Enter
    public void emitUserInput(String text) {
        if (text == null || text.trim().isEmpty()) return;

        // show user's bubble (left)
        Platform.runLater(() -> dialogContainer.getChildren().add(
                DialogBox.user(text, userAvatar) // left
        ));

        inputQueue.offer(text);
    }

    @Override
    public void showIntro() {
        Platform.runLater(() -> dialogContainer.getChildren().add(
                DialogBox.penguin("Hello! I’m Penguin. What can I do for you today?", penguinAvatar)
        ));
    }

    @Override
    public void showDivider() {
        // optional: subtle separator as a tiny message from penguin (or skip)
        Platform.runLater(() -> dialogContainer.getChildren().add(
                DialogBox.divider()
        ));
    }

    @Override
    public void showText(String text) {
        Platform.runLater(() -> dialogContainer.getChildren().add(
                DialogBox.penguin(text, penguinAvatar) // right
        ));
    }

    @Override
    public void showError(String errorText) {
        Platform.runLater(() -> dialogContainer.getChildren().add(
                DialogBox.penguin("[Oops] " + errorText, penguinAvatar, true) // right, error style
        ));
    }

    @Override
    public String readLine() {
        try {
            if (closed) return null;
            return inputQueue.take(); // blocks until user enters input
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public void showExit() {
        closed = true;
        Platform.runLater(() -> {
            dialogContainer.getChildren().add(
                    DialogBox.penguin("Goodbye! I’ll keep your tasks safe in the icebox. ❄️", penguinAvatar)
            );
            Platform.exit();
        });
    }
}
