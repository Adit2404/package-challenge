package com.mobiquity.exception;

/**
 * Custom exception class to handle parsing errors in the Packer application.
 */
public class ParserException extends RuntimeException {

    /**
     * Constructor for ParserException with a message and a nested exception.
     *
     * @param message Error message describing the issue.
     * @param e       Nested exception containing additional details.
     */
    public ParserException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Constructor for ParserException with a message.
     *
     * @param message Error message describing the issue.
     */
    public ParserException(String message) {
        super(message);
    }
}
