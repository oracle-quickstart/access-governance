package com.oracle.idm.agcs.grc.fn.responseTemplate.exception;

public class ProcessingFailedException extends RuntimeException {

  public ProcessingFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
