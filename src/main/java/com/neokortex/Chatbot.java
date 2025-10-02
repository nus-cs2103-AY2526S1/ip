package com.neokortex;

import java.util.regex.Pattern;

import com.neokortex.commands.CommandHandler;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.ui.Ui;

import javafx.scene.image.Image;

/**
 * Represents a generic conversational chatbot that provides user interaction.
 *
 * <p>
 * The {@code Chatbot} is the primary interface that the user will interact with.
 * It's responsibilities are as follows.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Respond to the user's every command through the {@link #interpret(String)} method</li>
 *   <li>Notify users of the success or failure of command execution</li>
 *   <li>Keep track of the identity of the {@code Chatbot} and all the details
 *   unique to it, such as its name, its DialogueMap and its handler</li>
 *   <li>Help remember information between interpretation</li>
 * </ul>
 *
 * <p><b>Credit: documentation was written based on suggestions and recommendations from generative AI</b></p>
 * @see Ui
 * @see CommandHandler
 * @see DialogueMap
 */
public class Chatbot {
    private final String name;
    private CommandHandler handler;
    private Ui ui;
    private boolean exitProgram = false;

    /**
     * ProfilePicture can be null in the event that we are running the CLI version of the program
     */
    private Image profilePicture;
    private DialogueMap dialogueMap;

    /**
     * Represents the list of the responses that we are going to send to the user temporarily
     */
    private String[] pendingResponses;

    /**
     * Constructs a new Chatbot instance with the specified name. As of now,
     * since there is only one {@link DialogueMap}, it simply initialises
     * it in the constructor as well.
     *
     * @param name the name of this chatbot. It is used in personalized messages.
     */
    public Chatbot(String name) {
        this.name = name;
        this.dialogueMap = new DialogueMap();
    }


    public void setCommandHandler(CommandHandler handler) {
        this.handler = handler;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Unused at the moment as there is only 1 {@code DialogueMap}
     *
     * @param dialogueMap the dialogueMap associated with the Chatbot. Contains all possible
     *                    responses to command execution responses as returned by {@link CommandHandler}
     */
    public void setDialogueMap(DialogueMap dialogueMap) {
        this.dialogueMap = dialogueMap;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Displays startup messages to the user through the appropriate ui.
     *
     * <p>
     * Startup mainly consists of the following.
     * </p>
     *
     * <p><b>Startup Sequence:</b></p>
     * <ol>
     *     <li>Greets the user</li>
     *     <li>Display special message upon load error</li>
     *     <li>Prompts user for action</li>
     * </ol>
     *
     * @param hasLoadError indicates whether data loading encountered errors.
     *                     If true, a specialised error message will be displayed.
     *
     * @see Ui
     */
    public void displayStartupMessage(boolean hasLoadError) {
        if (hasLoadError) {
            ui.respond(this.getDialogue(DialoguePath.STARTUP_FAILURE));
        } else {
            ui.respond(this.getDialogue(DialoguePath.STARTUP));
        }
    }

    /**
     * Interprets a user input and responds accordingly. Responses are gathered through
     * the result of running {@link CommandHandler#interpret(String)}, from which the
     * appropriate {@link DialoguePath}s are extracted and used to form responses. Responses
     * are stored within the {@code pendingResponses} field. Responses will then be sent out
     * through the {@link Chatbot#respond()} method.
     *
     * @param input the user input
     */
    public void interpret(String input) {
        Response handlerResponse = this.handler.interpret(input);
        this.pendingResponses = this.getDialogue(
                handlerResponse.getDirective(), handlerResponse.getAttachedResults());
        if (handlerResponse.getStatus() == ResponseStatus.EXIT_PROGRAM) {
            this.exitProgram = true;
        }
    }

    /**
     * Sends the appropriate response to command execution to the user through the ui.
     */
    public void respond() {
        this.ui.respond(pendingResponses);
        pendingResponses = null;
        if (this.exitProgram) {
            Thread temp = new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
                System.exit(0);
            });

            temp.start();

        }
    }

    /**
     * Gets the corresponding dialogue string from the {@link DialogueMap} and makes the
     * appropriate substitutions through the makeSubstitution method.
     *
     * @param dialoguePath the specified DialoguePath
     * @param arguments arguments from the attached results of {@link Response}
     * @return an array of all necessary responses to make.
     */
    private String[] getDialogue(DialoguePath dialoguePath, String... arguments) {
        String base = this.dialogueMap.getDialogueFromDirective(dialoguePath);
        String formatted = makeSubstitutions(base, arguments);
        return new String[] { formatted };
    }

    /**
     * Makes the appropriate substitutions to dialogue from {@link DialogueMap} as specified
     * in {@link DialogueMap}
     *
     * @param input the String to make substitutions on
     * @param arguments arguments from the attached results of {@link Response}
     * @return the String with the correct substitutions made.
     */
    private String makeSubstitutions(String input, String... arguments) {
        Pattern curlyBraces = Pattern.compile("(?<!\\\\)\\{([^{}]*)\\}");
        String output = curlyBraces.matcher(input).replaceAll(match -> {
            String content = match.group(1);
            String[] parts = content.split(":");
            String key = parts[0];

            return switch (key) {
            case "r" -> arguments[Integer.parseInt(parts[1])];
            case "name" -> this.name;
            default -> content;
            };
        });

        return output;
    }
}
