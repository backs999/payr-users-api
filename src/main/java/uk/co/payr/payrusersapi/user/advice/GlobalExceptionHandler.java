package uk.co.payr.payrusersapi.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import uk.co.payr.payrusersapi.user.exception.UserApiException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<String> handleCustomException(UserApiException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getHttpStatusCode()));
    }
}