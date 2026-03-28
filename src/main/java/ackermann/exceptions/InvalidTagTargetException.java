package ackermann.exceptions;

public class InvalidTagTargetException extends CheckedException {
    public InvalidTagTargetException() {
        super("Invalid Selection. Please make sure target exists.\nE.g. tag 1 something");
    }
}
