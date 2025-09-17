package haru.ui;

import java.util.Objects;

import javafx.scene.image.Image;

/**
 * Chat message bubble aligned to the left with Haru's avatar.
 */
public class HaruMessage extends Message {

    /**
     * Static avatar image for Haru messages.
     */
    public static final Image HARU_AVATAR = new Image(
            Objects.requireNonNull(HaruMessage.class.getResourceAsStream("/images/haru.png"))
    );

    /**
     * Creates a new left-aligned message bubble for Haru.
     *
     * @param text the message text
     */
    public HaruMessage(String text) {
        super(text, HARU_AVATAR, true);
    }
}
