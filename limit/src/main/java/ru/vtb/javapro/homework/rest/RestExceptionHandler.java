package ru.vtb.javapro.homework.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.vtb.javapro.homework.model.ErrorDto;

@RestControllerAdvice(assignableTypes = {
        LimitController.class
})
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class.getName());

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(IllegalArgumentException e) {
        log.error("Ошибка обработки лимита: %s".formatted(e.getMessage()));
        return new ResponseEntity<>(new ErrorDto("LIMIT_SERVICE_ERROR_CODE", e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
