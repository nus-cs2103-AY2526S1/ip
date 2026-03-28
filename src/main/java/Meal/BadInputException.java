package Meal;

public class BadInputException extends RuntimeException {
    public BadInputException(String msg) {
        super(msg);
    }
}