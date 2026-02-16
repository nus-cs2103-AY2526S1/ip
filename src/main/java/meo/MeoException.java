package meo;
public class MeoException extends Exception {
    public static String taskMissing = "Meow, please insert your task.";
    public static String deadlineTime = "Meow, please include your deadline with /by.";
    public static String eventTime = "Meow, please include your from and to timing with /from and /to.";
    public static String taskNotExist = "Meow, this task doesn't exist :<.";
    public static String commandUnknown = "Meow, I don't understand that... ";

    public MeoException(String errorMessage) {
        super(errorMessage);
    }

}
