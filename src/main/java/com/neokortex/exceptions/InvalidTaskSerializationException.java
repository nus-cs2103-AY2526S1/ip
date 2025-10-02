package com.neokortex.exceptions;

/**
 * Represents an exception that can arise when attempting to deserialize tasks with an invalid format
 */
public class InvalidTaskSerializationException extends Exception {
    public InvalidTaskSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
