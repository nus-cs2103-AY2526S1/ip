package haru.ui;

import java.util.Objects;

import javafx.scene.image.Image;

/**
 * Chat message bubble aligned to the right with the user avatar.
 */
public class UserMessage extends Message {

    /**
     * Static avatar image for user messages.
     */
    public static final Image USER_AVATAR = new Image(
            Objects.requireNonNull(UserMessage.class.getResourceAsStream("/images/user.png"))
    );

    /**
     * Creates a new right-aligned message bubble for the user.
     *
     * @param text the message text
     */
    public UserMessage(String text) {
        super(text, USER_AVATAR, false);
    }
}
