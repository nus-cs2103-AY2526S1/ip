// src/main/java/com/arnavjhajharia/penguin/ui/DialogBox.java
package com.arnavjhajharia.penguin.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private DialogBox(String text, Image avatar, boolean isPenguin, boolean isError) {
        setSpacing(10);
        setPadding(new Insets(4, 4, 4, 4));
        getStyleClass().add("dialog");

        Label bubble = new Label(text);
        bubble.setWrapText(true);
        bubble.setMaxWidth(320);
        bubble.getStyleClass().add("bubble");
        if (isPenguin) {
            bubble.getStyleClass().add("bubble-penguin");
        } else {
            bubble.getStyleClass().add("bubble-user");
        }
        if (isError) {
            bubble.getStyleClass().add("bubble-error");
        }

        ImageView dp = new ImageView(avatar);
        dp.setFitWidth(36);
        dp.setFitHeight(36);
        dp.getStyleClass().add("avatar");

        if (isPenguin) {
            setAlignment(Pos.TOP_RIGHT);
            getChildren().addAll(bubble, dp); // bubble then avatar on right
        } else {
            setAlignment(Pos.TOP_LEFT);
            getChildren().addAll(dp, bubble); // avatar then bubble on left
        }
    }

    public static Node user(String text, Image avatar) {
        return new DialogBox(text, avatar, false, false);
    }

    public static Node penguin(String text, Image avatar) {
        return new DialogBox(text, avatar, true, false);
    }

    public static Node penguin(String text, Image avatar, boolean error) {
        return new DialogBox(text, avatar, true, error);
    }

    public static Node divider() {
        HBox line = new HBox();
        line.getStyleClass().add("divider");
        line.setPadding(new Insets(6, 0, 6, 0));
        return line;
    }
}
