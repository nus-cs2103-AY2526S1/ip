// src/main/java/com/arnavjhajharia/penguin/app/MainFx.java
package com.arnavjhajharia.penguin.app;

import com.arnavjhajharia.penguin.ui.GuiUi;
import com.arnavjhajharia.penguin.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class MainFx extends Application {

    private VBox dialogContainer;
    private ScrollPane scrollPane;
    private TextField inputField;

    private GuiUi guiUi;
    private Thread simulatorThread;

    @Override
    public void start(Stage stage) {
        // UI skeleton
        dialogContainer = new VBox(10);
        dialogContainer.setPadding(new Insets(16));

        scrollPane = new ScrollPane(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        inputField = new TextField();
        inputField.setPromptText("Say something to Penguin…");
        inputField.setOnAction(e -> {
            String text = inputField.getText();
            inputField.clear();
            guiUi.emitUserInput(text); // hands input to Simulator loop
        });

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        root.setBottom(inputField);

        Scene scene = new Scene(root, 460, 640);
        scene.getStylesheets().add(
                getClass().getResource("/css/chat.css").toExternalForm()
        );

        stage.setTitle("Penguin");
        stage.setScene(scene);
        stage.show();

        // Images for chat bubbles
        Image userImg = new Image(getClass().getResourceAsStream("/images/user.png"));
        Image penguinImg = new Image(getClass().getResourceAsStream("/images/penguin.png"));

        // Wire GUI <-> Ui abstraction
        guiUi = new GuiUi(dialogContainer, scrollPane, userImg, penguinImg);

        // Boot the Simulator on a background thread
        simulatorThread = new Thread(() -> {
            Ui ui = guiUi;
            Simulator s = new Simulator("data/penguin.txt", ui);
            s.start();
        }, "Simulator");
        simulatorThread.setDaemon(true);
        simulatorThread.start();
    }

    @Override
    public void stop() {
        // Close JavaFX gracefully
        if (simulatorThread != null && simulatorThread.isAlive()) {
            simulatorThread.interrupt();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
