package com.example.metric.controller;

import com.example.metric.util.Constants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/* this is a global exception handler. For particular handling should use @ExceptionHandler
annotation on controller.
*/
@ControllerAdvice
public class LoanControllerExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String ERROR_BAD_REQUEST = "bad.request.message";

  @Autowired private MessageSource source;

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Map<String, String> response = new HashMap<>();
    response.put(Constants.STATUS, source.getMessage(ERROR_BAD_REQUEST, null, null));
    response.put(Constants.CODE, String.valueOf(HttpStatus.BAD_REQUEST.value()));

    return this.handleExceptionInternal(ex, response, headers, status, request);
  }
}
