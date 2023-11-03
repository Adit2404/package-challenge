package com.mobiquity.exception;

/**
 * Custom exception class to handle API-related errors in the application.
 */
public class APIException extends Exception {

  /**
   * Constructor for APIException with a message and a nested exception.
   *
   * @param message Error message describing the issue.
   * @param e       Nested exception containing additional details.
   */
  public APIException(String message, Exception e) {
    super(message, e);
  }

  /**
   * Constructor for APIException with a message.
   *
   * @param message Error message describing the issue.
   */
  public APIException(String message) {
    super(message);
  }
}
