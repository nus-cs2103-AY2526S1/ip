package ackermann.exceptions;

/**
 * Invalid codeword
 */
public class InvalidCodeException extends CheckedException {
    public InvalidCodeException() {
        super("No Such Codeword!");
    }
}
