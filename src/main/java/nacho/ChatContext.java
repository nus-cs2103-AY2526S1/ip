package nacho;

import nacho.commons.UiType;
import nacho.tasks.TaskList;

/**
 * Handles UI and Chat Context for Nacho Chatbot
 * chatType values:
 * (1): CLI -> Prints styled reply to CLI
 * (2): GUI -> Saves reply to latestMessage property to be read
 */
public class ChatContext {
    // Visual Elements
    private static String horizontalLine = "-----------------------------------";
    private static final int INDENT_LEVEL = 4;
    private TaskList taskList;
    private UiType chatType;
    private String latestMessage;
    private boolean isLatestMessageWrong = false;

    /**
     * Sets the Chat Context Object
     * @param taskList List of Task Objects
     * @param chatType type of chat "GUI" or "CLI" -> Affects reply handling
     */
    public ChatContext(TaskList taskList, UiType chatType) {
        this.taskList = taskList;
        this.chatType = chatType;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public int getIndentLevel() {
        return INDENT_LEVEL;
    }

    /**
     * Styles desired message with UI elements representing Nacho's voice
     * (e.g. Horizontal Line separation & Indentation)
     * @param message String containing information to be "said" by Nacho Chatbot
     */
    public void reply(String message) {
        assert (this.chatType == UiType.CLI || this.chatType == UiType.GUI);
        if (this.chatType == UiType.CLI) {
            String styledMessage = (horizontalLine + "\n" + message + "\n" + horizontalLine).indent(INDENT_LEVEL);
            System.out.print(styledMessage);
        } else if (this.chatType == UiType.GUI) {
            this.latestMessage = message;
        }
    }

    /**
     * Returns the latest message saved after replying in GUI mode
     * (Workaround to reduce need to rewrite old code)
     */
    public String getLatestReply() {
        String message = this.latestMessage;
        this.latestMessage = null; // Resets reply message after being "Read" to prevent reread
        return message;
    }

    /**
     * Returns whether the latest message is an error message
     * To use in styling GUI error messages
     * Resets back to false after being read
     * @return boolean representing whether latest message is an error message
     */
    public boolean getLatestMessageValidity() {
        boolean temp = this.isLatestMessageWrong;
        this.isLatestMessageWrong = false;
        return temp;
    }

    /**
     * Sets the latest message as an error message
     * To use in styling GUI error messages
     */
    public void setLatestMessageWrong() {
        this.isLatestMessageWrong = true;
    }
}
