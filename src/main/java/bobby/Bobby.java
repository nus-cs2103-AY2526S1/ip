package bobby;

import listmanager.ListManager;

import uimanager.UI;

import parser.Parser;

import java.util.Objects;


/**
 * A Chatbot that stores tasks. <code>Bobby</code> consists of a
 * <code>ListManager</code> that stores tasks, <code>UI</code> object that
 * manages responses and  a <code>Parser</code> that handles user input.
 *
 */
public class Bobby {
    private ListManager listManager;
    private Parser parser;
    private UI ui;

    /**
     * Sets isRunning to true.
     * Initializes ListManager, parser and ui instances.
     */
    public Bobby() {
        parser = new Parser();
        listManager = new ListManager();
        ui = new UI();
    }

    /**
     * Sets boolean isRunning to false.
     * Closes UI and ListManager instances.
     */
    public String endChat() {
        listManager.closeList();
        return ui.onEnd();
    }


    public String run(String ... n) {
        try {
            if (n.length == 0) {
                return ui.onStart();
            }
            String input = n[0];
            String response = parser.parseInput(listManager, input);

            if (Objects.equals(response, "bye")) {
                return endChat();
            }
            return response;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
