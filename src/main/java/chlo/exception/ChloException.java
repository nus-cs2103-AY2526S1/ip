package chlo.exception;

import java.io.IOException;

public class ChloException extends IOException {
    /**
     * Handles app exceptions
     * @param message takes in a error message in the form of a string
     */
    public ChloException(String message){
        super(message);
    }
}