package com.neokortex;

import javafx.scene.image.Image;

/**
 * Represents a generic User
 * <p>
 * The {@code User} contains information like name and profilePicture
 * </p>
 */
public class User {
    private String name;
    private Image profilePicture;

    /**
     * Constructs a {@code User} with the specified name and profilePicture.
     *
     * @param name the name of the User
     * @param profilePicture the profile picture associated to the {@code User}
     */
    public User(String name, Image profilePicture) {
        this.name = name;
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return this.name;
    }

    public Image getProfilePicture() {
        return this.profilePicture;
    }
}
