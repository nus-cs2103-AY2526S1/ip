/// Skeletal Version of GoksChat
///
/// @author Ravichandran Gokul
public class GoksChat {
    private static Ui ui = new Ui();
    private static InputProcessor inputProcessor = new InputProcessor(ui);

    public static void main(String[] args) throws InvalidPromptException, TodoException {
        ui.printWelcomeMessage();

        // Get user input
        String userInput = ui.readUserInput();

        // Print according to what the user input is
        while (!userInput.equals("bye")) {
            try {
                Command c = inputProcessor.processInput(userInput);
                c.execute();
            } catch (TodoException e) {
                ui.exceptionMessage(e);
            } catch (InvalidPromptException e) {
                ui.exceptionMessage(e);
            } finally {
                // Get user input again
                userInput = ui.readUserInput();
            }
        }

        ui.printGoodbyeMessage();
    }
}
