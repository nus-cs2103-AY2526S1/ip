package com.cc.exceptions;

/**
 * Exception for invalid command words
 */
public class WrongHeadingException extends Exception {
    public WrongHeadingException() {
        super("say what?");
    }
}