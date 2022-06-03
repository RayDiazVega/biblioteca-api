package com.ceiba.biblioteca.exception;

import com.ceiba.biblioteca.prestamo.dto.PrestamoError;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

  private String isMethodArgumentNotValidException(Exception ex) {
    if (ex instanceof MethodArgumentNotValidException) {
      return Objects.requireNonNull(
              ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError())
          .getDefaultMessage();
    }
    return ex.getMessage();
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentNotValidException.class,
      PropertyReferenceException.class, NoSuchElementException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object badRequest(Exception ex) {
    ex.printStackTrace();
    String error = isMethodArgumentNotValidException(ex);
    return new PrestamoError(error);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Object internalServerError(Exception ex) {
    ex.printStackTrace();
    return new PrestamoError("Server error, ".concat(LocalDateTime.now().toString()));
  }
}
