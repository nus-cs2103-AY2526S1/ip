package v.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;

/**
 * Manages random user avatars and V's Guy Fawkes mask avatar.
 */
public class AvatarManager {
    private static final Random random = new Random();
    private static final List<String> userAvatars = new ArrayList<>();
    private static Image vAvatar;
    static {
        initializeAvatars();
    }
    private static void initializeAvatars() {
        // Add all user avatar images
        userAvatars.add("/images/user1.jpg");
        userAvatars.add("/images/user2.jpeg");
        userAvatars.add("/images/user3.jpg");
        userAvatars.add("/images/user4.jpg");
        userAvatars.add("/images/user5.jpg");
        userAvatars.add("/images/user6.jpeg");
        // Load V's Guy Fawkes mask
        try {
            vAvatar = new Image(AvatarManager.class.getResourceAsStream("/images/v-mask.png"));
        } catch (Exception e) {
            System.err.println("Failed to load V avatar: " + e.getMessage());
            vAvatar = ImageGenerator.createVImage(); // Fallback
        }
    }
    /**
     * Gets a random user avatar.
     *
     * @return A random user avatar image.
     */
    public static Image getRandomUserAvatar() {
        String randomAvatarPath = userAvatars.get(random.nextInt(userAvatars.size()));
        try {
            return new Image(AvatarManager.class.getResourceAsStream(randomAvatarPath));
        } catch (Exception e) {
            System.err.println("Failed to load user avatar: " + randomAvatarPath);
            return ImageGenerator.createUserImage(); // Fallback
        }
    }
    /**
     * Gets V's Guy Fawkes mask avatar.
     *
     * @return V's avatar image.
     */
    public static Image getVAvatar() {
        return vAvatar;
    }
    /**
     * Gets a specific user avatar by index.
     *
     * @param index The index of the avatar to get.
     * @return The user avatar image at the specified index.
     */
    public static Image getUserAvatar(int index) {
        if (index >= 0 && index < userAvatars.size()) {
            try {
                return new Image(AvatarManager.class.getResourceAsStream(userAvatars.get(index)));
            } catch (Exception e) {
                System.err.println("Failed to load user avatar at index " + index);
            }
        }
        return getRandomUserAvatar();
    }
}
