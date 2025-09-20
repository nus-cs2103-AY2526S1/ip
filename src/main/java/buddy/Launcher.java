package buddy;

import java.awt.GraphicsEnvironment;

import javafx.application.Application;

public class Launcher {

    private static final String FALLBACK_MESSAGE =
            "Buddy could not start the GUI and is switching to the CLI instead.\n";

    public static void main(String[] args) {
        if (shouldUseCliMode()) {
            runCli();
            return;
        }

        try {
            Application.launch(MainApp.class, args);
        } catch (RuntimeException e) {
            if (isGraphicsInitializationFailure(e)) {
                informFallback(e);
                runCli();
            } else {
                throw e;
            }
        } catch (UnsatisfiedLinkError | ExceptionInInitializerError e) {
            informFallback(e);
            runCli();
        }
    }

    private static boolean shouldUseCliMode() {
        return GraphicsEnvironment.isHeadless() || Boolean.getBoolean("buddy.forceCli");
    }

    private static boolean isGraphicsInitializationFailure(Throwable throwable) {
        for (Throwable cause = throwable; cause != null; cause = cause.getCause()) {
            String message = cause.getMessage();
            if (message == null) {
                continue;
            }

            if (message.contains("No toolkit found") || message.contains("Error initializing QuantumRenderer")
                    || message.contains("Toolkit not initialized") || message.contains("no suitable pipeline")) {
                return true;
            }
        }
        return false;
    }

    private static void informFallback(Throwable cause) {
        System.err.println(FALLBACK_MESSAGE + "Reason: " + cause.getMessage());
    }

    private static void runCli() {
        System.out.println("Buddy is running in CLI mode. Type your commands below!\n");
        new Buddy().run();
    }
}
