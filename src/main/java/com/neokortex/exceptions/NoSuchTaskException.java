package com.neokortex.exceptions;

/**
 * Represents an exception that can arise during task deserialisation where an invalid Task Type is found.
 */
public class NoSuchTaskException extends RuntimeException {
    public NoSuchTaskException(char taskType) {
        super(String.format("There are no such task of type '%c'", taskType));
    }
}
