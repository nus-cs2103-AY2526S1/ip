package com.neokortex.exceptions;

/**
 * Represents an exception that can arise when attempting to deserialize dates with an invalid format
 */
public class InvalidDateSerializationException extends RuntimeException {
    public InvalidDateSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
