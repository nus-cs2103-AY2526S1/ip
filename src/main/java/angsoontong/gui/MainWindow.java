package angsoontong.gui;

import angsoontong.AngSoonTong;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;


/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private static class LyricLine {
        final double atSec;   // when to show (seconds from start)
        final String text;
        LyricLine(double atSec, String text) { this.atSec = atSec; this.text = text; }
    }

    /**
     * lyrics for the 1st song that could possibly be played
     */
    private final java.util.List<LyricLine> LYRICS1 = java.util.List.of(
            new LyricLine(0.3,  "希望你以后不会后悔没选择我"),
            new LyricLine(6.4,  "也相信你有更好的生活"),
            new LyricLine(13.0,  "我会在心里"),
            new LyricLine(15.5,  "默默地为你而执着~")
    );

    /**
     * lyrics for the 2nd song that could possibly be played
     */
    private final java.util.List<LyricLine> LYRICS2 = java.util.List.of(
            new LyricLine(0.2,  "gang chant~"),
            new LyricLine(13.6,  "toh teng jit ki ang ji kao!"),
            new LyricLine(23.7,  "kaninabeh si kah po!"),
            new LyricLine(28.2,  "I wanna know where you belong~")
    );

    /**
     * lyrics for the 3rd song that could possibly be played
     */
    private final java.util.List<LyricLine> LYRICS3 = java.util.List.of(
            new LyricLine(0.0,  "如果让你从新来过你会不会爱我"),
            new LyricLine(5.1,  "爱情让人拥有快乐也会带来折磨"),
            new LyricLine(11.9,  "曾经和你一起走过传说中的爱河"),
            new LyricLine(17.8,  "已经被我泪水淹没变成痛苦的爱河")
    );

    private AngSoonTong angSoonTong;
    private Timeline lyricsTimeline;
    private MediaPlayer player;
    private int currSong;

    /**
     * access images from resources folder for profile pictures
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/AhBeng.png"));
    private Image ASTImage = new Image(this.getClass().getResourceAsStream("/images/AngSoonTong.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the AST instance
     */
    public void setAngSoonTong(AngSoonTong ast) {
        angSoonTong = ast;
        dialogContainer.getChildren().add(
                DialogBox.getASTDialog("Eh! I'm Soon Tong\nWhat you want?!", ASTImage)
        );
    }

    /**
     * array containing filePaths for each of the possible songs
     */
    private static final String[] SONGS = {
            "/audio/song1.wav",
            "/audio/song2.wav",
            "/audio/song3.wav"
    };

    /**
     * randomly picks a song number and returns its filePath, and sets currSong to that number
     */
    private String pickRandomSong() {
        int i = ThreadLocalRandom.current().nextInt(SONGS.length);
        this.currSong = i + 1;
        return SONGS[i];
    }

    /**
     * sing function randomly chooses and then plays a song when sing command is called
     */
    private void sing() {

        // setup media
        String path = getClass().getResource(pickRandomSong()).toExternalForm();
        player = new MediaPlayer(new Media(path));
        player.setStopTime(Duration.seconds(42));

        // lyric timeline
        if (lyricsTimeline != null) lyricsTimeline.stop();
        lyricsTimeline = new Timeline();
        lyricsTimeline.setCycleCount(1);

        // add keyframe for each lyric line
        if (currSong == 1) {
            for (LyricLine line : LYRICS1) {
                lyricsTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(line.atSec), e -> showResponse(line.text))
                );
            }
        } else if (currSong == 2) {
            for (LyricLine line : LYRICS2) {
                lyricsTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(line.atSec), e -> showResponse(line.text))
                );
            }
        } else if (currSong == 3) {
            for (LyricLine line : LYRICS3) {
                lyricsTimeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(line.atSec), e -> showResponse(line.text))
                );
            }
        } else { }

        player.play();
        lyricsTimeline.play();

        // cleanup when media ends
        player.setOnEndOfMedia(() -> {
            if (lyricsTimeline != null) lyricsTimeline.stop();
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * AngSoonTong's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = angSoonTong.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getASTDialog(response, ASTImage)
        );
        userInput.clear();

        if (input.equalsIgnoreCase("sing")) {
            sing();
            userInput.clear();
            return;
        }

        // shutdown on 'bye' input
        if (input.trim().equalsIgnoreCase("bye")) {
            // delay a bit so user sees the final message
            new Thread(() -> {
                try {
                    Thread.sleep(1000); // 1 second delay
                } catch (InterruptedException e) {
                    // ignore
                }
                javafx.application.Platform.exit();
            }).start();
        }
    }

    /**
     * Adds a line of text to the chat window as the chatbot's response.
     */
    public void showResponse(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        dialogContainer.getChildren().add(label);

        // ensure we’re not setting a bound property
        javafx.application.Platform.runLater(() -> {
            scrollPane.vvalueProperty().unbind();   // <— important
            scrollPane.setVvalue(1.0);
        });
    }

}
