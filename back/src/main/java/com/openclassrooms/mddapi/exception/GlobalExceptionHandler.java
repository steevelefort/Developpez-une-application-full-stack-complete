package com.openclassrooms.mddapi.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleEntityNotFound(EntityNotFoundException e) {
    return Map.of("Error", "Ressource non trouvée");
  }


  @ExceptionHandler(NumberFormatException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleNumberFormatException(NumberFormatException e) {
    return Map.of("Error", "Format d’id non valide");
  }

}
